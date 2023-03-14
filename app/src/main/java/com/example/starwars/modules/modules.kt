package com.example.starwars.modules

import com.example.starwars.BuildConfig.URL_API
import com.example.starwars.data.ApiRequest
import com.example.starwars.retrofit.FlowCallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val modules = module {
    single {
        val converter = get<GsonConverterFactory>()

        Retrofit.Builder()
            .addConverterFactory(converter)
            .baseUrl(URL_API)
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .build()
            .create(ApiRequest::class.java)
    }
}