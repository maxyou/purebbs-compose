package com.maxporj.purebbs_compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
fun Drawer(
    myViewModel: MyViewModel,
    openLoginDialog: MutableState<Boolean>,
    openLogoutDialog: MutableState<Boolean>,
    openRegisterDialog: MutableState<Boolean>,
) {

    val userInfo by myViewModel.userInfo.collectAsState()

    Column {

        Column(
            Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(color = Color(0xff999fff)),
//                .wrapContentSize(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
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

                Button(onClick = { openLogoutDialog.value = true }, modifier = Modifier.padding(10.dp).width(100.dp)) {
                    Text(text = "Logout")
                }
            }else{
                Button(onClick = { openLoginDialog.value = true }, modifier = Modifier.padding(10.dp).width(100.dp)) {
                    Text(text = "Login")
                }
                Button(onClick = { openRegisterDialog.value = true }, modifier = Modifier.padding(10.dp).width(100.dp)) {
                    Text(text = "Register")
                }
            }
        }
    }

}