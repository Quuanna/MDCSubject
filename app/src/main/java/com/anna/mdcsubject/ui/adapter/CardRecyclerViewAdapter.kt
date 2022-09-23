package com.anna.mdcsubject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anna.mdcsubject.CardsType
import com.anna.mdcsubject.databinding.ItemCardsGridBinding
import com.anna.mdcsubject.databinding.ItemCardsVerticalBinding
import com.anna.mdcsubject.ui.adapter.viewHolder.GridViewHolder

class CardRecyclerViewAdapter(
    private val productList: List<Int>,
    private val favoriteDataList: ArrayList<Int>,
    private val type: CardsType,
    private val isFavorite: Boolean?,
    private var mItemButtonClickListener: ((position: Int, isFavorite: Boolean?) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (type) {
            CardsType.GRID -> {
                GridViewHolder(
                    ItemCardsGridBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    productList,
                    favoriteDataList,
                    isFavorite,
                    mItemButtonClickListener
                )
            }
            CardsType.VERTICAL -> {
                GridViewHolder(
                    ItemCardsVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    productList,
                    favoriteDataList,
                    isFavorite,
                    mItemButtonClickListener
                )
            }
        }
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GridViewHolder).bind()
    }

}


