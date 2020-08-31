package com.example.assignmentproject.ui.news

import android.content.Intent
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.assignmentproject.R
import com.example.assignmentproject.data.remote.model.ArticlesItem
import com.example.assignmentproject.databinding.ActivityMainBinding
import com.example.assignmentproject.ui.base.activity.BaseActivity
import com.example.assignmentproject.ui.news.detail.MainActivityDetail
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

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
        setToolbar(toolbar)
        this.setToolbar(toolbar)
        val tvTitle = toolbar.findViewById<TextView>(R.id.tv_title)
        tvTitle.text = "HEADLINES"
        supportActionBar?.title = ""
        recycler.adapter = mainAdapter
    }


    override fun onViewModelCreated() {
        super.onViewModelCreated()
        viewModel.newsData.observe(this, Observer {
            mainAdapter.submitList(it)
        })
    }

    override fun onClickItem(item: ArticlesItem) {
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra(MainActivityDetail.EXTRA_ARTICLES,Gson().toJson(item))
        startActivity(intent)
    }
}