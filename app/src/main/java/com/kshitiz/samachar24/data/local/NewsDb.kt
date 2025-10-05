package com.kshitiz.samachar24.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kshitiz.samachar24.data.local.dao.NewsDao
import com.kshitiz.samachar24.data.local.entity.NewsItem
import com.kshitiz.samachar24.data.local.entity.RssItemConverter

@Database(entities = [NewsItem::class], version = 1, exportSchema = false)
abstract class NewsDb : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}

