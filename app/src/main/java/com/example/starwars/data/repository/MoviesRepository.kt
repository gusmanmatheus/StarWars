package com.example.starwars.data.repository

import com.example.starwars.data.MoviesPage
import com.example.starwars.retrofit.ApiResult
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(): Flow<ApiResult<MoviesPage>>
}