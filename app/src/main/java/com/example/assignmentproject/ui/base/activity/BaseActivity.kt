package com.example.assignmentproject.ui.base.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentproject.di.qualifier.ActivityContext
import com.example.assignmentproject.ui.base.viewmodel.BaseActivityViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import com.example.assignmentproject.BR

abstract class BaseActivity<D : ViewDataBinding, V : BaseActivityViewModel> : AppCompatActivity(),
    HasAndroidInjector {
    @Inject
    protected lateinit var supportFragmentInjector: DispatchingAndroidInjector<Any>

    @Inject
    @field:ActivityContext
    protected lateinit var viewModelProvider: ViewModelProvider

    @get:LayoutRes
    protected abstract val layoutViewRes: Int

    protected abstract val viewModelClass: Class<V>
    lateinit var viewModel: V
        private set

    protected lateinit var binding: D
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        //create view model
        viewModel = viewModelProvider.get(viewModelClass)
        viewModel.handleActivityContext(this)
        viewModel.handleCreate()
        viewModel.handleIntent(intent)
        onViewModelCreated()

        //create data binding layout and set view model
        binding = DataBindingUtil.setContentView(this, layoutViewRes)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel,viewModel)
        onViewCreated()
    }

    protected fun setToolbar(
        toolbar: Toolbar
    ) {
        setSupportActionBar(toolbar)
    }

    @CallSuper
    protected open fun onViewModelCreated() {

    }

    @CallSuper
    protected open fun onViewCreated() {

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return supportFragmentInjector
    }
}
