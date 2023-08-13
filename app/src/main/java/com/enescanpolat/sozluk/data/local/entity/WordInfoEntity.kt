package com.enescanpolat.sozluk.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.enescanpolat.sozluk.domain.model.Meaning
import com.enescanpolat.sozluk.domain.model.WordInfo


@Entity
data class WordInfoEntity(
    val word:String,
    val phonetic:String,
    val origin : String,
    val meanings: List<Meaning>,
    @PrimaryKey val id : Int?=null
){
    fun toWordInfo():WordInfo{
        return WordInfo(origin = origin, word = word, meanings = meanings, phonetic = phonetic)
    }
}
