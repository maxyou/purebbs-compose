package com.maxporj.purebbs_compose.ui.detail

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maxporj.purebbs_compose.config.MyViewModel
import com.maxporj.purebbs_compose.ui.common.PageRound
import com.maxporj.purebbs_compose.ui.post.PostList
import com.maxporj.purebbs_compose.ui.post.RoundButton
import com.maxporj.purebbs_compose.ui.post.RoundInterval
import kotlin.math.ceil


@Composable
fun DetailLayout(
    myViewModel: MyViewModel = viewModel(LocalContext.current as ComponentActivity),
    id:String?,
    backToPost:()->Unit
){
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.padding(15.dp)
    ) {

        Row(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(1f)
            .height(40.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color(0xffccccff)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            Text(text = "删除")
            Text(text = "编辑")
            Text(text = "评论")
        }

        Column(
            modifier = Modifier
                .background(Color(0xffff9900))
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
                .weight(weight =1f, fill = false)

        ) {
            Text(//modifier = Modifier.scrollable(state = scrollState,orientation = Orientation.Vertical),
                text = "content" +
                        "QQQQQQQQQQQQ" +
                        "&&&&&&&&&&&&" +
                        "QQQQQQQQQQQQ" +
                        "$$$$$$$$$$$$" +
                        "QQQQQQQQQQQQ" +
                        "@@@@@@@@@@@@" +
                        "QQQQQQQQQQQQ" +
                        "############",
                fontSize = 50.sp,
            )

        }


//        SelectionContainer{
//            Text(text = "content")
//        }
    }

}