package com.moviedbopenplay.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_tbl")
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = "",

    @ColumnInfo(name = "originalTitleText")
    val originalTitleText: String = "",

    @ColumnInfo(name = "plot")
    val plot: String = "",

    @ColumnInfo(name = "primaryImage")
    val primaryImage: String = "",

    @ColumnInfo(name = "ratingsSummary")
    val ratingsSummary: String = "",

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String = "",

    @ColumnInfo(name = "genres")
    val genres: String = "",

    @ColumnInfo(name = "runtime")
    val runtime: String = ""
)