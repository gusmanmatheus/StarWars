package com.example.starwars.data.repository

import com.example.starwars.data.PeoplePage
import com.example.starwars.retrofit.ApiResult
import kotlinx.coroutines.flow.Flow

interface PeoplesRepository {
    fun getPeoples(page:String): Flow<ApiResult<PeoplePage>>
}