package com.example.starwars.data.datasource

import com.example.starwars.data.PeoplePage
import com.example.starwars.retrofit.ApiResult
import kotlinx.coroutines.flow.Flow

interface PeoplesDatasource {
    fun getPeoples(page:String) : Flow<ApiResult<PeoplePage>>
}