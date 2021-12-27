package com.maxporj.purebbs_compose.config

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface ConfigDao {
    @Query("SELECT * from category_table ORDER BY idStr ASC")
    fun getCategoryList(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList( list: List<Category>)
}