package com.anna.mdcsubject.ui.adapter.viewHolder

import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.anna.mdcsubject.R
import com.anna.mdcsubject.databinding.ItemCardsGridBinding
import com.anna.mdcsubject.databinding.ItemCardsVerticalBinding

class GridViewHolder(private val binding: ViewBinding, private val onItemClickListener: ((position: Int) -> Unit)?) : RecyclerView.ViewHolder(binding.root) {


    private var isCanFavorite = true // 是否可以被收藏

    fun bind(position: Int){

        when (binding) {
            is ItemCardsGridBinding -> {
                // 呈現網格狀的
                binding.image.setBackgroundResource(R.color.gray)
                binding.tvTitle.text = "Title"
                binding.tvSubtitle.text = "Subtitle"
                binding.filledCard.setOnClickListener {
                    onItemClickListener?.invoke(position)
                }
                binding.iconButton.setOnClickListener {
                    if (isCanFavorite) {
                        binding.iconButton.icon = ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_select_favorite, null)
                        isCanFavorite = false
                    } else {
                        binding.iconButton.icon = ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_favorite, null)
                        isCanFavorite = true
                    }
                }
            }

            is ItemCardsVerticalBinding -> {
                // 呈現垂直狀的
                binding.image.setBackgroundResource(R.color.gray)
                binding.tvTitle.text = "Title"
                binding.tvSubtitle.text = "Subtitle"
                binding.filledCard.setOnClickListener {
                    onItemClickListener?.invoke(position)
                }
                binding.iconButton.setOnClickListener {
                    if (isCanFavorite) {
                        binding.iconButton.icon = ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_select_favorite, null)
                        isCanFavorite = false
                    } else {
                        binding.iconButton.icon = ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_favorite, null)
                        isCanFavorite = true
                    }
                }
            }
        }
    }
}