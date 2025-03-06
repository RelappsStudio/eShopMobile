package com.relapps.eshopmobile.productCatalog.utils

import com.relapps.eshopmobile.productCatalog.data.remote.CatalogApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:5222/"


    val catalogApi: CatalogApiService by lazy {

        val  interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val client =  OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CatalogApiService::class.java)
    }
}

