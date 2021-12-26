package com.maxporj.purebbs_compose.biz.detail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.maxporj.purebbs_compose.route.Page


@Composable
fun DetailLayout(navController: NavHostController, id:String?){
    val context = LocalContext.current

    Text(
        text = "Post Detail, id:${id}",
        modifier = Modifier.clickable {
            Toast.makeText(context, "OnClick", Toast.LENGTH_LONG).show()
            Log.v("OnClick", "OnClick ");
            navController.navigate(Page.PostList.name)
        })
}