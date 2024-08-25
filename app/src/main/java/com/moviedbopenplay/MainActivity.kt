package com.moviedbopenplay

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.moviedbopenplay.model.bottomNavItems
import com.moviedbopenplay.navigation.NavigationScreen
import com.moviedbopenplay.presentation.screens.PopularMoviesScreen
import com.moviedbopenplay.presentation.viewmodels.FavoriteViewModel
import com.moviedbopenplay.presentation.viewmodels.MoviesViewModel
import com.moviedbopenplay.presentation.viewmodels.SearchScreenViewModel
import com.moviedbopenplay.ui.theme.MovieDBOpenPlayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val moviesViewModel by viewModels<MoviesViewModel>()
    private val searchScreenViewModel by viewModels<SearchScreenViewModel>()
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            MovieDBOpenPlayTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigation(backgroundColor = Color.Black) {

                            bottomNavItems.forEach { screen ->
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = screen.icon),
                                            contentDescription = screen.title,
                                            tint = Color.White
                                        )
                                    },
                                    label = { Text(text = screen.route) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {

                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }

                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    selectedContentColor = Color(0xFFFF3D00),
                                    alwaysShowLabel = false
                                )
                            }
                        }
                    },
                ) { innerPadding ->
                    NavigationScreen(
                        modifier = Modifier.padding(innerPadding), navController = navController,
                        viewModel = moviesViewModel,
                        searchScreenViewModel = searchScreenViewModel,
                        favoriteViewModel = favoriteViewModel
                    )
                }

                if (!isInternetAvailable(LocalContext.current.applicationContext)) {
                if(currentDestination?.route == "Movies" || currentDestination?.route == "Search"  ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(modifier = Modifier.align(Alignment.Center)) {


                            Text(
                                text = "No internet connection",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 36.sp,
                                color = Color.Black
                            )
                            Text(text = "Go to Favorites Tab", color = Color.DarkGray)
                        }

                    }
                }

                }
            }
        }
    }
}

fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val networkCapabilities =
        connectivityManager.getNetworkCapabilities(network) ?: return false

    return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieDBOpenPlayTheme {
        Greeting("Android")
    }
}