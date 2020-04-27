package com.darshan.androidtutorial.di.modules

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.darshan.androidtutorial.BaseApplication
import com.darshan.androidtutorial.di.scopes.ApplicationScope
import com.darshan.androidtutorial.news.api.NewsListServiceBase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(): OkHttpClient {

        return with(OkHttpClient.Builder()) {
            connectTimeout(OKHTTP_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            readTimeout(OKHTTP_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            writeTimeout(OKHTTP_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(logging)
            build()

        }
    }

    @Provides
    @ApplicationScope
    fun provideRetrofitInstance(httpClient: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://newsapi.org/v2")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideGlideInstance(
        application: BaseApplication,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }


  /*  @ApplicationScope
    @Provides
    fun provideNewsListServiceBase(retrofit: Retrofit): NewsListServiceBase =
        retrofit.create(NewsListServiceBase::class.java)*/


    companion object {
        private const val OKHTTP_TIMEOUT_SECONDS = 60L
    }
}