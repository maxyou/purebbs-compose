package com.maxporj.purebbs_compose.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxporj.purebbs_compose.config.Config.application
import com.maxporj.purebbs_compose.net.HttpService
import com.maxporj.purebbs_compose.ui.post.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel: ViewModel() {

    val myRepository:MyRepository = MyRepository(
        viewModelScope,
        MyRoomDatabase.getDatabase(application, viewModelScope).postDao(),
        MyRoomDatabase.getDatabase(application, viewModelScope).detailDao(),
        HttpService.api
    )

    val canNavigateBack = mutableStateOf(false)

    val posts: Flow<List<Post>?> = myRepository.posts

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun clearLoading(){
        _isLoading.value = false
    }
    fun load(){
        myRepository.load()
    }

}