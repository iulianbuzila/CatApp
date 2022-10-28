package com.example.catapp.ui.base

import androidx.lifecycle.ViewModel
import com.example.catapp.utils.SingleLiveEvent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel: ViewModel() {
    var progressLiveEvent = SingleLiveEvent<Boolean>()
    var errorMessage = SingleLiveEvent<String>()

    private var disposables: CompositeDisposable? = null

    protected fun addSubscription(compositeDisposable: Disposable) {
        if (disposables == null || disposables!!.isDisposed) {
            disposables = CompositeDisposable()
        }
        disposables!!.add(compositeDisposable)
    }

    protected fun unSubscribeAll() {
        if (disposables != null && !disposables!!.isDisposed) {
            disposables!!.dispose()
            disposables = null
        }
    }

    override fun onCleared() {
        unSubscribeAll()
        super.onCleared()
    }

    fun setIsLoading(value: Boolean) {
        progressLiveEvent.value = value
    }

    fun setErrorMessage(message: String?) {
        message?.let {
            if (it.isNotEmpty()) {
                errorMessage.value = it
            }
        }
    }
}