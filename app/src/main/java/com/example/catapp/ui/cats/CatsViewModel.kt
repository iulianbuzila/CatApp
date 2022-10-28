package com.example.catapp.ui.cats

import androidx.core.view.ViewCompat
import androidx.lifecycle.MutableLiveData
import com.example.catapp.R
import com.example.catapp.data.repository.CatRepository
import com.example.catapp.extensions.applyDefaultSchedulers
import com.example.catapp.models.CatModel
import com.example.catapp.ui.base.BaseViewModel
import com.example.catapp.utils.StringResource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val catRepository: CatRepository,
    private val stringResource: StringResource
) : BaseViewModel() {

    val catsList = MutableLiveData<List<CatModel>>()
    val catsFiltered = MutableLiveData<List<CatModel>>()
    val countriesLiveData = MutableLiveData<ArrayList<String>>()
    val checkedFilters = arrayListOf<Int>()

    private val chipsIds = arrayListOf<Pair<String,Int>>()

    fun getCatsList() {
        addSubscription(catRepository.fetchCats()
            .applyDefaultSchedulers()
            .doOnSubscribe { setIsLoading(true) }
            .doAfterTerminate { setIsLoading(false) }
            .subscribeBy(
                onSuccess = {
                    val countries = countriesLiveData.value ?: arrayListOf()
                    it.forEach { cat ->
                        cat.countryCode?.let { countryCode ->
                            if (!countries.contains(countryCode)) {
                                countries.add(countryCode)
                            }
                        }
                    }
                    countriesLiveData.postValue(countries)
                    catsList.postValue(it)
                },
                onError = {
                    setErrorMessage(stringResource.getString(R.string.lbl_something_went_wrong))
                }
            )
        )
    }

//    TODO for a long list, filter should be done in a different thread
    fun filterCats(filters: ArrayList<String>) {
        if (filters.isEmpty()) {
            catsFiltered.postValue(catsList.value)
            return
        }

        val filteredCats = arrayListOf<CatModel>()
        catsList.value?.forEach { cat ->
            if (filters.contains(cat.countryCode)) {
                filteredCats.add(cat)
            }
        }
        catsFiltered.postValue(filteredCats)
    }

    fun getViewId(countryCode: String): Int {
        var oldId: Int? = null
        chipsIds.forEach {
            if (it.first == countryCode) {
                oldId = it.second
            }
        }
        return if (oldId != null) {
            oldId!!
        } else {
            val id = ViewCompat.generateViewId()
            chipsIds.add(Pair(countryCode, id))
            id
        }
    }
}