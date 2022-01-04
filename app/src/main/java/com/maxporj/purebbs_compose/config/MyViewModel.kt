package com.maxporj.purebbs_compose.config

import androidx.lifecycle.ViewModel
import com.maxporj.purebbs_compose.ui.post.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel: ViewModel() {

    private val _posts = MutableStateFlow(emptyList<Post>())
    val posts: StateFlow<List<Post>> = _posts

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun clearLoading(){
        _isLoading.value = false
    }

}