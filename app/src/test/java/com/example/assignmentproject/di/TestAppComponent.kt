package com.example.assignmentproject.di

import com.example.assignmentproject.BaseRepositoryTest
import com.example.assignmentproject.di.module.ApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestNetworkModule::class, TestRxJavaModule::class, ApiModule::class])
interface TestAppComponent {
    fun inject(baseRepository: BaseRepositoryTest)

}