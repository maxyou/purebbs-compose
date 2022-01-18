package com.maxporj.purebbs_compose.net

import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HttpApi {

    @POST("user/login")
    suspend fun login(
        @Body loginData:HttpData.LoginData
    ): HttpData.LoginReturn

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