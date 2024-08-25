package com.moviedbopenplay.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moviedbopenplay.model.DetailedMovie
import com.moviedbopenplay.model.Favorite
import com.moviedbopenplay.model.Results

@Database(entities = [Favorite::class], version = 2, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun MovieDao(): MovieDao
}



