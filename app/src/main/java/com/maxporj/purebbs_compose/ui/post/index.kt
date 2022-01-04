package com.maxporj.purebbs_compose.ui.post

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.maxporj.purebbs_compose.config.MyViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maxporj.purebbs_compose.ui.common.LoadingIndicator
import com.maxporj.purebbs_compose.ui.route.Page

@Composable
fun PostLayout(
    myViewModel: MyViewModel = viewModel(LocalContext.current as ComponentActivity),
    goToDetail:(String)->Unit
){
    val posts by myViewModel.posts.collectAsState(initial = null)
    val isLoading by myViewModel.isLoading.collectAsState()

//    when {
//        isLoading -> LoadingIndicator()
//        else -> PostList(posts, goToDetail, myViewModel)
//    }
    PostList(posts, goToDetail, myViewModel)
}

@Composable
fun PostList(posts: List<Post>?, goToDetail:(String)->Unit, myViewModel: MyViewModel){

    val context = LocalContext.current
    val id = "12345"

    Text(
        text = "Post List",
        modifier = Modifier.clickable {
            goToDetail("${Page.PostDetail.name}/${id}")
        }
    )

    when(posts){
        null ->{}
        else ->{
            LazyColumn{
                items(posts){
                    post ->
                    Row {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = post.title!!
                        )
                    }
                }
            }
        }
    }

}