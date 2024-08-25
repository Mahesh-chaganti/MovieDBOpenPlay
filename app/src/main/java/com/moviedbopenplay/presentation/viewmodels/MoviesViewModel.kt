package com.moviedbopenplay.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviedbopenplay.repository.MoviesDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import com.moviedbopenplay.Result
import com.moviedbopenplay.model.DetailedMovie
import com.moviedbopenplay.model.Titles
import com.moviedbopenplay.utils.Constants.BASE_URL

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesDBRepository): ViewModel() {
    private val _searchState = MutableStateFlow<Titles?>(Titles())
    val searchState = _searchState.asStateFlow()

    private val _movieState = MutableStateFlow<DetailedMovie?>(DetailedMovie())
    val movieState = _movieState.asStateFlow()

    private val _inProgress = MutableStateFlow<Boolean>(false)
    val inProgress = _inProgress.asStateFlow()

    private val _seearchString = MutableStateFlow<String>("")
    val searchString = _seearchString.asStateFlow()


    init {
        _seearchString.value = "$BASE_URL/titles?list=top_rated_english_250&sort=year.decr&info=mini_info"
        fetchSearch(searchString.value)
    }
    fun fetchSearch(searchString: String){
        _inProgress.value = true


        viewModelScope.launch(Dispatchers.IO) {

            try {
                when (val result = repository.getPopularMovies(searchString = searchString)) {
                    is Result.Success -> {
                        // Update UI with the fetched data
                        _searchState.value = result.data

                        Log.d("all articles", "fetchSearch: ${searchState.value}")



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
    fun fetchMovie(searchString: String){
        _inProgress.value = true


        viewModelScope.launch(Dispatchers.IO) {

            try {
                when (val result = repository.getMovie(searchString = searchString)) {
                    is Result.Success -> {
                        // Update UI with the fetched data
                        _movieState.value = result.data

                        Log.d("all articles", "fetchSearch: ${searchState.value}")



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
    fun onScroll(nextPageString: String){



        viewModelScope.launch(Dispatchers.IO) {
            _searchState.value = Titles()
           _seearchString.value = BASE_URL + nextPageString
            fetchSearch(searchString.value)
        }
    }
    fun onMovieClick(openScreen: (String) -> Unit, id: String,){
        viewModelScope.launch() {
            _movieState.value = DetailedMovie()
            openScreen("DetailedMovieScreen")
            val url = BASE_URL + "/titles/$id?info=base_info"
            fetchMovie(searchString = url)
        }

    }
}