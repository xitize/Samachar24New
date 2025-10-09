package com.kshitiz.samachar24.ui.screen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.gson.Gson
import com.kshitiz.samachar24.R
import com.kshitiz.samachar24.data.local.entity.NewsItem
import com.kshitiz.samachar24.databinding.ActivityDetailBinding
import com.kshitiz.samachar24.util.DateTimeUtils
import com.kshitiz.samachar24.util.TextUtils

class DetailFeed : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val inset = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.root.setPadding(0, inset.top, 0, 0)
            insets
        }
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        populateUi()
    }

    private fun populateUi() {
        val newJson = intent.getStringExtra("NEWS")
        newJson?.let { json ->
            val newsItem = gson.fromJson(json, NewsItem::class.java)
            binding.apply {
                tvTitle.text = TextUtils.parseHtml(newsItem.rssItem?.title ?: "")
                dateDetail.text = DateTimeUtils.getRelativeTime(newsItem.rssItem?.pubDate ?: "")
                Glide.with(this@DetailFeed)
                    .load(newsItem.rssItem?.image)
                    .error(Glide.with(this@DetailFeed).load(newsItem.sourceImg))
                    .into(detailImageView)
                val content = newsItem.rssItem?.content ?: newsItem.rssItem?.description
                contentDetail.text = TextUtils.parseHtml(content ?: "")
                authorTv.text = newsItem.rssItem?.author ?: newsItem.source
                // Set categories as chips
                val categories = newsItem.rssItem?.categories
                if (!categories.isNullOrEmpty()) {
                    categoryChipGroup.isVisible = true
                    categoryChipGroup.removeAllViews()

                    categories.forEach { category ->
                        val chip = Chip(
                            ContextThemeWrapper(
                                this@DetailFeed,
                                R.style.Widget_App_SmallChip
                            ), null, 0
                        )
                        chip.isClickable = false
                        chip.isCheckable = false
                        chip.isFocusable = false
                        chip.text = category
                        categoryChipGroup.addView(chip)
                    }
                } else {
                    categoryChipGroup.isVisible = false
                }
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.title = ""
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Use recommended onBackPressed replacement
        }
    }
}