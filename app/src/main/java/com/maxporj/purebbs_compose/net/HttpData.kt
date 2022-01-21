package com.maxporj.purebbs_compose.net

import com.maxporj.purebbs_compose.ui.detail.Detail
import com.maxporj.purebbs_compose.ui.post.Post
import com.maxporj.purebbs_compose.config.Category

class HttpData {

    data class LoginData(
        val name:String,
        val password:String,
        val code:String
    )

    data class LoginReturn(
        val code:Int? = null,
        val message:String? = null,
        val data:Data? = null,
    ){
        data class Data(
            val _id:String? = null,
            val uuid:String? = null,
            val name:String? = null,
            val email:String? = null,
            val role:String? = null,
            val updated:String? = null,
            val created:String? = null,
            val avatarFileName:String? = null,
            val setting:Setting? = null,
        ){
            data class Setting(
                val language:String? = null,
                val postPageSize:Int? = null,
                val commentPageSize:Int? = null,
            )
        }
    }

    data class LogoutData(
        val language:String = "en",
        val postPageSize:Int = 10,
        val commentPageSize:Int = 10
    )

    data class LogoutReturn(
        val code:Int? = null,
        val message:String? = null,
    )


    data class RegisterReturn(
        val code:Int? = null,
        val message:String? = null,
        val data:Data? = null,
    ){
        data class Data(
            val _id:String? = null,
            val uuid:String? = null,
            val name:String? = null,
            val email:String? = null,
            val role:String? = null,
            val updated:String? = null,
            val created:String? = null,
        )
    }

    data class UserStatusReturn(
        val code:Int? = null,
        val message:String? = null,
        val data:Data? = null,
    ){
        data class Data(
            val _id:String? = null,
            val uuid:String? = null,
            val name:String? = null,
            val email:String? = null,
            val role:String? = null,
            val updated:String? = null,
            val created:String? = null,
            val avatarFileName:String? = null,
            val source:String? = null,
            val oauth:Oauth? = null,
            val setting:Setting? = null,
        ){
            data class Setting(
                val language:String? = null,
                val postPageSize:Int? = null,
                val commentPageSize:Int? = null,
            )
            data class Oauth(
                val oauthName:String? = null,
                val type:String? = null,
                val login:String? = null,
                val id:Int? = null,
                val name:String? = null,
                val email:String? = null,
                val avatarUrl:String? = null,
                val homepageUrl:String? = null,
            )
        }
    }



    data class DetailListQuery(
        val query:PostID?,
        val options:Options
    ){
        data class PostID(val postId:String)

        data class Options(
            val offset:Int,
            val limit:Int,
            val sort:Sort,
            val select:String
        ){
            data class Sort(val allUpdated: Int)
        }
    }
    data class DetailListRet(val code:Int, val message:String, val data:List<Detail>, val totalDocs:Int)


    data class PostListQuery(
        val query:Category?,
        val options:Options
    ){
        data class Category(val category:String)

        data class Options(
            val offset:Int,
            val limit:Int,
            val sort:Sort,
            val select:String
        ){
            data class Sort(val allUpdated: Int)
        }
    }
    data class PostListRet(val code:Int, val message:String, val data:List<Post>, val totalDocs:Int)

    data class CategoryObject(val category: List<Category>)
    data class CategoryListRet(val code:Int, val message:String, val data:CategoryObject, val totalDocs:Int)
}