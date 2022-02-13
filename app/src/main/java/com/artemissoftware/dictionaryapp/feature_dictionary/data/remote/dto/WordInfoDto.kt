package com.artemissoftware.dictionaryapp.feature_dictionary.data.remote.dto

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val word: String
) {
//    fun toWordInfoEntity(): WordInfoEntity {
//        return WordInfoEntity(
//            meanings = meanings.map { it.toMeaning() },
//            origin = origin,
//            phonetic = phonetic,
//            word = word
//        )
//    }
}
