package com.enescanpolat.sozluk.data.remote.dto

import com.enescanpolat.sozluk.domain.model.Meaning

data class MeaningDto(

    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
){
    fun toMeaning():Meaning{
        return Meaning(definitions = definitions.map { it.toDefinition() }, partOfSpeec = partOfSpeech)
    }
}