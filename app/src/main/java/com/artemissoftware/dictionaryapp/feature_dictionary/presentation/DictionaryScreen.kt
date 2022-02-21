package com.artemissoftware.dictionaryapp.feature_dictionary.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DictionaryScreen(){

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
                TextField(
                    value = viewModel.searchQuery.value,
                    onValueChange = viewModel::onSearch,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Search...")
                    }
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
