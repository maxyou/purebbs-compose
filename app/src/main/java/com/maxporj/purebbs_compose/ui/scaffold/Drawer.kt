package com.maxporj.purebbs_compose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.maxporj.purebbs_compose.config.MyViewModel

@Composable
fun Drawer(myViewModel: MyViewModel) {

    Column() {
        Text(text = "Drawer")
    }

}