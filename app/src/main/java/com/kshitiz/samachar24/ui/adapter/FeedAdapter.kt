package com.kshitiz.samachar24.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kshitiz.samachar24.data.local.entity.NewsItem
import com.kshitiz.samachar24.databinding.ItemFeedBinding
import com.kshitiz.samachar24.util.DateTimeUtils

class FeedAdapter : ListAdapter<NewsItem, FeedAdapter.ViewHolder>(FeedDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.bind(item)

    }

    class FeedDiffCallback : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.id == newItem.id || oldItem.rssItem?.link == newItem.rssItem?.link
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }

    }


    inner class ViewHolder(private val binding: ItemFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsItem) {
            binding.apply {
                val rssItem = item.rssItem!!
                title.text = rssItem.title
                source.text = item.source
                date.text = rssItem.pubDate?.let { DateTimeUtils.getRelativeTime(it) } ?: ""
                Glide.with(root.context)
                    .load(rssItem.image)
                    .error(Glide.with(root.context).load(item.sourceImg))
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache the final image
                    .into(imageView)
            }
        }


    }

}