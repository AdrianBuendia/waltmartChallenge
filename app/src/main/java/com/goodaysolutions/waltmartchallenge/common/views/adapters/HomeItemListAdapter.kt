package com.goodaysolutions.waltmartchallenge.common.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.goodaysolutions.waltmartchallenge.core.data.api.models.responses.Result
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.goodaysolutions.waltmartchallenge.R
import com.goodaysolutions.waltmartchallenge.common.extensions.setFormattedCurrency
import com.goodaysolutions.waltmartchallenge.core.data.local.Item
import com.goodaysolutions.waltmartchallenge.databinding.LayoutCommonItemBinding
import javax.inject.Inject

class HomeItemListAdapter @Inject constructor() :
    ListAdapter<Item, HomeItemListAdapter.ItemViewHolder>(
        DiffCallback
    ) {

    var onItemClickListener: ((item: Item) -> Unit)? = null

    inner class ItemViewHolder(
        private val binding: LayoutCommonItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                tvValue.text =
                    binding.root.context.getString(
                        R.string.home_item_label_value,
                        item.title,
                        item.price.setFormattedCurrency(false)
                    )

                Glide.with(iv)
                    .load(item.thumbnail)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv)

                cv.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = LayoutCommonItemBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}