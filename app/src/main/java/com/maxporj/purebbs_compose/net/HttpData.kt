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
        val code:Int,
        val message:String,
        val data:LoginRetrunData
    ){
        data class LoginRetrunData(
            val name:String,
            val _id:String,
            val source:String,
        )
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