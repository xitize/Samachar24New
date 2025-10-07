package com.kshitiz.samachar24.data.repository

import android.content.Context
import android.util.Log
import com.kshitiz.samachar24.data.local.dao.NewsDao
import com.kshitiz.samachar24.data.local.entity.NewsItem
import com.kshitiz.samachar24.usecase.FeedsUrl
import com.kshitiz.samachar24.util.DateTimeUtils
import com.kshitiz.samachar24.util.SharedPrefManager
import com.prof18.rssparser.RssParserBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes

class NewsRepository @Inject constructor(
    val dao: NewsDao
) {
    companion object {
        private const val LAST_UPDATE_KEY = "last_update_timestamp"
        private val REFRESH_INTERVAL = 15.minutes.inWholeMilliseconds
    }

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var applicationContext: Context

    suspend fun refreshFeeds() {
        val sharePref = SharedPrefManager(applicationContext)
        Log.i("NewsRepository", "Refreshing feeds...")
        val lastUpdate = sharePref.getLong(LAST_UPDATE_KEY, 0L)
        if (System.currentTimeMillis() - lastUpdate < REFRESH_INTERVAL) {
            Log.i("NewsRepository", "Skipping refresh, data is recent enough.")
            return
        }

        val rssParser = RssParserBuilder(callFactory = okHttpClient).build()
        val allItems = withContext(Dispatchers.IO) {
            coroutineScope {
                FeedsUrl.nepaliFeeds.map { feedItem ->
                    async { // Each feed is fetched and parsed in a separate coroutine
                        runCatching {
                            val channel = rssParser.getRssChannel(feedItem.url)
                            Log.i("refreshFeeds", " Fetched RSS feed from $feedItem")
                            val sourceName = feedItem.name
                            channel.items
                                .filter { it.guid != null }
                                .map {
                                    NewsItem(
                                        it.guid!!,
                                        it,
                                        source = sourceName,
                                        sourceImg = feedItem.image
                                    )
                                }
                        }.getOrElse { throwable ->
                            Log.e(
                                "NewsRepository",
                                "Failed to fetch or parse RSS feed from $feedItem",
                                throwable
                            )
                            emptyList()
                        }
                    }
                }.awaitAll().flatten()
            }
        }
        dao.insertAll(allItems)
        sharePref.saveLong(LAST_UPDATE_KEY, System.currentTimeMillis())
        Log.i("NewsRepository", "Feeds refreshed and timestamp updated.")
    }

    suspend fun getAllNewsFromDb() = withContext(Dispatchers.IO) {
        dao.getAllNewsItems().map { items ->
            items.sortedByDescending {
                DateTimeUtils.getTimeFromRssDate(
                    it.rssItem?.pubDate ?: ""
                )
            }
        }
    }
}
