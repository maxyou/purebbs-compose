package com.maxporj.purebbs_compose.config

import com.maxporj.purebbs_compose.ui.post.Post

object Util {

    val API_PATH_AVATAR = "user/avatar/"

    fun calcAvatarUrl(post: Post){

        val path =
            if(post.anonymous){
                "anonymous.png"
            }else{
                if(post.source == "oauth"){
                    post.oauth?.avatarUrl
                }else{
                    post.avatarFileName ?: "default.png"
                }
            }


    }

}