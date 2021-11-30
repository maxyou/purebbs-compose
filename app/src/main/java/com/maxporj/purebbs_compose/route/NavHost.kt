package com.maxporj.purebbs_compose.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maxporj.purebbs_compose.biz.detail.DetailLayout
import com.maxporj.purebbs_compose.biz.post.PostLayout

enum class Page (val title:String){
    PostList("post list"),
    PostDetail("post detail")
}

@Composable
fun GetNavHost(navController: NavHostController, modifier: Modifier = Modifier){
    NavHost(
        navController = navController,
        startDestination = Page.PostList.name,
        modifier = modifier
    ){
        composable(Page.PostList.name){
            PostLayout(navController)
        }
        composable(Page.PostDetail.name){
            DetailLayout(navController)
        }
    }
}
