package com.example.assignmentproject.di

import com.example.assignmentproject.common.utils.Constant
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class TestRxJavaModule {

    @Provides
    @Named(Constant.SUBCRIBER_ON)
    @Singleton
    fun provideSuscriberOn(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named(Constant.OBSERVER_ON)
    @Singleton
    fun provideObserverOn(): Scheduler = AndroidSchedulers.mainThread()
}