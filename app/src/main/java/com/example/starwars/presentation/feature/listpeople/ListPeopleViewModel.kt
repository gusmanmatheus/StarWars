package com.example.starwars.presentation.feature.listpeople

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.data.repository.PeoplesRepository
import com.example.starwars.presentation.model.People
import com.example.starwars.presentation.model.toListPeople
import com.example.starwars.retrofit.apiCollect
import kotlinx.coroutines.launch

class ListPeopleViewModel(
    private val peoplesRepository: PeoplesRepository,
) : ViewModel() {
    private val _nextPageLiveData = MutableLiveData("1")
    val nextPageLiveData: LiveData<String> = _nextPageLiveData
    private val _previousPageLiveData = MutableLiveData("1")
    val previousPageLiveData: LiveData<String> = _previousPageLiveData

    private val _actualPeopleListLiveData = MutableLiveData<List<People>>()
    val actualPeopleListLiveData: LiveData<List<People>> = _actualPeopleListLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private val _requestError = MutableLiveData<String>()
    val requestError: LiveData<String> = _requestError

    fun getPeoples(page: String) {
        viewModelScope.launch {
            peoplesRepository.getPeoples(page).apiCollect(
                onLoading = {
                    _loadingLiveData.value = true
                },
                onError = {
                    _loadingLiveData.value = false
                    _requestError.value = it.message.toString()
                },
                onSuccessful = { peoplePage ->
                    _loadingLiveData.value = false
                    _nextPageLiveData.value = peoplePage?.nextPage ?: "0"
                    _previousPageLiveData.value = peoplePage?.previousPage ?: "0"
                    _actualPeopleListLiveData.value = peoplePage?.peopleList?.toListPeople()
                }
            )
        }
    }
}