package com.maxporj.purebbs_compose.config

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.net.URL

object Config {
    lateinit var application: MyApplication

        val BASE_URL: URL = URL("http://192.168.31.70:3001")
//    val BASE_URL: URL = URL("http://purebbs.com")
    val PATH_AVATAR:String = "user/avatar/"
    val CATEGORY_ALL:String = "category_all"
//    var categoryCurrent:String = Config.CATEGORY_ALL

    var categoryLiveStr = Config.CATEGORY_ALL
    fun updateAndCompareCategoryLiveStr(newStr:String):Boolean{
        if(newStr == categoryLiveStr){
            return false
        }else{
            categoryLiveStr = newStr
            return true
        }
    }
    val _categoryCurrentLive: MutableLiveData<String> by lazy {
        Log.d("PureBBS", "<category refresh> category by lazy")
        MutableLiveData<String>(CATEGORY_ALL)
    }
    val categoryCurrentLive: LiveData<String>
        get(){
            Log.d("PureBBS", "<category refresh> category get()")
            return _categoryCurrentLive
        }

    val DATABASE_NAME:String = "purebbs_database21"

    fun calcTopAppBarAvatarPath(userInfo: UserInfo):String{
        if (userInfo.avatarFileName != null) {
            Log.d("PureBBS", "avatarFileName: ${userInfo.avatarFileName}")
            return URL(BASE_URL, PATH_AVATAR + userInfo.avatarFileName).toString()
        } else {
            Log.d("PureBBS", "avatarFileName: default.png")
            return URL(BASE_URL, PATH_AVATAR + "default.png").toString()
        }
    }

    fun calcAvatarPath(
        source: String?,
        avatarFileName: String?,
        oauth_avatarUrl: String?,
        anonymous: Boolean,
        isMyself: Boolean
    ): String {

        if (anonymous) {
            if (isMyself) {
                return URL(BASE_URL, PATH_AVATAR + "myanonymous.png").toString()
//                return PATH_AVATAR + "myanonymous.png"
            } else {
                return URL(BASE_URL, PATH_AVATAR + "anonymous.png").toString()
//                return PATH_AVATAR + "anonymous.png"
            }
        } else {
            if (source == "oauth") {
                return oauth_avatarUrl!!
            } else {//?????????????????? oauth ??? register ?????????null?????????register
                if (avatarFileName != null) {
                    return URL(BASE_URL, PATH_AVATAR + avatarFileName).toString()
//                    return PATH_AVATAR + avatarFileName
                } else {
                    return URL(BASE_URL, PATH_AVATAR + "default.png").toString()
//                    return PATH_AVATAR + "default.png"
                }
            }
        }
    }

    fun calcRandomCodeImgPath(random:Int = 0):String{
        return URL(BASE_URL,"tool/verify?mt=${random}").toString()
    }
}