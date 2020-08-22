package com.example.assignmentproject.di.module

import com.example.assignmentproject.ui.news.MainActivity
import com.example.assignmentproject.di.scope.ActivityScope
import com.example.assignmentproject.ui.news.MainActivityModule
import com.example.assignmentproject.ui.news.detail.MainActivityDetail
import com.example.assignmentproject.ui.news.detail.MainActivityDetailModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @ActivityScope
    abstract fun contributeUploadActivity(): MainActivity

    @ContributesAndroidInjector(modules = [MainActivityDetailModule::class])
    @ActivityScope
    abstract fun contributeMainActivityDetail(): MainActivityDetail
}