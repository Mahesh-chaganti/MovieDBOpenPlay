package com.moviedbopenplay.di

import android.content.Context
import androidx.room.Room
import com.moviedbopenplay.data.MovieDao
import com.moviedbopenplay.data.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Singleton
    @Provides
    fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val cacheSize = (5 * 1024 * 1024).toLong()

        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(1, TimeUnit.MINUTES) // write timeout
            .readTimeout(1, TimeUnit.MINUTES)
            .cache(Cache(File(context.cacheDir,"http_cache"),cacheSize))
            .addInterceptor(logging)
            .build()

    }
    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: MovieDatabase): MovieDao
            = weatherDatabase.MovieDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): MovieDatabase
            = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        "movie_database")
        .fallbackToDestructiveMigration()
        .build()

}