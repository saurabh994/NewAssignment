package com.example.assignmentproject.di.module

import com.example.assignmentproject.data.remote.api.MainInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideMainApi(retrofit: Retrofit): MainInterface =
        retrofit.create(MainInterface::class.java)
}