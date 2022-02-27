package com.artemissoftware.dictionaryapp.feature_dictionary.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.dictionaryapp.feature_dictionary.domain.models.WordInfo
import com.artemissoftware.dictionaryapp.feature_dictionary.presentation.cached.CachedViewModel

@Composable
fun CachedWordsScreen(){

    val viewModel: CachedViewModel = hiltViewModel()
    val state = viewModel.state.value

    Scaffold {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
        ) {

            Column {
                Title()

                LazyColumn {
                    items(items = state.wordInfoItems) { word ->
                        WordItem(wordInfo = word)
                        Divider(modifier = Modifier.padding(horizontal = 16.dp))
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
private fun Title(){

    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Cached Words",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )

        Divider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.Black
        )
    }
}

@Composable
private fun WordItem(
    wordInfo: WordInfo
) {


    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = wordInfo.word,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = wordInfo.phonetic,
            style = MaterialTheme.typography.subtitle1,

        )
    }

}