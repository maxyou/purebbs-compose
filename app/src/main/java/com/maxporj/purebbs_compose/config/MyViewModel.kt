package com.maxporj.purebbs_compose.config

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxporj.purebbs_compose.config.Config.application
import com.maxporj.purebbs_compose.net.HttpService
import com.maxporj.purebbs_compose.ui.post.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    val myRepository:MyRepository = MyRepository(
        this,
        MyRoomDatabase.getDatabase(application, viewModelScope).postDao(),
        MyRoomDatabase.getDatabase(application, viewModelScope).detailDao(),
        HttpService.api
    )

    val canNavigateBack = mutableStateOf(false)

    val postList = mutableStateOf(emptyList<Post>())
//    val postPageSize by mutableStateOf(10)
//    val postPageIndex by mutableStateOf(0)

    var current = MutableStateFlow<Int>(1)
    var totalDocs = MutableStateFlow<Int>(300)
    var pageSize = MutableStateFlow<Int>(10)

    init {
        viewModelScope.launch {

            current.collect {
                myRepository.loadPostList(pageSize.value, current.value)
            }
        }
    }


//    val posts: Flow<List<Post>?> = myRepository.posts

//    private val _isLoading = MutableStateFlow(true)
//    val isLoading: StateFlow<Boolean> = _isLoading
//
//    fun clearLoading(){
//        _isLoading.value = false
//    }
//    fun load(){
//        myRepository.load()
//        myRepository.loadPostList(postPageSize, postPageIndex)
//    }

}