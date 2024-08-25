package com.moviedbopenplay.presentation.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.moviedbopenplay.R
import com.moviedbopenplay.presentation.viewmodels.MoviesViewModel
import com.moviedbopenplay.presentation.viewmodels.SearchScreenViewModel
import com.moviedbopenplay.ui.composables.OrangeProgressIndicator

@Composable
fun SearchScreen(modifier: Modifier = Modifier,
                 openScreen:(String) -> Unit,
searchScreenViewModel : SearchScreenViewModel,
                 viewModel: MoviesViewModel
) {
    var text by remember { mutableStateOf("") }
    val movies = searchScreenViewModel.queryMoviesState.collectAsStateWithLifecycle().value
    val inProgress = searchScreenViewModel.inProgress.collectAsState().value
    Surface(modifier = modifier.fillMaxSize(), color = Color.White) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(24.dp)) {


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                value = text,
                onValueChange = {
                    text = it
                },
                label = { androidx.compose.material3.Text("Search any movie") },
                placeholder = {},
                textStyle = TextStyle(fontSize = 18.sp),
                shape = RoundedCornerShape(18.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    focusedLabelColor = Color(0xFFFF3D00),
                    focusedBorderColor = Color(0xFFFF3D00),
                    unfocusedContainerColor = Color.DarkGray
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    searchScreenViewModel.fetchSearch(text)

                }),
                trailingIcon = {
                    IconButton(onClick = {
                        searchScreenViewModel.fetchSearch(text)

                    }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                }

            )
            if (movies?.results?.isNotEmpty() == true) {
                LazyColumn(Modifier.fillMaxSize()) {
                    itemsIndexed(movies.results) { index, item ->
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp, vertical = 8.dp)
                            .clickable {
                                item.id?.let {
                                    viewModel.onMovieClick(
                                        openScreen = openScreen,
                                        id = it
                                    )
                                }


                            }) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.Top
                            ) {
                                AsyncImage(
                                    model = item.primaryImage?.url,
                                    contentDescription = "Album Image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(84.dp)
                                        .padding(4.dp)
                                )
                                Column(
                                    Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceEvenly,
                                    horizontalAlignment = Alignment.Start
                                ) {

                                    item.originalTitleText?.text?.let {
                                        Text(

                                            text = it,
                                            fontSize = 28.sp,

                                            color = Color.Black,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    Text(
                                        text = item.releaseYear?.year.toString(),
                                        fontSize = 18.sp,

                                        color = Color.Gray,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
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
}

@Preview
@Composable
private fun Comprev() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {

    }
    LazyColumn {
        items(20){
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 8.dp)
                .clickable {


                }) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.kobe),
                        contentDescription = "Album Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(84.dp)
                            .padding(4.dp)
                    )
                    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.Start) {

                        Text(

                            text = "Title of the movie",
                            fontSize = 28.sp,

                            color = Color.Black,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "hello . " ,
                            fontSize = 18.sp,

                            color = Color.Gray,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}