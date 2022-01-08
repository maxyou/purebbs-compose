package com.maxporj.purebbs_compose.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlin.math.ceil

@Composable
fun PageRound(current:Int, ext:Int, totalDocs:Int, pageSize:Int ) {

    var maxRight = ceil(totalDocs.toDouble() / pageSize.toDouble())
    val maxRightInt = maxRight.toInt()

    val ba = calcPaginateArray(current = current, ext = ext, maxRight = maxRightInt)

    Row {

        if(ba[0] != 1){
            Row {
                Text(text = "${if(1==current)'/' else ' '}1${if(1==current)'/' else ' '}")
                if(ba[0] != 2){
                    Text(text = "...")
                }
            }
        }

        ba.map {
            Text(text = "${if(it==current)'/' else ' '}${it}${if(it==current)'/' else ' '}")
        }

        if(ba[ba.size-1] != maxRightInt){
            Row {
                if(ba[ba.size-1] != maxRightInt-1){
                    Text(text = "...")
                }
                Text(text = "${if(maxRightInt==current)'/' else ' '}${maxRightInt}${if(maxRightInt==current)'/' else ' '}")
            }
        }
    }
}

fun calcPaginateArray(current: Int, ext: Int, maxRight: Int): MutableList<Int> {
    // console.log('------------calcPaginateArray---------------')
    // console.log(typeof current)
    // console.log(current)
    // console.log(ext)
    // console.log(maxRight)
    // required:
    //  current <= maxright

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