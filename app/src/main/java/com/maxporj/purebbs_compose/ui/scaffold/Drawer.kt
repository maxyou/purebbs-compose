package com.maxporj.purebbs_compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.maxporj.purebbs_compose.config.Config
import com.maxporj.purebbs_compose.config.MyViewModel

@Composable
fun Drawer(myViewModel: MyViewModel) {

    val userInfo by myViewModel.userInfo.collectAsState()

    Column() {

        Box(
            Modifier.height(200.dp).fillMaxWidth()
                .background(color = Color(0xff999fff))
                .wrapContentSize(Alignment.Center)
        ){
            if(userInfo!=null && userInfo!!.code == 0){

                val path = Config.calcTopAppBarAvatarPath(userInfo!!)

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
                        .clickable { }
                        .width(50.dp)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                )
            }else{
                Text(text = "Login")
            }
        }
    }

}