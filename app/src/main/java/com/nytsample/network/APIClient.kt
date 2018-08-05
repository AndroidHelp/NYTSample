package com.nytsample.network

import com.google.gson.Gson
import com.nytsample.utils.AppUtils.Companion.BaseURL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Hemant Katariya on 1/8/2018.
 */
public class APIClient {

    companion object {
        private var retrofit: Retrofit? = null

        fun create(): APIMethods {

            val okHttpClient = OkHttpClient.Builder()

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            okHttpClient.addInterceptor(httpLoggingInterceptor)
            okHttpClient.connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BaseURL)
                        .client(okHttpClient.build())
                        .addConverterFactory(GsonConverterFactory.create(Gson()))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build()
            }

            return retrofit!!.create(APIMethods::class.java)

        }
    }

}