package com.kshitiz.samachar24.repository

import android.util.Log
import com.kshitiz.samachar24.data.local.dao.NewsDao
import com.kshitiz.samachar24.data.local.entity.NewsItem
import com.kshitiz.samachar24.usecase.FeedsUrl
import com.prof18.rssparser.RssParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(val dao: NewsDao) {
    suspend fun refreshFeeds() {
        val rssParser = RssParser()
        val allItems = withContext(Dispatchers.IO) {
            coroutineScope {
                FeedsUrl.urlNp.map { url ->
                    async { // Each feed is fetched and parsed in a separate coroutine
                        runCatching {
                            val channel = rssParser.getRssChannel(url)
                            val sourceName = url.substringAfter("https://").substringBefore(".com")
                            channel.items
                                .filter { it.guid != null }
                                .map { NewsItem(it.guid!!, it, source = sourceName) }
                        }.getOrElse { throwable ->
                            Log.e(
                                "NewsRepository",
                                "Failed to fetch or parse RSS feed from $url",
                                throwable
                            )
                            emptyList()
                        }
                    }
                }.awaitAll().flatten()
            }
        }
        dao.insertAll(allItems)
    }

    fun getAllNewsFromDb() = dao.getAllNewsItems()

}
