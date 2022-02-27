package com.artemissoftware.dictionaryapp.feature_dictionary.domain.repository

import com.artemissoftware.dictionaryapp.feature_dictionary.core.util.Resource
import com.artemissoftware.dictionaryapp.feature_dictionary.domain.models.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>

    fun getCachedWords(): Flow<Resource<List<WordInfo>>>
}