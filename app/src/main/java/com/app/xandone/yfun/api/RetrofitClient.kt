package com.app.xandone.yfun.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * author: xandone
 * created on: 2017/9/8 9:19
 */
object RetrofitClient {

    private val mRetrofit by lazy { createRetrofit() }

    val gClient by lazy { createRetrofit(ApiService::class.java) }

    fun <T> createRetrofit(serviceClass: Class<T>): T {
        return mRetrofit.create(serviceClass)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                //Okhttp
                .client(initokhttp())
                //RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //字符串
                .addConverterFactory(ScalarsConverterFactory.create())
                //Xml
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
    }

    private fun initokhttp(): OkHttpClient {
        var builder = OkHttpClient.Builder()
        if (ApiConstants.LOG_FLAG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        builder.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                    // 设置请求头，ApiConstants.TOKEN请求header头也会修改
                    .header("Content-Type", ApiConstants.TOKEN)
                    .method(original.method(), original.body())
                    .build()
            return@addInterceptor chain.proceed(request)
        }

        return builder.build()
    }
}
