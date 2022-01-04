package com.maxporj.purebbs_compose.config

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import com.google.gson.Gson
import com.maxporj.purebbs_compose.net.HttpApi
import com.maxporj.purebbs_compose.net.HttpData
import com.maxporj.purebbs_compose.ui.detail.DetailDao
import com.maxporj.purebbs_compose.ui.post.Post
import com.maxporj.purebbs_compose.ui.post.PostDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MyRepository(
    private val viewModelScope: CoroutineScope,
    private val postDao: PostDao,
    private val detailDao: DetailDao,
    private val httpApi: HttpApi
) {

//    private var _posts: MutableState<List<Post>> =  postDao.getPostList().observeAsState()
    val posts: Flow<List<Post>?> = postDao.getPostList()

    init {

    }

    fun load(){
        postBoundaryGetMore()
    }


    private suspend fun httpGetMore(): HttpData.PostListRet?{

        var data: HttpData.PostListRet? = null

        val postCount = postDao.getPostCount()
        Log.d("PureBBS", "<PostBoundaryCallback> getPostCount(): $postCount")
        val query = HttpData.PostListQuery(
            query = if (Config.categoryCurrentLive.value == Config.CATEGORY_ALL || Config.categoryCurrentLive.value == null)
                null //category of all
            else HttpData.PostListQuery.Category(
                category = Config.categoryCurrentLive.value!!
            ),
            options = HttpData.PostListQuery.Options(
                offset = postCount,
                limit = 20,
                sort = HttpData.PostListQuery.Options.Sort(allUpdated = -1),
                select = "source oauth title content postId author authorId commentNum likeUser updated created avatarFileName lastReplyId lastReplyName lastReplyTime allUpdated stickTop category anonymous extend"
            )
        )
        val queryStr = Gson().toJson(query)
        Log.d("PureBBS", "<PostBoundaryCallback> queryStr: $queryStr")
        try {
            Log.d("PureBBS", "<PostBoundaryCallback> before httpApi.getPostByPaginate")
            data = httpApi.getPostByPaginate(queryStr)
            Log.d("PureBBS", "<PostBoundaryCallback> after httpApi.getPostByPaginate")
        } catch (he: HttpException) {
            Log.d("PureBBS", "<PostBoundaryCallback> catch HttpException")
            Log.d("PureBBS", he.toString())

        } catch (throwable: Throwable) {
            Log.d("PureBBS", "<PostBoundaryCallback> catch Throwable")
            Log.d("PureBBS", throwable.toString())

        }
        return data
    }

    private fun postBoundaryGetMore(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = httpGetMore()
            if(data != null){
                postDao.insertList(data.data)
            }
        }
    }
}