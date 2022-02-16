package com.artemissoftware.dictionaryapp.feature_dictionary.domain.models

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)