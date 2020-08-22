package com.example.assignmentproject.ui.news.detail

import android.os.Bundle
import com.example.assignmentproject.R
import com.example.assignmentproject.databinding.ActivityDetailBinding
import com.example.assignmentproject.ui.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*

class MainActivityDetail : BaseActivity<ActivityDetailBinding, MainActivityDetailViewModel>(){
    override val layoutViewRes: Int =
            R.layout.activity_detail

    override val viewModelClass: Class<MainActivityDetailViewModel>
        get() = MainActivityDetailViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
    }

    override fun onViewCreated() {
        super.onViewCreated()
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    companion object{
        const val EXTRA_ARTICLES = "articles"
    }
}