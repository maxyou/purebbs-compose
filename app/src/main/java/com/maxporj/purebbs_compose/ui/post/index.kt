package com.maxporj.purebbs_compose.ui.post

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.maxporj.purebbs_compose.config.MyViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maxporj.purebbs_compose.ui.route.Page

@Composable
fun PostLayout(
    myViewModel: MyViewModel = viewModel(LocalContext.current as ComponentActivity),
    goToDetail:(String)->Unit
){

    val context = LocalContext.current
    val id = "12345"

    Text(
        text = "Post List, post count: ${myViewModel.postCount}, detail count: ${myViewModel.detailCount}",

        modifier = Modifier.clickable {

            Toast.makeText(context, "OnClick", Toast.LENGTH_LONG).show()
            Log.v("OnClick", "OnClick ");

            myViewModel.postCount++

            goToDetail("${Page.PostDetail.name}/${id}")
    })
}