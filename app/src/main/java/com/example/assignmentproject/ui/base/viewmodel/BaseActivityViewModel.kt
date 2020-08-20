package com.example.assignmentproject.ui.base.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivityViewModel : ViewModel() {
    protected lateinit var mContext: Context

    open fun handleActivityContext(context: Context) {
        mContext = context
    }

    internal val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    open fun handleCreate(){}

    open fun handleIntent(intent: Intent) {}

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}