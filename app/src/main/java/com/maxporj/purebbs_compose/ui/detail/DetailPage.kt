package com.maxporj.purebbs_compose.ui.detail

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maxporj.purebbs_compose.config.MyViewModel
import kotlin.math.ceil


@Composable
fun DetailLayout(
    myViewModel: MyViewModel = viewModel(LocalContext.current as ComponentActivity),
    id:String?,
    backToPost:()->Unit
){
    val context = LocalContext.current

    Column(modifier = Modifier.padding(15.dp)) {

        val ceil = 8.toDouble() / 10.toDouble()
        val cei1 = 10.toDouble() / 10.toDouble()
        val cei2 = 12.toDouble() / 10.toDouble()
        val cei3 = 18.toDouble() / 10.toDouble()

        Text(text = "${ceil}")
        Text(text = "${cei1}")
        Text(text = "${cei2}")
        Text(text = "${cei3}")
        Text(text = "---------------")
        Text(text = "${ceil(ceil)}")
        Text(text = "${ceil(cei1)}")
        Text(text = "${ceil(cei2)}")
        Text(text = "${ceil(cei3)}")

    }

//    Text(
//        text = "Post Detail, id:${id}",
//
//        modifier = Modifier.clickable {
//            backToPost()
//        }
//    )
}