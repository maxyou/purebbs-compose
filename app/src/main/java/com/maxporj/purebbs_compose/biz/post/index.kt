package com.maxporj.purebbs_compose.biz.post

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maxporj.purebbs_compose.route.Page

@Composable
fun PostLayout(navController: NavHostController){

    val context = LocalContext.current
    val id = "12345"

    Text(
        text = "Post List",
        modifier = Modifier.clickable {
            Toast.makeText(context, "OnClick", Toast.LENGTH_LONG).show()
            Log.v("OnClick", "OnClick ");
            navController.navigate("${Page.PostDetail.name}/${id}")
    })
}