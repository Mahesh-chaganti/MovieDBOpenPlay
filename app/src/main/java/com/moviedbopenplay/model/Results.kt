package com.moviedbopenplay.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


data class Results(

    val _id: String? = "",
    val episodes: String? = "",
    val genres: Genres? = Genres(),
    val id: String? ="",
    val meterRanking: MeterRanking?=MeterRanking(),
    val originalTitleText: OriginalTitleText? =OriginalTitleText(),
    val plot: Plot? = Plot(),
    val primaryImage: PrimaryImage? = PrimaryImage(),
    val ratingsSummary: RatingsSummary? = RatingsSummary(),
    val releaseDate: ReleaseDate? = ReleaseDate(),
    val releaseYear: ReleaseYear? = ReleaseYear(),
    val runtime: Runtime? = Runtime(),
    val series: String? = "",
    val titleText: TitleText? = TitleText(),
    val titleType: TitleType? = TitleType()
)