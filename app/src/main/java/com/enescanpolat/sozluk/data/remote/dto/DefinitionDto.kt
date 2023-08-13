package com.enescanpolat.sozluk.data.remote.dto

import com.enescanpolat.sozluk.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
){
    fun toDefinition():Definition{
        return Definition(
            antonyms, definition, example, synonyms
        )
    }
}