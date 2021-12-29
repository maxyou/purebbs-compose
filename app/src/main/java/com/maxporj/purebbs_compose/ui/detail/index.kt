package com.maxporj.purebbs_compose.ui.detail

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maxporj.purebbs_compose.config.MyViewModel


@Composable
fun DetailLayout(
    myViewModel: MyViewModel = viewModel(LocalContext.current as ComponentActivity),
    id:String?,
    backToPost:()->Unit
){
    val context = LocalContext.current

    Text(
        text = "Post Detail, id:${id}, post count: ${myViewModel.postCount}, detail count: ${myViewModel.detailCount}",

        modifier = Modifier.clickable {

            Toast.makeText(context, "OnClick", Toast.LENGTH_LONG).show()
            Log.v("OnClick", "OnClick ");

            myViewModel.detailCount++

            backToPost()
        })
}