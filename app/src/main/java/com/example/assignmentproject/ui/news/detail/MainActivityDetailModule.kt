package com.example.assignmentproject.ui.news.detail

import androidx.appcompat.app.AppCompatActivity
import com.example.assignmentproject.di.key.ActivityViewModelKey
import com.example.assignmentproject.di.scope.ActivityScope
import com.example.assignmentproject.ui.base.activity.BaseActivityModule
import com.example.assignmentproject.ui.base.viewmodel.BaseActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [BaseActivityModule::class])
abstract class MainActivityDetailModule {
    @Binds
    @ActivityScope
    abstract fun bindActivity(activity: MainActivityDetail): AppCompatActivity

    @Binds
    @IntoMap
    @ActivityViewModelKey(MainActivityDetailViewModel::class)
    @ActivityScope
    abstract fun bindViewModel(viewModel: MainActivityDetailViewModel): BaseActivityViewModel
}