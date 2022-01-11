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
import androidx.compose.runtime.*
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
import com.maxporj.purebbs_compose.ui.common.PageRound
import com.maxporj.purebbs_compose.ui.route.Page

@Composable
fun PostLayout(
    myViewModel: MyViewModel = viewModel(LocalContext.current as ComponentActivity),
    goToDetail: (String) -> Unit
) {

    val current by myViewModel.current.collectAsState()
    val totalDocs by myViewModel.totalDocs.collectAsState()
    val pageSize by myViewModel.pageSize.collectAsState()

    Column(
        modifier = Modifier.padding(15.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            PageRound(
                current = current,
                ext = 2,
                totalDocs = totalDocs,
                pageSize = pageSize,
                onClick = { myViewModel.current.value = it },
                RoundButton = RoundButton,
                RoundInterval = RoundInterval
            )
        }

//    PostList(posts, goToDetail, myViewModel)
        PostList(myViewModel.postList.value, goToDetail, myViewModel)
    }
}

val RoundInterval = @Composable {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "---")
    }
}

val RoundButton = @Composable { count: Int, isCurrent: Boolean, onClick: (Int) -> Unit ->
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick(count) }
            .padding(2.dp)
            .background(if (isCurrent) Color(0xFF9CCC65) else Color(0xFFbC4Ca5)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "${count}")
    }
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


@Composable
fun RoundButton(count: Int, isCurrent: Boolean, onClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick(count) }
            .padding(2.dp)
            .background(if (isCurrent) Color(0xFF9CCC65) else Color(0xFFbC4Ca5)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "${count}")
    }
}

@Composable
fun RoundInterval() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "---")
    }
}

