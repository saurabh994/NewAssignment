package com.example.assignmentproject.ui.news.detail

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.example.assignmentproject.common.extension.getDate
import com.example.assignmentproject.common.extension.request
import com.example.assignmentproject.common.widget.ListLiveData
import com.example.assignmentproject.data.remote.model.ArticlesItem
import com.example.assignmentproject.data.remote.repository.MainRepository
import com.example.assignmentproject.di.scope.ActivityScope
import com.example.assignmentproject.ui.base.viewmodel.BaseActivityViewModel
import com.google.gson.Gson
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class MainActivityDetailViewModel @Inject constructor(): BaseActivityViewModel() {
    val articles = MutableLiveData<ArticlesItem>()
    val date = MutableLiveData<String>()

    override fun handleIntent(intent: Intent) {
        super.handleIntent(intent)
        intent.extras?.apply {
            articles.value = Gson().fromJson(
                getString(MainActivityDetail.EXTRA_ARTICLES),
                ArticlesItem::class.java
            )
            date.value = articles.value?.publishedAt?.let {
                it.getDate(it)
            }
        }
    }

}