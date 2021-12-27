package com.maxporj.purebbs_compose.ui.post

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.maxporj.purebbs_compose.ui.route.Page

@Composable
fun PostLayout(goToDetail:(String)->Unit){

    val context = LocalContext.current
    val id = "12345"

    Text(
        text = "Post List",
        modifier = Modifier.clickable {
            Toast.makeText(context, "OnClick", Toast.LENGTH_LONG).show()
            Log.v("OnClick", "OnClick ");
            goToDetail("${Page.PostDetail.name}/${id}")
    })
}