package com.artemissoftware.dictionaryapp.feature_dictionary.domain.usecases

import com.artemissoftware.dictionaryapp.feature_dictionary.core.util.Resource
import com.artemissoftware.dictionaryapp.feature_dictionary.domain.models.WordInfo
import com.artemissoftware.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordsInfo(
    private val repository: WordInfoRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()) {
            return flow {  }
        }
        return repository.getWordInfo(word)
    }
}