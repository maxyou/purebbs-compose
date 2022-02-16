package com.maxporj.purebbs_compose.ui.route

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.maxporj.purebbs_compose.config.MyViewModel
import com.maxporj.purebbs_compose.ui.detail.DetailLayout
import com.maxporj.purebbs_compose.ui.post.PostLayout

enum class Page(val title: String) {
    PostList("post list"),
    PostDetail("post detail")
}

@Composable
fun GetNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    myViewModel: MyViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    NavHost(
        navController = navController,
        startDestination = Page.PostList.name,
        modifier = modifier
    ) {
        composable(
            Page.PostList.name,
        ) {
            PostLayout {
                navController.navigate(it)
            }
        }
        composable(
            "${Page.PostDetail.name}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailLayout(
                id = it.arguments?.getString("id")
            ) {
                navController.popBackStack()
            }
        }
    }
}
