package com.enescanpolat.sozluk.data.remote.dto

import com.enescanpolat.sozluk.data.local.entity.WordInfoEntity
import com.enescanpolat.sozluk.domain.model.WordInfo

data class WordInfoDto(

    val origin:String,
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val word: String
){
    fun toWordInfoEntity():WordInfoEntity{
        return WordInfoEntity(word = word, phonetic = phonetic, origin = origin,meanings = meanings.map { it.toMeaning() })
    }


}