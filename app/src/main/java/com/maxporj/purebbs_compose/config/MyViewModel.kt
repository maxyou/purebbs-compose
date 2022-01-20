package com.maxporj.purebbs_compose.config

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxporj.purebbs_compose.config.Config.application
import com.maxporj.purebbs_compose.net.HttpData
import com.maxporj.purebbs_compose.net.HttpService
import com.maxporj.purebbs_compose.ui.post.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    val myRepository:MyRepository = MyRepository(
        this,
        MyRoomDatabase.getDatabase(application, viewModelScope).postDao(),
        MyRoomDatabase.getDatabase(application, viewModelScope).detailDao(),
        HttpService.api
    )

    var userInfo = MutableStateFlow<UserInfo?>(null)
//    var loginRetrun = MutableStateFlow<HttpData.LoginReturn?>(null)

    fun getUserInfoFromUserStatus(userStatusReturn: HttpData.UserStatusReturn?):UserInfo?{

        userStatusReturn?:return null
        userStatusReturn.code?:return null

        val userInfo = UserInfo(
            loginType = "login",
            code = userStatusReturn.code,
            userStatusReturn = userStatusReturn
        )

        if(userStatusReturn.code == 0 && userStatusReturn.data!=null){
            userInfo._id = userStatusReturn.data._id
            userInfo.name = userStatusReturn.data.name
            userInfo.email = userStatusReturn.data.email
            userInfo.role = userStatusReturn.data.role
            userInfo.updated = userStatusReturn.data.updated
            userInfo.created = userStatusReturn.data.created
            userInfo.avatarFileName = userStatusReturn.data.avatarFileName
            userInfo.setting = userStatusReturn.data.setting
            userInfo.source = userStatusReturn.data.source
            userInfo.oauth = userStatusReturn.data.oauth
        }

        return userInfo
    }

    fun getUserInfoFromLogin(loginReturn: HttpData.LoginReturn?):UserInfo?{

        loginReturn?:return null
        loginReturn.code?:return null

        val userInfo = UserInfo(
            loginType = "login",
            code = loginReturn.code,
            loginReturn = loginReturn
        )

        if(loginReturn.code == 0 && loginReturn.data!=null){
            userInfo._id = loginReturn.data._id
            userInfo.name = loginReturn.data.name
            userInfo.email = loginReturn.data.email
            userInfo.role = loginReturn.data.role
            userInfo.updated = loginReturn.data.updated
            userInfo.created = loginReturn.data.created
            userInfo.avatarFileName = loginReturn.data.avatarFileName
            Log.d("PureBBS", "loginReturn.data.avatarFileName: ${loginReturn.data.avatarFileName}")
        }

        return userInfo
    }
    fun getUserInfoFromRegister(registerReturn: HttpData.RegisterReturn?):UserInfo?{

        registerReturn?:return null
        registerReturn.code?:return null

        val userInfo = UserInfo(
            loginType = "register",
            code = registerReturn.code,
            registerReturn = registerReturn
        )

        if(registerReturn.code == 0 && registerReturn.data!=null){
            userInfo._id = registerReturn.data._id
            userInfo.name = registerReturn.data.name
            userInfo.email = registerReturn.data.email
            userInfo.role = registerReturn.data.role
            userInfo.updated = registerReturn.data.updated
            userInfo.created = registerReturn.data.created
        }

        return userInfo
    }


    val canNavigateBack = mutableStateOf(false)

    val postList = mutableStateOf(emptyList<Post>())
//    val postPageSize by mutableStateOf(10)
//    val postPageIndex by mutableStateOf(0)

    var current = MutableStateFlow<Int>(1)
    var totalDocs = MutableStateFlow<Int>(10)
    var pageSize = MutableStateFlow<Int>(10)

    init {
        viewModelScope.launch {

            current.collect {
                myRepository.loadPostList(pageSize.value, current.value)
            }
        }
    }


//    val posts: Flow<List<Post>?> = myRepository.posts

//    private val _isLoading = MutableStateFlow(true)
//    val isLoading: StateFlow<Boolean> = _isLoading
//
//    fun clearLoading(){
//        _isLoading.value = false
//    }
//    fun load(){
//        myRepository.load()
//        myRepository.loadPostList(postPageSize, postPageIndex)
//    }

}