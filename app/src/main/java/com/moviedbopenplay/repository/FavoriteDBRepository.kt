package com.moviedbopenplay.repository

import com.moviedbopenplay.data.MovieDao
import com.moviedbopenplay.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteDBRepository @Inject constructor(private val movieDao: MovieDao) {

    fun getFavorites(): Flow<List<Favorite>> = movieDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite) = movieDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = movieDao.updateFavorite(favorite)
    suspend fun deleteAllFavorites() = movieDao.deleteAllFavorites()
    suspend fun deleteFavorite(favorite: Favorite) = movieDao.deleteFavorite(favorite)
    suspend fun getFavById(city: String): Favorite = movieDao.getFavById(city)





}