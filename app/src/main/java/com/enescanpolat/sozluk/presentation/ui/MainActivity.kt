package com.enescanpolat.sozluk.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.rememberScaffoldState

import androidx.compose.material.*

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.enescanpolat.sozluk.presentation.ui.theme.SozlukTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SozlukTheme {
                // A surface container using the 'background' color from the theme
                val viewmodel : WordInfoWiewModel = hiltViewModel()
                val state = viewmodel.state.value
                val scaffoldState = rememberScaffoldState()

                LaunchedEffect(key1 = true ){
                    viewmodel.ewentflow.collectLatest {event->
                        when(event){

                            is WordInfoWiewModel.UIEvent.ShowSnackBar ->{
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }

                        }
                    }
                }
                Scaffold(scaffoldState = scaffoldState
                ) {it.calculateTopPadding()
                    Box(modifier = Modifier.background(color = MaterialTheme.colors.background)) {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                        ) {
                            TextField(value =viewmodel.searchQuery.value ,
                                    onValueChange = viewmodel::onSearch,
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = { Text(text = "Search...")}
                                )
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyColumn(modifier = Modifier.fillMaxSize()){
                                items(state.wordInfoItems.size){i->
                                    val wordInfo = state.wordInfoItems[i]
                                    if (i>0){
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                    WordInfoItem(wordInfo=wordInfo)
                                    if (i<state.wordInfoItems.size - 1){
                                        Divider()
                                    }

                                }
                            }

                        }
                        if(state.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }

                    }
                }
        }
    }
}

