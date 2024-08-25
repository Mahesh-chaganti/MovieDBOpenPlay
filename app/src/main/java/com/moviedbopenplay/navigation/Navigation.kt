package com.moviedbopenplay.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.moviedbopenplay.model.BottomNav
import com.moviedbopenplay.model.DetailedMovie
import com.moviedbopenplay.presentation.screens.DetailedMovieScreen
import com.moviedbopenplay.presentation.screens.FavoriteMoviesScreen
import com.moviedbopenplay.presentation.screens.PopularMoviesScreen
import com.moviedbopenplay.presentation.screens.SearchScreen
import com.moviedbopenplay.presentation.viewmodels.FavoriteViewModel
import com.moviedbopenplay.presentation.viewmodels.MoviesViewModel
import com.moviedbopenplay.presentation.viewmodels.SearchScreenViewModel

@Composable
fun NavigationScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MoviesViewModel,
    searchScreenViewModel: SearchScreenViewModel,
    favoriteViewModel: FavoriteViewModel
) {
    fun navigateTo(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun onBack() {
        navController.popBackStack()
    }
    NavHost(navController = navController, startDestination = BottomNav.Movies.route) {
        composable(BottomNav.Movies.route) {
            PopularMoviesScreen(
                modifier = modifier,
                viewModel = viewModel,
                openScreen = { route -> navigateTo(route) })
        }
        composable("DetailedMovieScreen") {
            DetailedMovieScreen(
                modifier = modifier,
                viewModel = viewModel,
                goBack = { onBack() },
                favoriteViewModel = favoriteViewModel
            )
        }
        composable(BottomNav.Search.route) {
            SearchScreen(modifier = modifier,
                searchScreenViewModel = searchScreenViewModel,
                viewModel = viewModel,
                openScreen = { route -> navigateTo(route) }
            )
        }
        composable(BottomNav.Favorites.route) {
            FavoriteMoviesScreen(
                modifier = modifier,
                viewModel = viewModel,
                openScreen = { route -> navigateTo(route) },
                favoriteViewModel = favoriteViewModel
            )
        }

    }
}
