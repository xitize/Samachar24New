package com.kshitiz.samachar24.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.prof18.rssparser.model.RssItem

@Entity(tableName = "news_items")
@TypeConverters(RssItemConverter::class)
data class NewsItem(
    @PrimaryKey val id: String,
    val rssItem: RssItem? = null,
    val source: String? = null,
    val sourceImg: String? = null
)

object RssItemConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromRssItem(rssItem: RssItem?): String? {
        return rssItem?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toRssItem(json: String?): RssItem? {
        return json?.let { gson.fromJson(it, RssItem::class.java) }
    }
}
