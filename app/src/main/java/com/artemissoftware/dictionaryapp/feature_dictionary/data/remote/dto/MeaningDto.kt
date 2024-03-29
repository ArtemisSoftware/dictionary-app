package com.artemissoftware.dictionaryapp.feature_dictionary.data.remote.dto

import com.artemissoftware.dictionaryapp.feature_dictionary.domain.models.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String
) {
    fun toMeaning(): Meaning {
        return Meaning(
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech
        )
    }
}
