package com.kshitiz.samachar24.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.kshitiz.samachar24.data.local.entity.NewsItem
import com.kshitiz.samachar24.databinding.ActivityDetailFeedBinding
import com.kshitiz.samachar24.util.TextUtils

class DetailFeed : AppCompatActivity() {
    val binding: ActivityDetailFeedBinding by lazy {
        ActivityDetailFeedBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val newJson = intent.getStringExtra("NEWS")
        val newsItem = Gson().fromJson(newJson, NewsItem::class.java)
        binding.apply {
            titleDetail.text = TextUtils.parseHtml(newsItem.rssItem?.title ?: "")
            dateDetail.text = newsItem.rssItem?.pubDate
            Glide.with(this@DetailFeed)
                .load(newsItem.rssItem?.image)
                .error(Glide.with(this@DetailFeed).load(newsItem.sourceImg))
                .into(detailImageView)
            val content = newsItem.rssItem?.content ?: newsItem.rssItem?.description
            contentDetail.text = TextUtils.parseHtml(content ?: "")
            authorTv.text = newsItem.rssItem?.author
            toolbar.title = newsItem.source
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
        }


    }
}
