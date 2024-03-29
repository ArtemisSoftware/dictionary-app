package com.artemissoftware.dictionaryapp.feature_dictionary.data.remote.dto

import com.artemissoftware.dictionaryapp.feature_dictionary.data.local.entities.WordInfoEntity
import com.artemissoftware.dictionaryapp.feature_dictionary.domain.models.WordInfo

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val origin: String?, //can be null
    val phonetic: String?,
    val phonetics: List<PhoneticDto>,
    val word: String
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },
            origin = origin ?: "",
            phonetic = phonetic ?: "",
            word = word
        )
    }
}
