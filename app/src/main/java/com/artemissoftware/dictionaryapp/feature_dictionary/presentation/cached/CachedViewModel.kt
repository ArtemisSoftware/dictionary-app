package com.artemissoftware.dictionaryapp.feature_dictionary.presentation.cached

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.dictionaryapp.feature_dictionary.core.util.Resource
import com.artemissoftware.dictionaryapp.feature_dictionary.domain.models.WordInfoState
import com.artemissoftware.dictionaryapp.feature_dictionary.domain.usecases.GetCachedWordInfo
import com.artemissoftware.dictionaryapp.feature_dictionary.domain.usecases.GetWordInfo
import com.artemissoftware.dictionaryapp.feature_dictionary.presentation.WordInfoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CachedViewModel @Inject constructor(
    private val getCachedWordInfo: GetCachedWordInfo
) : ViewModel() {

    private val _state = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    init {
        getCachedWords()
    }

    private fun getCachedWords() {

        getCachedWordInfo()
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}