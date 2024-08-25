package com.moviedbopenplay.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import com.moviedbopenplay.R

sealed class BottomNav(val route: String, val title: String, val icon: Int) {
    object Movies : BottomNav(route = "Movies", title = "Movies", icon = R.drawable.movies)
    object Search : BottomNav("Search", "Search",R.drawable.search,)
    object Favorites : BottomNav("Favorites", "Favorites",R.drawable.favorites)


}

val bottomNavItems = listOf(
    BottomNav.Movies,
    BottomNav.Search,
    BottomNav.Favorites
)