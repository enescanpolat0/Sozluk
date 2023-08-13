package com.enescanpolat.sozluk.domain.use_case

import com.enescanpolat.sozluk.domain.repository.WordInfoRepository

class GetWordInfoUseCase(private val repository: WordInfoRepository) {



    fun wordInfo(word:String)=repository.getWordInfo(word = word)

}