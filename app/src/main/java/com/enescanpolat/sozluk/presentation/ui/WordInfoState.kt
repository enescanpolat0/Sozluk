package com.enescanpolat.sozluk.presentation.ui

import com.enescanpolat.sozluk.domain.model.WordInfo

data class WordInfoState (
    val wordInfoItems : List<WordInfo> = emptyList(),
    val isLoading : Boolean = false
)