package com.moviedbopenplay.model

import com.moviedbopenplay.Result

data class Titles(
    val entries: Int? = 0,
    val next: String? = "",
    val page: Int? = 0,
    var results: List<com.moviedbopenplay.model.Result> = listOf()
)