package com.example.starwars.data.datasource

import com.example.starwars.data.ApiRequest
import com.example.starwars.data.model.MoviesPage
import com.example.starwars.retrofit.ApiResult
import kotlinx.coroutines.flow.Flow

class MoviesDatasourceImpl(private val apiRequest: ApiRequest) : MoviesDatasource {
    override fun getMovies(): Flow<ApiResult<MoviesPage>> {
        return apiRequest.getMovies()
    }
}