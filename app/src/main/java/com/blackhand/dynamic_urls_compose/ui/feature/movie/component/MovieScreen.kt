@file:OptIn(ExperimentalFoundationApi::class)

package com.blackhand.dynamic_urls_compose.ui.feature.movie.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.blackhand.dynamic_urls_compose.R
import com.blackhand.dynamic_urls_compose.domain.model.movie.MovieResultModel
import com.blackhand.dynamic_urls_compose.ui.common_ui.ErrorMessage
import com.blackhand.dynamic_urls_compose.ui.common_ui.LoadingProgressBar
import com.blackhand.dynamic_urls_compose.ui.common_ui.RemoteImage
import com.blackhand.dynamic_urls_compose.ui.feature.movie.viewmodel.MovieViewModel

@Composable
fun MovieScreen(moviesViewModel: MovieViewModel) {
    LaunchedEffect(key1 = true) {
        moviesViewModel.getMoviesList()
    }
    val movies = moviesViewModel.movies.collectAsStateWithLifecycle()
    val moviesList = movies.value.moviesList
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { moviesList?.size ?: 0 })
    if (movies.value.isLoading == true) {
        LoadingProgressBar()
    } else if (movies.value.errorMessage != null) {
        ErrorMessage(error = movies.value.errorMessage)
    } else {
        MoviesPager(pagerState, moviesList)
    }

}

@Composable
private fun MoviesPager(pagerState: PagerState, movies: List<MovieResultModel>?) {
    VerticalPager(state = pagerState) { page ->
        Column(modifier = Modifier.background(color = Color.Black)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                RemoteImage(
                    data = movies?.get(page)?.posterPath.toString(),
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(0.dp))
                )
                Image(
                    painter = painterResource(id = R.drawable.play_button),
                    contentDescription = "", modifier = Modifier.align(Alignment.Center).size(75.dp)
                )
            }
        }
    }
}