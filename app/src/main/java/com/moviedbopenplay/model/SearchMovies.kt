package com.moviedbopenplay.model

data class SearchMovies(
    val entries: Int? = 0,
    val next: String? = "",
    val page: Int? = 0,
    var results: List<Results> = listOf()
)