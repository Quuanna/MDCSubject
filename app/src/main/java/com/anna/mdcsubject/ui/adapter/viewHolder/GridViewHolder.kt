package com.anna.mdcsubject.ui.adapter.viewHolder

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.anna.mdcsubject.R
import com.anna.mdcsubject.databinding.ItemCardsGridBinding
import com.anna.mdcsubject.databinding.ItemCardsVerticalBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class GridViewHolder(
    private val binding: ViewBinding,
    private val productList: List<Int>,
    private val favoriteDataList: ArrayList<Int>,
    private val isFavorite: Boolean?,
    private val onItemClickListener: ((position: Int, isFavorite: Boolean?) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    private var isCanFavorite = true // 是否可以被收藏

    fun bind(){

        when (binding) {
            is ItemCardsGridBinding -> {
                // 呈現網格狀的
                binding.image.setBackgroundResource(R.color.gray)
                binding.tvSubtitle.text = "Subtitle"


                // 設定tvTitle
                if (productList.isNotEmpty()) {
                    binding.tvTitle.text = productList[adapterPosition].toString()
                }

                // 設定加入愛心的卡片
                if (favoriteDataList.isNotEmpty()) {
                    if (favoriteDataList.indexOf(adapterPosition+1) != -1) {
                        binding.iconButton.icon = ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_select_favorite, null)
                        isCanFavorite = false
                    }

                    if (isFavorite!= null && isFavorite == true) {
                        binding.iconButton.visibility = View.GONE
                    }
                }

                binding.filledCard.setOnClickListener {
                    onItemClickListener?.invoke(adapterPosition, null)
                }

                // 切換是否加入愛心
                binding.iconButton.setOnClickListener {
                    if (isCanFavorite) {
                        binding.iconButton.icon = ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_select_favorite, null)
                        isCanFavorite = false
                        onItemClickListener?.invoke(adapterPosition, true)
                    } else {
                        binding.iconButton.icon = ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_favorite, null)
                        isCanFavorite = true
                        onItemClickListener?.invoke(adapterPosition, false)
                    }
                }
            }

            is ItemCardsVerticalBinding -> {
                // 呈現垂直狀的
                binding.image.setBackgroundResource(R.color.gray)
                binding.tvSubtitle.text = "Subtitle"


                // 設定tvTitle
                if (productList.isNotEmpty()) {
                    binding.tvTitle.text = productList[adapterPosition].toString()
                }

                // 設定加入愛心的卡片
                if (favoriteDataList.isNotEmpty()) {
                    if (favoriteDataList.indexOf(adapterPosition+1) != -1) {
                        binding.iconButton.icon = ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_select_favorite, null)
                        isCanFavorite = false
                    }

                    if (isFavorite!= null && isFavorite == true) {
                        binding.iconButton.visibility = View.GONE
                    }
                }

                binding.filledCard.setOnClickListener {
                    onItemClickListener?.invoke(adapterPosition, null)
                }

                // 切換是否加入愛心
                binding.iconButton.setOnClickListener {
                    if (isCanFavorite) {
                        binding.iconButton.icon = ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_select_favorite, null)
                        isCanFavorite = false
                        onItemClickListener?.invoke(adapterPosition, true)
                    } else {
                        binding.iconButton.icon = ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_favorite, null)
                        isCanFavorite = true
                        onItemClickListener?.invoke(adapterPosition, false)
                    }
                }
            }
        }
    }
}

