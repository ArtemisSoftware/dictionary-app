package com.artemissoftware.dictionaryapp.feature_dictionary.data.remote.dto

import com.artemissoftware.dictionaryapp.feature_dictionary.domain.models.Definition

data class DefinitionDto(
    val antonyms: List<String>?,
    val definition: String,
    val example: String?,
    val synonyms: List<String>? //can be null
) {
    fun toDefinition(): Definition {
        return Definition(
            antonyms = antonyms ?: emptyList(),
            definition = definition,
            example = example,
            synonyms = synonyms ?: emptyList()
        )
    }
}
