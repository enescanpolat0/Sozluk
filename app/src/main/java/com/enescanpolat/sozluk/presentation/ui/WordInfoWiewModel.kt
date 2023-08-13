package com.enescanpolat.sozluk.presentation.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enescanpolat.sozluk.domain.use_case.GetWordInfoUseCase
import com.enescanpolat.sozluk.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoWiewModel @Inject constructor(private val getWordInfo:GetWordInfoUseCase):ViewModel() {

    private val _searchQuery = mutableStateOf<String>("")
    val searchQuery : State<String> = _searchQuery

    private val _state= mutableStateOf<WordInfoState>(WordInfoState())
    val state : State<WordInfoState> = _state

    private val _ewentFlow = MutableSharedFlow<UIEvent>()
    val ewentflow = _ewentFlow.asSharedFlow()

    private var searchJob : Job?=null

    fun onSearch(query:String){

        _searchQuery.value=query
        searchJob?.cancel()
        searchJob=viewModelScope.launch {
            delay(500L)
            getWordInfo.wordInfo(query).onEach {result->

                when(result){

                    is Resource.Loading->{
                        _state.value=state.value.copy(wordInfoItems = result.data?: emptyList(),true)

                    }

                    is Resource.Error->{
                        _state.value=state.value.copy(wordInfoItems = result.data?: emptyList(),false)
                        _ewentFlow.emit(UIEvent.ShowSnackBar(result.message ?: "Unkown Error"))
                    }

                    is Resource.Success->{
                        _state.value=state.value.copy(wordInfoItems = result.data?: emptyList(),false)
                    }

                }

            }.launchIn(this)
        }

    }


    sealed class UIEvent(){
        data class ShowSnackBar(val message:String): UIEvent()
    }

}