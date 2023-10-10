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
import com.goodaysolutions.waltmartchallenge.databinding.LayoutCommonItemBinding
import javax.inject.Inject

class HomeItemListAdapter @Inject constructor() :
    ListAdapter<Result, HomeItemListAdapter.ResultViewHolder>(
        DiffCallback
    ) {

    var onItemClickListener: ((result: Result) -> Unit)? = null

    inner class ResultViewHolder(
        private val binding: LayoutCommonItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            binding.apply {
                tvValue.text =
                    binding.root.context.getString(
                        R.string.home_item_label_value,
                        result.title,
                        result.price.setFormattedCurrency(false)
                    )

                Glide.with(iv)
                    .load(result.thumbnail)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv)

                cv.setOnClickListener {
                    onItemClickListener?.invoke(result)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val binding = LayoutCommonItemBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}