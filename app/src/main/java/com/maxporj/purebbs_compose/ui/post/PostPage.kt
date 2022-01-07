package com.maxporj.purebbs_compose.ui.post

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.maxporj.purebbs_compose.config.Config
import com.maxporj.purebbs_compose.config.MyViewModel
import com.maxporj.purebbs_compose.ui.route.Page

@Composable
fun PostLayout(
    myViewModel: MyViewModel = viewModel(LocalContext.current as ComponentActivity),
    goToDetail: (String) -> Unit
) {
    val posts by myViewModel.posts.collectAsState(initial = null)
    val isLoading by myViewModel.isLoading.collectAsState()

//    PostList(posts, goToDetail, myViewModel)
    PostList(myViewModel.postList.value, goToDetail, myViewModel)
}

@ExperimentalCoilApi
@Composable
fun PostList(posts: List<Post>?, goToDetail: (String) -> Unit, myViewModel: MyViewModel) {

    when (posts) {
        null -> {}
        else -> {
            LazyColumn {
                items(posts) { post ->

                    Row(
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth()
                            .padding(5.dp)
                            .background(color = Color(0xFF9CCC65))
                            .clickable { goToDetail("${Page.PostDetail.name}/${post.postId}") },
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        val path = Config.calcAvatarPath(
                            source = post.source,
                            avatarFileName = post.avatarFileName,
                            oauth_avatarUrl = post.oauth?.avatarUrl,
                            anonymous = post.anonymous,
                            isMyself = false
                        )
                        val painter = rememberImagePainter(
                            data = path,
                            builder = {
                                crossfade(true)
                            }
                        )

                        Image(
                            painter = painter,
                            contentDescription = "image",
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .padding(10.dp)
                                .clip(CircleShape)
                        )

                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = post.title!!
//                            text = path
                        )
                    }
                }
            }
        }
    }

}