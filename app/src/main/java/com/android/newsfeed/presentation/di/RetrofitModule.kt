package com.android.newsfeed.presentation.di

import android.content.Context
import com.android.newsfeed.BuildConfig
import com.android.newsfeed.data.api.FeedAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

private const val CACHE_FILE_SIZE: Long = (30 * 1000 * 1000).toLong() // 30 Mib

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(callFactory: Call.Factory): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/")
            .callFactory(callFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideFeedAPIService(retrofit: Retrofit): FeedAPIService {
        return retrofit.create(FeedAPIService::class.java)
    }

    @Singleton
    @Provides
    fun provideCacheFile(@ApplicationContext context: Context) = File(context.filesDir, "my_own_created_cache").apply {
        if (!this.exists())
            mkdirs()
    }

    @Singleton
    @Provides
    fun provideCache(cacheFile: File) = Cache(cacheFile, CACHE_FILE_SIZE)

    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .cache(cache)
        .build()

    private val interceptor: Interceptor
        get() = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
}