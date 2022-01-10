package com.maxporj.purebbs_compose.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.math.ceil

@Composable
fun PageRound(current: Int, ext: Int, totalDocs: Int, pageSize: Int, onClick: (Int) -> Unit) {

    var maxRight = ceil(totalDocs.toDouble() / pageSize.toDouble())
    val maxRightInt = maxRight.toInt()

    val ba = calcPaginateArray(current = current, ext = ext, maxRight = maxRightInt)

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff88cccc)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        if (ba[0] != 1) {
            Box(modifier = Modifier.weight(1f)) { RoundButton(1, 1 == current, onClick) }
            if (ba[0] != 2) {
                Box(modifier = Modifier.weight(1f)) { RoundInterval() }
            }
        }

        ba.map {
            Box(modifier = Modifier.weight(1f)) {
                RoundButton(
                    it,
                    it == current,
                    onClick
                )
            }
        }

        if (ba[ba.size - 1] != maxRightInt) {
            if (ba[ba.size - 1] != maxRightInt - 1) {
                Box(modifier = Modifier.weight(1f)) { RoundInterval() }
            }
            Box(modifier = Modifier.weight(1f)) {
                RoundButton(
                    maxRightInt,
                    maxRightInt == current,
                    onClick
                )
            }
        }
    }
}

@Composable
fun RoundButton(count: Int, isCurrent: Boolean, onClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick(count) }
            .padding(2.dp)
            .background(if (isCurrent) Color(0xFF9CCC65) else Color(0xFFbC4Ca5)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "${count}")
    }
}

@Composable
fun RoundInterval() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "---")
    }
}

fun calcPaginateArray(current: Int, ext: Int, maxRight: Int): MutableList<Int> {

    val ba = mutableListOf<Int>(current)
    for (i in 0 until ext) {
        ba.add((current + 1) + i)
    }
    for (i in 0 until ext) {
        ba.add(0, (current - 1) - i)
    }
    // console.log(ba)
    while (true) {
        if (ba[0] < 1) {
            ba.removeFirstOrNull()
            val addRight = ba[ba.size - 1] + 1
            if (addRight <= maxRight) {
                ba.add(addRight)
            }
        } else {
            break
        }
    }
    // console.log(ba)
    while (true) {
        if (ba[ba.size - 1] > maxRight) {
            ba.removeLastOrNull()
            val addLeft = ba[0] - 1
            if (addLeft >= 1) {
                ba.add(0, addLeft)
            }
        } else {
            break
        }
    }
    // console.log(ba)
    return ba
}