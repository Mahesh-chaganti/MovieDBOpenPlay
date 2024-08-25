package com.moviedbopenplay.repository

import android.util.Log
import com.moviedbopenplay.network.MoviesAPI
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import com.moviedbopenplay.Result
import com.moviedbopenplay.model.DetailedMovie
import com.moviedbopenplay.model.SearchMovies
import com.moviedbopenplay.model.Titles
import com.moviedbopenplay.utils.Constants.API_KEY
import com.moviedbopenplay.utils.Constants.API_VALUE
import com.moviedbopenplay.utils.Constants.HOST_KEY
import com.moviedbopenplay.utils.Constants.HOST_VALUE
import okhttp3.Response


class MoviesDBRepository @Inject constructor(private val client: OkHttpClient): MoviesAPI{
    override suspend fun getPopularMovies(searchString: String): Result<Titles> {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val jsonAdapter: JsonAdapter<Titles> = moshi.adapter(Titles::class.java)

        val request = Request.Builder()
            .url(searchString)
            .get()
            .addHeader("x-rapidapi-key", "942184027emsh4351ce776b23ab6p102cd4jsn732fa7ba11ca")
            .addHeader("x-rapidapi-host", "moviesdatabase.p.rapidapi.com")
            .build()


        return try {
            val response = client.newCall(request).execute()
            checkResponse(response)

            val json = response.body?.string()

            if (response.isSuccessful) {
                val data = json?.let { jsonAdapter.fromJson(it) }!!
                Result.Success(data)
                // Parse response body and return Result.Success
            } else {
                Result.ApiError(message = json.toString(),code = response.code)

                // Handle error and return Result.Error
            }
        } catch (e: Exception) {
            Result.Error(exception = e)
            // Handle exception and return Result.Error
        }
    }

    override suspend fun getMovie(searchString: String): Result<DetailedMovie> {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val jsonAdapter: JsonAdapter<DetailedMovie> = moshi.adapter(DetailedMovie::class.java)

        val request = Request.Builder()
            .url(searchString)
            .get()
            .addHeader(API_KEY, API_VALUE)
            .addHeader(HOST_KEY, HOST_VALUE)
            .build()


        return try {
            val response = client.newCall(request).execute()
            checkResponse(response)

            val json = response.body?.string()

            if (response.isSuccessful) {
                val data = json?.let { jsonAdapter.fromJson(it) }!!
                Result.Success(data)
                // Parse response body and return Result.Success
            } else {
                Result.ApiError(message = json.toString(),code = response.code)

                // Handle error and return Result.Error
            }
        } catch (e: Exception) {
            Result.Error(exception = e)
            // Handle exception and return Result.Error
        }
    }
    override suspend fun queryMovie(searchString: String): Result<SearchMovies> {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val jsonAdapter: JsonAdapter<SearchMovies> = moshi.adapter(SearchMovies::class.java)

        val request = Request.Builder()
            .url(searchString)
            .get()
            .addHeader(API_KEY, API_VALUE)
            .addHeader(HOST_KEY, HOST_VALUE)
            .build()


        return try {
            val response = client.newCall(request).execute()
            checkResponse(response)

            val json = response.body?.string()

            if (response.isSuccessful) {
                val data = json?.let { jsonAdapter.fromJson(it) }!!
                Result.Success(data)
                // Parse response body and return Result.Success
            } else {
                Result.ApiError(message = json.toString(),code = response.code)

                // Handle error and return Result.Error
            }
        } catch (e: Exception) {
            Result.Error(exception = e)
            // Handle exception and return Result.Error
        }
    }


    private fun checkResponse(response: Response) {
        if(response.networkResponse == null && response.cacheResponse != null){
            Log.d("N/W or Cache", "checkResponse: Came from Cache")
        }
        else if(response.cacheResponse == null && response.networkResponse != null){
            Log.d("N/W or Cache", "checkResponse: Came from Network")
        }
        else if( response.cacheResponse != null && response.networkResponse != null){
            Log.d("N/W or Cache", "checkResponse: Conditional GET ")
        }
    }

}