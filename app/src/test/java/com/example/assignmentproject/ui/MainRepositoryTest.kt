package com.example.assignmentproject.ui

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentproject.BaseRepositoryTest
import com.example.assignmentproject.data.remote.model.ArticlesItem
import com.example.assignmentproject.data.remote.repository.MainRepository
import com.example.assignmentproject.data.remote.response.ApiResponse
import com.example.assignmentproject.ui.news.MainActivity
import com.example.assignmentproject.ui.news.MainActivityViewModel
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.SingleEmitter
import org.json.JSONObject
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class MainRepositoryTest : BaseRepositoryTest() {
    @Mock
    lateinit var mainRepository: MainRepository

    private lateinit var activity: MainActivity

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun setUp() {
        super.setUp()
        val activityController = Robolectric.buildActivity(MainActivity::class.java)
        activityController.create()
        activity = activityController.get()
        mainActivityViewModel =
            ViewModelProvider(
                this.activity,
                activityViewModelFactory
            )[MainActivityViewModel::class.java]
    }

    override fun isMockServerEnabled(): Boolean {
        return true
    }

    private fun getMockResponse(response: String): Single<ApiResponse<List<ArticlesItem>>> {
        val jsonObject = JSONObject(response)

        val apiResponse = ApiResponse<List<ArticlesItem>>()

        if (jsonObject.has("articles")) {
            val responseData = jsonObject.getJSONArray("articles")
            apiResponse.articles = Gson().fromJson(responseData.toString() , Array<ArticlesItem>::class.java).toList()
        }
        return Single.create { e: SingleEmitter<ApiResponse<List<ArticlesItem>>>? -> e?.onSuccess(apiResponse) }
    }


    @Test
    fun get_news_success() {
        Mockito.`when`(mainRepository.getArticles())
            .thenReturn(getMockResponse(getJson("news_articles.json")))
        val resultSingle = mainRepository.getArticles()
        val testObserver = resultSingle.test()
        testObserver.assertNoErrors()
        testObserver.assertValue{ it.articles != null}
    }

    @Test
    fun get_news_error() {
        Mockito.`when`(mainRepository.getArticles())
            .thenReturn(getMockResponse(getJson("news_error.json")))
        val resultSingle = mainRepository.getArticles()
        val testObserver = resultSingle.test()
        testObserver.assertNoErrors()
        testObserver.assertValue{ it.articles == null}
    }



}