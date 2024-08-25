package com.moviedbopenplay

import com.moviedbopenplay.model.OriginalTitleText
import com.moviedbopenplay.model.PrimaryImage
import com.moviedbopenplay.model.ReleaseDate
import com.moviedbopenplay.model.ReleaseYear
import com.moviedbopenplay.model.TitleText
import com.moviedbopenplay.model.TitleType



sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class ApiError(val message: String, val code: Int) : Result<Nothing>()
    data class Error(val exception: Exception) : Result<Nothing>()
}