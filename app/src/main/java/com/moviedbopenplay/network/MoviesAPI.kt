package com.moviedbopenplay.network

import com.moviedbopenplay.Result
import com.moviedbopenplay.model.DetailedMovie
import com.moviedbopenplay.model.SearchMovies
import com.moviedbopenplay.model.Titles


interface MoviesAPI {

    suspend fun getPopularMovies(searchString: String): Result<Titles>
    suspend fun getMovie(searchString: String): Result<DetailedMovie>
    suspend fun queryMovie(searchString: String): Result<SearchMovies>

}
