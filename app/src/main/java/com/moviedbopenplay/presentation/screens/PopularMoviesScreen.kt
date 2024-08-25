package com.moviedbopenplay.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.moviedbopenplay.R
import com.moviedbopenplay.model.Result
import com.moviedbopenplay.presentation.viewmodels.MoviesViewModel
import com.moviedbopenplay.ui.composables.OrangeProgressIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMoviesScreen(modifier: Modifier = Modifier, viewModel: MoviesViewModel, openScreen: (String) -> Unit,
                        ) {
    val movies = viewModel.searchState.collectAsStateWithLifecycle().value
    val inProgress = viewModel.inProgress.collectAsState().value

    val buffer = 1
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    var allMovies by remember { mutableStateOf<List<Result>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }



    val isScrolledBeyondLastItem by remember {

        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - buffer




        }
    }


    LaunchedEffect(movies) {
        if (movies?.results?.isNotEmpty() == true) {
            allMovies = allMovies.plus(movies.results)
        }
    }
        LaunchedEffect(isScrolledBeyondLastItem ) {
            if (isScrolledBeyondLastItem && !isLoading) {
                isLoading = true
                coroutineScope.launch {


                    movies?.next?.let { viewModel.onScroll(it) }
                    delay(5000L)




                    isLoading = false
                }
            }
        }

    if (movies != null) {
        Surface(modifier = modifier.fillMaxSize(), color = Color.White) {
            LazyColumn(modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(24.dp),
                state = listState
            ) {
                item{
                   Text(text ="Top rated movies", fontSize = 36.sp, color = Color.Red, fontWeight = FontWeight.ExtraBold)
                }


                if(allMovies.isNotEmpty()){
                itemsIndexed(allMovies.toList()) { index, movie ->

                    ElevatedCard(onClick = {
                        movie.id?.let { viewModel.onMovieClick(openScreen = openScreen, id = it)}
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.elevatedCardColors(containerColor = Color.DarkGray),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                model = movie.primaryImage?.url,
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds
                            )



                                Column(modifier = Modifier.align(Alignment.BottomStart)) {


                                    movie.originalTitleText?.text?.let {
                                        Text(
                                            text = it,
                                            modifier = Modifier
                                                .padding(horizontal = 16.dp),
                                            color = Color.White,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    Text(
                                        text = movie.releaseYear?.year.toString(),
                                        modifier = Modifier
                                            .padding(start = 16.dp, bottom = 16.dp),
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }




                        }

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                if (isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
                }
            if (inProgress && !isScrolledBeyondLastItem) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        OrangeProgressIndicator()
                    }


                }
            }
        }
    }

}

