package com.example.assignmentproject.ui.news

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.assignmentproject.R
import com.example.assignmentproject.databinding.ActivityMainBinding
import com.example.assignmentproject.ui.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(),MainNavigator{
    override val layoutViewRes: Int
        get() = R.layout.activity_main

    override val viewModelClass: Class<MainActivityViewModel>
        get() = MainActivityViewModel::class.java

    private val mainAdapter by lazy {
        MainAdapter(this)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        recycler.adapter = mainAdapter
    }


    override fun onViewModelCreated() {
        super.onViewModelCreated()
        viewModel.articlesLiveData.observe(this, Observer {
            mainAdapter.submitList(it)
        })
    }
}