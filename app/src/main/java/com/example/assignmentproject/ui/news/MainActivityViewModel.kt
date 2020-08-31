package com.example.assignmentproject.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignmentproject.common.extension.applyIoScheduler
import com.example.assignmentproject.common.extension.disposeWith
import com.example.assignmentproject.common.extension.request
import com.example.assignmentproject.common.widget.ListLiveData
import com.example.assignmentproject.data.remote.model.ArticlesItem
import com.example.assignmentproject.data.remote.repository.MainRepository
import com.example.assignmentproject.data.remote.response.ApiResponse
import com.example.assignmentproject.di.scope.ActivityScope
import com.example.assignmentproject.ui.base.viewmodel.BaseActivityViewModel
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class MainActivityViewModel @Inject constructor(private val mainRepository: MainRepository): BaseActivityViewModel() {
    lateinit var newsData: LiveData<List<ArticlesItem>>
    val isLoading = MutableLiveData(false)
    override fun handleCreate() {
        super.handleCreate()
        newsData = mainRepository.getAllPostsFromLocal()

        getArticles()
    }

     fun getArticles(){
         mainRepository.getArticles()
             .request({
                 isLoading.value = true
             },{
                 isLoading.value = false
             },{
                 mainRepository.clearNewsFromLocal()
                 it.let { it1 -> mainRepository.insert(it1) }
             },{

            }).disposeWith(compositeDisposable)
     }

}