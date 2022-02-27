package com.artemissoftware.dictionaryapp.feature_dictionary.domain.usecases

import com.artemissoftware.dictionaryapp.feature_dictionary.core.util.Resource
import com.artemissoftware.dictionaryapp.feature_dictionary.domain.models.WordInfo
import com.artemissoftware.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow

class GetCachedWordInfo(
    private val repository: WordInfoRepository
) {

    operator fun invoke(): Flow<Resource<List<WordInfo>>> {
        return repository.getCachedWords()
    }
}