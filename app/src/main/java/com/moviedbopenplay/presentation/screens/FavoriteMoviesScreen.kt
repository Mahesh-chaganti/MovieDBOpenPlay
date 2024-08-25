package com.moviedbopenplay.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import coil.compose.AsyncImage
import com.moviedbopenplay.model.Favorite
import com.moviedbopenplay.presentation.viewmodels.FavoriteViewModel
import com.moviedbopenplay.presentation.viewmodels.MoviesViewModel


@Composable
fun FavoriteMoviesScreen(
    modifier: Modifier,
    viewModel: MoviesViewModel,
    openScreen: (String) -> Unit,
    favoriteViewModel: FavoriteViewModel
) {
    val favs = favoriteViewModel.favList.collectAsStateWithLifecycle().value
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(24.dp)) {
            itemsIndexed(favs) { index, item ->
        Box(
            Modifier
                .fillMaxWidth()
                .height(360.dp)

        )
        {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = item.primaryImage,
                contentDescription = "Movie",
                contentScale = ContentScale.FillBounds
            )






        }

        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = modifier
                ){
                TextWithBoldFirstWord(text = "Movie:  ${item.originalTitleText} ")

                TextWithBoldFirstWord(text = "Plot:  ${item.plot} ")
                TextWithBoldFirstWord(
                    text = "Genre: ${item.genres}"
                )
                TextWithBoldFirstWord(text = "Rating: ${item.ratingsSummary}")

                TextWithBoldFirstWord(
                    text = "Runtime: ${item.runtime}"
                )
                TextWithBoldFirstWord(text = "ReleaseDate: ${item.releaseDate}")
                TextWithBoldFirstWord(text = "Language: English")
            }
            HorizontalDivider(Modifier.fillMaxWidth())
        }


    }

//                Box(modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 18.dp, vertical = 8.dp)
//                    .clickable {
//                        item.id?.let {
//
//                        }
//
//
//                    }) {
//                    Row(
//                        modifier = Modifier.fillMaxSize(),
//                        horizontalArrangement = Arrangement.Start,
//                        verticalAlignment = Alignment.Top
//                    ) {
//                        AsyncImage(
//                            model = item.primaryImage,
//                            contentDescription = "Album Image",
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier
//                                .size(84.dp)
//                                .padding(4.dp)
//                        )
//                        Column(
//                            Modifier.fillMaxSize(),
//                            verticalArrangement = Arrangement.SpaceEvenly,
//                            horizontalAlignment = Alignment.Start
//                        ) {
//
//                            item.originalTitleText?.let {
//                                Text(
//
//                                    text = it,
//                                    fontSize = 28.sp,
//
//                                    color = Color.Black,
//                                    overflow = TextOverflow.Ellipsis
//                                )
//                            }
//                            Text(
//                                text = item.releaseDate.toString(),
//                                fontSize = 18.sp,
//
//                                color = Color.Gray,
//                                overflow = TextOverflow.Ellipsis
//                            )
//                        }
//                    }
//                }
            }
        }

}
