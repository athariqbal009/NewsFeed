package com.android.newsfeed.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.newsfeed.data.model.Row
import com.android.newsfeed.databinding.FeedListItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

class FeedAdapter: RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    private val callback = object :DiffUtil.ItemCallback<Row>() {
        override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem.imageHref == newItem.imageHref
        }

        override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding = FeedListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val feed = differ.currentList[position]
        holder.bind(feed)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class FeedViewHolder(private val binding: FeedListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(feed: Row){
            binding.textViewTitle.text = feed.title
            binding.textViewDescription.text = feed.description
            Glide.with(binding.imageView.context).load(feed.imageHref).into(binding.imageView)
        }
    }

    private var onItemClickListener :((Row)->Unit)?=null

    fun setOnItemClickListener(listener : (Row)->Unit){
        onItemClickListener = listener
    }
}