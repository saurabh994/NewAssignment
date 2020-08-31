package com.example.assignmentproject.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class TestNetworkModule(private val baseUrl: String) {
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .build()

    companion object {
        const val TIMEOUT_REQUEST: Long = 1000
    }
}