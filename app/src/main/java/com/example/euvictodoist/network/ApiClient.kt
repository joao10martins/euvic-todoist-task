package com.example.euvictodoist.network

import com.example.euvictodoist.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {
    var retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(getOkHttpClient().build())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()

    fun getOkHttpClient() : OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(getLoggingCapableHttpClient())
        }
        return builder
    }

    private fun getLoggingCapableHttpClient(): HttpLoggingInterceptor {
        val mLogging = HttpLoggingInterceptor()
        mLogging.level = HttpLoggingInterceptor.Level.BODY
        return mLogging
    }

    fun getApiClient() : Retrofit {
        return retrofit
    }
}