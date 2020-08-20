package com.example.assignmentproject.di.key

import com.example.assignmentproject.ui.base.viewmodel.BaseActivityViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
@MustBeDocumented
annotation class ActivityViewModelKey(val value: KClass<out BaseActivityViewModel>)