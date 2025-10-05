package com.kshitiz.samachar24.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kshitiz.samachar24.data.local.entity.NewsItem
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {
    @Query("SELECT * FROM news_items")
    fun getAllNewsItems(): Flow<List<NewsItem>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(newsItems: List<NewsItem>)

    @Query("DELETE FROM news_items")
    suspend fun deleteAll()

}