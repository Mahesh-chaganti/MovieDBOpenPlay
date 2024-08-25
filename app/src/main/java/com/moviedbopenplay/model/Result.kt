package com.moviedbopenplay.model

data class Result(
    val _id: String? = "",
    val id: String? ="",
    val originalTitleText: OriginalTitleText? = OriginalTitleText(),
    val primaryImage: PrimaryImage? = PrimaryImage(),
    val releaseDate: ReleaseDate? = ReleaseDate(),
    val releaseYear: ReleaseYear? = ReleaseYear(),
    val titleText: TitleText? = TitleText(),
    val titleType: TitleType? = TitleType()
)