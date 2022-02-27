package com.artemissoftware.dictionaryapp.feature_dictionary.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.artemissoftware.dictionaryapp.Screen
import com.artemissoftware.dictionaryapp.ui.theme.Purple700
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DictionaryScreen(
    navController: NavHostController
){

    val viewModel: WordInfoViewModel = hiltViewModel()
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is WordInfoViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                WordSearch(
                    viewModel = viewModel,
                    navController = navController
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.wordInfoItems.size) { i ->

                        val wordInfo = state.wordInfoItems[i]

                        if(i > 0) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        WordInfoItem(wordInfo = wordInfo)

                        if(i < state.wordInfoItems.size - 1) {
                            Divider()
                        }
                    }
                }
            }
            if(state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}


@Composable
private fun WordSearch(
    viewModel: WordInfoViewModel,
    navController: NavHostController
){

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        TextField(
            value = viewModel.searchQuery.value,
            onValueChange = viewModel::onSearch,
            modifier = Modifier.fillMaxWidth(.8f),
            placeholder = {
                Text(text = "Search...")
            }
        )

        IconButton(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(32.dp),
            onClick = {
                navController.navigate(route = Screen.CachedWordsScreen.route)
            }
        ) {
            Icon(Icons.Filled.Info, "info", tint =Purple700)
        }

        
    }
}