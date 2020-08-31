package com.example.assignmentproject

import android.content.Context
import androidx.multidex.MultiDex
import com.example.assignmentproject.di.component.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class ApplicationClass : DaggerApplication(){
    @Inject
    lateinit var timberTree: Timber.Tree
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        LeakCanary.install(this)
        Timber.plant(timberTree)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        try {
            MultiDex.install(this)
        } catch (multiDexException: RuntimeException) {
            multiDexException.printStackTrace()
        }
    }
}