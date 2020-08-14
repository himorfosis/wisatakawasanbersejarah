package com.kempoamikom.historyapp.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class AppNetwork {

    companion object {

//        val URL = "https://pembelajaransejarah.000webhostapp.com"
        val URL = "https://wisatakawasanbersejarah.com"
//        val URL = "http://192.190.0.101/wisatakawasanbersejarah"
        val API = URL + "/"
        val URL_IMAGE = "${API}uploaded/"
        val URL_FILE = "${API}uploaded/sumber/"

        private val retrofitBuilder = Retrofit.Builder()

        fun <T> buildService(service: Class<T>) : T {

            val client = OkHttpClient.Builder()
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(this)
            }
            client.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("Accept", "application/json;charset=UTF-8")
                    .build()
                chain.proceed(request)
            }

            client.connectTimeout(2, TimeUnit.MINUTES)
            client.readTimeout(2, TimeUnit.MINUTES)

            val gson = GsonBuilder()
                .setLenient()
                .create()

            retrofitBuilder.baseUrl(API)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            return retrofitBuilder.build().create(service)

        }

        fun <T> createService(service: Class<T>): T {
            return retrofitBuilder.build().create(service)
        }

    }

}