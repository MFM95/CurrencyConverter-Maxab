package com.example.currencyconverter.core

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitServiceGenerator {

    fun <S> createService(
        serviceClass: Class<S>,
        baseURL: String
    ): S {
        val okHttpClient = getCommonOkHttpClient()

        val builder = Retrofit.Builder()

        val gsonBuilder = GsonBuilder()
            .enableComplexMapKeySerialization()
            .setLenient()

        val gson = gsonBuilder.create()

        builder.baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))

        builder.client(okHttpClient)

        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }



    private fun getCommonOkHttpClient(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)


        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)

        return httpClient.build()
    }

}