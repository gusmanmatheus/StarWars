package com.example.starwars.presentation.feature.listmovie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwars.data.repository.MoviesRepository
import com.example.starwars.presentation.model.Movie
import com.example.starwars.presentation.model.toMovieList
import com.example.starwars.retrofit.apiCollect
import kotlinx.coroutines.launch

class ListMovieViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val _nextPageLiveData = MutableLiveData("1")
    val nextPageLiveData: LiveData<String> = _nextPageLiveData
    private val _previousPageLiveData = MutableLiveData("1")
    val previousPageLiveData: LiveData<String> = _previousPageLiveData

    private val _actualMovieListLiveData = MutableLiveData<List<Movie>>()
    val actualMovieListLiveData: LiveData<List<Movie>> = _actualMovieListLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private val _requestError = MutableLiveData<String>()
    val requestError: LiveData<String> = _requestError

    fun getMovies(page: String) {
        viewModelScope.launch {
            moviesRepository.getMovies(page).apiCollect(
                onLoading = {
                    _loadingLiveData.value = true
                },
                onError = {
                    _loadingLiveData.value = false
                    _requestError.value = it.message.toString()
                },
                onSuccessful = { moviePage ->
                    _loadingLiveData.value = false
                    _nextPageLiveData.value = moviePage?.nextPage ?: "0"
                    _previousPageLiveData.value = moviePage?.previousPage ?: "0"
                    _actualMovieListLiveData.value = moviePage?.movieList?.toMovieList()
                }
            )
        }
    }
}