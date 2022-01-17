package com.maxporj.purebbs_compose.net

import retrofit2.http.GET
import retrofit2.http.Query

interface HttpApi {

    @GET("detail/comment/getpage")
    suspend fun getDetailByPaginate(
        @Query("pageInfo") pageInfo: String
    ): HttpData.DetailListRet

    @GET("post/getpage")
    suspend fun getPostByPaginate(
        @Query("pageInfo") pageInfo: String
    ): HttpData.PostListRet

    //{ code: 0, message: '获取数据成功', data: {category: appConfig.category} };
    @GET("sys/category")
    suspend fun getCategoryList(): HttpData.CategoryListRet
}