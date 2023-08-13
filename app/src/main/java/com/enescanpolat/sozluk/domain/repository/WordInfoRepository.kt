package com.enescanpolat.sozluk.domain.repository

import com.enescanpolat.sozluk.domain.model.WordInfo
import com.enescanpolat.sozluk.util.Resource
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word:String):Flow<Resource<List<WordInfo>>>

}