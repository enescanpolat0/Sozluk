package com.enescanpolat.sozluk.domain.model

import com.enescanpolat.sozluk.data.remote.dto.MeaningDto
import com.enescanpolat.sozluk.data.remote.dto.PhoneticDto

data class WordInfo(
    val origin:String,
    val meanings: List<Meaning>,
    val phonetic: String,
    val word: String
)
