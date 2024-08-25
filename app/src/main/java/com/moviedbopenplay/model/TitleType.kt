package com.moviedbopenplay.model

data class TitleType(
    val __typename: String?= "",
    val id: String? ="",
    val isEpisode: Boolean= false,
    val isSeries: Boolean = false,
    val text: String? =""
)