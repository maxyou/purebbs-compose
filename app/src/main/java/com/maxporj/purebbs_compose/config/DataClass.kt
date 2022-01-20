package com.maxporj.purebbs_compose.config

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.maxporj.purebbs_compose.net.HttpData

data class UserInfo(

    var loginType:String,//"login" or "register" or "getUserStatus"
    var code:Int = -1,

    //often used info
    var _id:String? = null,
    var uuid:String? = null,
    var name:String? = null,
    var email:String? = null,
    var role:String? = null,
    var updated:String? = null,
    var created:String? = null,
    var avatarFileName:String? = null,
    var source:String? = null,
    var oauth: HttpData.UserStatusReturn.Data.Oauth? = null,
    var setting: HttpData.UserStatusReturn.Data.Setting? = null,

    // result
    var userStatusReturn: HttpData.UserStatusReturn? = null,
    var loginReturn: HttpData.LoginReturn? = null,
    var registerReturn: HttpData.RegisterReturn? = null
)

@Entity(tableName = "category_table")
data class Category(@PrimaryKey val idStr: String, val name: String)

@Entity(tableName = "config_set_table")
data class ConfigSet(@PrimaryKey val name: String, val value: String)

data class Oauth(val avatarUrl:String)

data class LikeUser(val _id: String, val name:String){
    companion object{
        fun fromJsonStr(str:String):LikeUser?{
            Log.d("PureBBS", "PostBrief.LikeUser.fromJsonStr: $str")
            return Gson().fromJson(str, LikeUser::class.java)
        }
    }
    fun toJsonStr():String?{
        val js = Gson().toJson(this)
        Log.d("PureBBS", "PostBrief.LikeUser.toJsonStr: $js")
        return js
    }
}

data class Extend(val addChoice:String){

}