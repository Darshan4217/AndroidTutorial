package com.darshan.androidtutorial.di.modules

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.darshan.androidtutorial.BaseApplication
import com.darshan.androidtutorial.BuildConfig
import com.darshan.androidtutorial.data.Environment
import com.darshan.androidtutorial.data.MockInterceptor
import com.darshan.androidtutorial.di.scopes.ApplicationScope
import com.darshan.androidtutorial.news.api.NewsListServiceBase
import com.darshan.androidtutorial.utils.NetworkConnectionInterceptor
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
    fun provideNetworkConnectionInterceptor(context: Context) =
        NetworkConnectionInterceptor(context)

    @Provides
    fun provideMockInterceptor(context: Context) =
        MockInterceptor()

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor,
        mockInterceptor: MockInterceptor
    ): OkHttpClient {

        return with(OkHttpClient.Builder()) {
            connectTimeout(OKHTTP_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            readTimeout(OKHTTP_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            writeTimeout(OKHTTP_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.BASIC

            }
            addInterceptor(logging)
            if (Environment.MOCK == Environment.valueOf(BuildConfig.FLAVOR.toUpperCase())) {
                addInterceptor(mockInterceptor)
            }
            addInterceptor(networkConnectionInterceptor)
            build()

        }
    }

    @Provides
    @ApplicationScope
    fun provideRetrofitInstance(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
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


    @ApplicationScope
    @Provides
    fun provideNewsListServiceBase(retrofit: Retrofit): NewsListServiceBase =
        retrofit.create(NewsListServiceBase::class.java)


    companion object {
        private const val OKHTTP_TIMEOUT_SECONDS = 60L
        private const val BASE_URL = "https://newsapi.org/v2/"
    }
}