package com.ali.starzplay.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.ali.starzplay.R
import com.ali.starzplay.core.domain.model.MediaItem
import com.ali.starzplay.ui.screen.destinations.DetailScreenDestination
import com.ali.starzplay.ui.theme.StarzPlayTheme
import com.ali.starzplay.ui.viewmodel.MainViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

// NOTE: In larger projects, prefer using an event-driven approach by sending UI events
// (e.g., sealed class events) to a single `onEvent()` handler in the ViewModel.
// For simplicity and because this is a small interview project, we're directly calling the ViewModel function here

@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    MainScreenContent(
        groupedItems = uiState.groupedItems,
        isLoading = uiState.isLoading,
        error = uiState.error,
        onSearch = viewModel::onSearch,
        onItemClick = { item ->
            navigator.navigate(DetailScreenDestination(item = item))
        }
    )
}

@Composable
fun MainScreenContent(
    groupedItems: Map<String, List<MediaItem>>,
    isLoading: Boolean,
    error: String?,
    onSearch: (String) -> Unit,
    onItemClick: (MediaItem) -> Unit
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val isTablet = maxWidth > 600.dp
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .then(
                    if (isTablet) {
                        Modifier
                            .requiredWidthIn(max = 700.dp)
                    } else Modifier
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    onSearch(it.text)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp , vertical = 18.dp),
                decorationBox = { innerTextField ->
                    Box(
                        Modifier
                            .background(Color.LightGray, MaterialTheme.shapes.small)
                            .padding(12.dp)
                    ) {
                        if (searchQuery.text.isEmpty()) {
                            Text(stringResource(R.string.search_content), color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.height(6.dp))
            if (isLoading) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.semantics { contentDescription =
                            context.getString(R.string.loading) }
                    )
                }
            }
            if (error != null) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text("Error: $error", color = Color.Red)
                }
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                groupedItems.toSortedMap().forEach { (mediaType, items) ->
                    item {
                        Text(
                            text = mediaType,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        LazyRow {
                            items(items.size) { index ->
                                val item = items[index]
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .size(120.dp)
                                        .clickable { onItemClick(item) }
                                ) {
                                    val imageUrl = item.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" }
                                    if (imageUrl != null) {
                                        Image(
                                            painter = rememberAsyncImagePainter(imageUrl),
                                            contentDescription = item.title,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    } else {
                                        Box(
                                            modifier = Modifier.fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(stringResource(R.string.no_image), color = Color.Gray)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 900, heightDp = 600, locale = "en")
@Composable
fun PreviewTabletLandscape() {
    StarzPlayTheme {
        MainScreenContent(
            groupedItems = emptyMap(),
            isLoading = false,
            error = null,
            onSearch = {},
            onItemClick = {}
        )
    }
} 

