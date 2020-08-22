package com.example.assignmentproject.di.module

import android.app.Application
import com.example.assignmentproject.data.db.AppDatabase
import com.example.assignmentproject.data.db.dao.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun providesAppDatabase(application: Application): AppDatabase =
        AppDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providesPostDao(appDatabase: AppDatabase): NewsDao = appDatabase.newsDao()
}