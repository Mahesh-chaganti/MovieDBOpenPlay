package com.moviedbopenplay.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.moviedbopenplay.R
import com.moviedbopenplay.model.Favorite
import com.moviedbopenplay.presentation.viewmodels.FavoriteViewModel
import com.moviedbopenplay.presentation.viewmodels.MoviesViewModel
import com.moviedbopenplay.ui.composables.OrangeProgressIndicator


@Composable
fun DetailedMovieScreen(
    modifier: Modifier,
    viewModel: MoviesViewModel,
    goBack: () -> Unit,
    favoriteViewModel: FavoriteViewModel
) {
    val movie = viewModel.movieState.collectAsStateWithLifecycle().value
    val inProgress = viewModel.inProgress.collectAsState().value
    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }
    Surface(modifier = modifier.fillMaxSize(), color = Color.White) {

    }
    if (movie?.results?.genres?.genres?.size != 0) {
        Column(
            modifier = modifier
                .fillMaxSize()
        )
        {


            Box(
                Modifier
                    .fillMaxWidth()
                    .height(360.dp)
            )
            {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    model = movie?.results?.primaryImage?.url,
                    contentDescription = "Movie",
                    contentScale = ContentScale.FillBounds
                )
                IconButton(
                    onClick = { goBack.invoke() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = Color.Red,
                        modifier = Modifier.size(36.dp)

                    )
                }
                androidx.compose.material3.IconButton(
                    onClick = {
                        isFavorite = !isFavorite
                        if (movie != null) {
                            favoriteViewModel.insertFavorite(
                                favorite = Favorite(
                                    id = movie.results?.id.toString(),
                                    originalTitleText = movie?.results?.originalTitleText?.text
                                        ?: "",
                                    plot = movie?.results?.plot?.plotText?.plainText ?: "",
                                    genres = movie?.results?.genres?.genres?.joinToString(", ") { it.text.toString() }
                                        ?: "",
                                    primaryImage = movie?.results?.primaryImage?.url ?: "",
                                    ratingsSummary = movie?.results?.ratingsSummary?.aggregateRating.toString(),
                                    runtime = "${movie?.results?.runtime?.seconds?.div(3600)}hr " +
                                            "${
                                                (movie?.results?.runtime?.seconds?.rem(3600)
                                                    ?.div(60))
                                            }min",
                                    releaseDate = " ${movie?.results?.releaseDate?.day} - ${movie?.results?.releaseDate?.month} - ${movie?.results?.releaseDate?.year}"
                                )
                            )
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector =

                        if (isFavorite) Icons.Default.Favorite
                        else
                            Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.White
                    )
                }


            }
            ElevatedCard(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    TextWithBoldFirstWord(text = "Movie:  ${movie?.results?.originalTitleText?.text} ")

                    TextWithBoldFirstWord(text = "Plot:  ${movie?.results?.plot?.plotText?.plainText} ")
                    val allGenres =
                        movie?.results?.genres?.genres?.joinToString(", ") { it.text.toString() }
                    TextWithBoldFirstWord(
                        text = "Genre: ${allGenres}"
                    )
                    TextWithBoldFirstWord(text = "Rating: ${movie?.results?.ratingsSummary?.aggregateRating}")

                    TextWithBoldFirstWord(
                        text = "Runtime: ${movie?.results?.runtime?.seconds?.div(3600)}hr " +
                                "${(movie?.results?.runtime?.seconds?.rem(3600)?.div(60))}min"
                    )
                    TextWithBoldFirstWord(text = "ReleaseDate: ${movie?.results?.releaseDate?.day} - ${movie?.results?.releaseDate?.month} - ${movie?.results?.releaseDate?.year}")
                    TextWithBoldFirstWord(text = "Language: English")
                    TextWithBoldFirstWord(text = "CurrentRank: ${movie?.results?.meterRanking?.currentRank}")
                }
            }


        }

    }
    if (inProgress) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                OrangeProgressIndicator()
            }


        }
    }

}

@Composable
fun TextWithBoldFirstWord(text: String) {
    val words = text.split(" ", limit = 2)
    if (words.isNotEmpty()) {
        androidx.compose.material3.Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.ExtraBold, color = Color.Red,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                ) {
                    append(words[0]) // First word bold and colored
                }
                if (words.size > 1) {
                    append(" ${words[1]}") // Append the rest of the text
                }
            },
            color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
