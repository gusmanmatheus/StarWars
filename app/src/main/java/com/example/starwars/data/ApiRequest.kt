package com.example.starwars.data

import com.example.starwars.data.model.MoviesPage
import com.example.starwars.data.model.PeoplePage
import com.example.starwars.retrofit.ApiResult
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiRequest {
    @GET("/films/")
    fun getMovies(): Flow<ApiResult<MoviesPage>>

    @GET("/people/")
    fun getPeoples(page: String): Flow<ApiResult<PeoplePage>>
}