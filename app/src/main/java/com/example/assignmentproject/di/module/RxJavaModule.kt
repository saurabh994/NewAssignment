package com.example.assignmentproject.di.module

import com.example.assignmentproject.common.utils.Constant
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class RxJavaModule {
    @Provides
    @Named(Constant.SUBCRIBER_ON)
    @Singleton
    fun provideSubscriberOn(): Scheduler = Schedulers.io()

    @Provides
    @Named(Constant.OBSERVER_ON)
    @Singleton
    fun provideObserverOn(): Scheduler = AndroidSchedulers.mainThread()
}