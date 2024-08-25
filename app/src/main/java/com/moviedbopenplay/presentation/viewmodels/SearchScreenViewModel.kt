package com.moviedbopenplay.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviedbopenplay.Result
import com.moviedbopenplay.model.Results
import com.moviedbopenplay.model.SearchMovies
import com.moviedbopenplay.repository.MoviesDBRepository
import com.moviedbopenplay.utils.Constants.BASE_URL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val repository: MoviesDBRepository): ViewModel() {
    private val _queryMoviesState = MutableStateFlow<SearchMovies?>(SearchMovies())
    val queryMoviesState = _queryMoviesState.asStateFlow()

    private val _inProgress = MutableStateFlow<Boolean>(false)
    val inProgress = _inProgress.asStateFlow()

    private val _queriedMovieState = MutableStateFlow<Results?>(Results())
    val queriedMovieState = _queriedMovieState.asStateFlow()

    fun fetchSearch(searchString: String){
        _inProgress.value = true
        val searchUrl = BASE_URL + "/titles/search/title/$searchString?exact=false&info=base_info&sort=year.incr&titleType=movie"

        viewModelScope.launch(Dispatchers.IO) {

            try {
                when (val result = repository.queryMovie(searchString = searchUrl)) {
                    is Result.Success -> {
                        // Update UI with the fetched data
                        _queryMoviesState.value = result.data

                        Log.d("all articles", "fetchSearch: ${_queryMoviesState.value}")



                    }
                    is Result.Error -> {
                        // Handle the error, e.g., show an error message
                        Log.d("Error!!", "fetchQuestions: ${result.exception.message}")
                    }
                    is Result.ApiError -> {
                        Log.d("API Error", "fetchQuestions: ${result.message} with code: ${result.code} ")
                    }
                }





                ProgressIndicator()
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }

    fun ProgressIndicator() {
        viewModelScope.launch {

            _inProgress.value = false


        }
    }


}
