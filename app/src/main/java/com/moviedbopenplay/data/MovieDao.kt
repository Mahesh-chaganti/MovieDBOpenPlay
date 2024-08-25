package com.moviedbopenplay.data


import androidx.room.*
import com.moviedbopenplay.model.Favorite
import com.moviedbopenplay.model.Results

import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * from fav_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * from fav_tbl where id =:id")
    suspend fun getFavById(id: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query("DELETE from fav_tbl")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)




}