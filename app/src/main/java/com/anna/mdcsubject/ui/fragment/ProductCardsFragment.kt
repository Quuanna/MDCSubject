package com.anna.mdcsubject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anna.mdcsubject.ui.adapter.CardRecyclerViewAdapter
import com.anna.mdcsubject.CardsType
import com.anna.mdcsubject.R
import com.anna.mdcsubject.databinding.FragmentProductCardsBinding
import com.anna.mdcsubject.ui.bottomSheet.ModalBottomSheet
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProductCardsFragment : Fragment() {

    private var _binding: FragmentProductCardsBinding? = null
    private val binding get() = _binding
    private val defaultDataList = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8)
    private var favoriteDataList = arrayListOf<Int>()
    private val menuItemClick = OnMenuItemClick()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductCardsBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initToolbarClick()

    }

    private fun initToolbarClick() {
        binding?.topAppBar?.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.search -> {
                    Toast.makeText(context, "測試 menu item search", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.filter -> {
                    Toast.makeText(context, "開啟Bottom Sheet", Toast.LENGTH_LONG).show()
                    // 開啟Bottom Sheet
                    val modalBottomSheet = ModalBottomSheet()
                    if(context != null) {
                        modalBottomSheet.show(requireActivity().supportFragmentManager, ModalBottomSheet.TAG)
                    }
                    true
                }
                else -> false
            }
        }

        binding?.topAppBar?.setNavigationOnClickListener {
            Toast.makeText(context, "測試Navigation", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        setRecycleViewLayout(defaultDataList, CardsType.GRID)
        binding?.bottomAppBar?.setOnMenuItemClickListener(menuItemClick)


        binding?.fab?.setOnClickListener {
            // 新增Cards
            if (isCheckGridView()) {
                defaultDataList.add(9)
                setRecycleViewLayout(defaultDataList, CardsType.GRID)
            } else {
                defaultDataList.add(9)
                setRecycleViewLayout(defaultDataList, CardsType.VERTICAL)
            }
        }

        binding?.toggleButton?.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btn_grid -> setRecycleViewLayout(defaultDataList, CardsType.GRID)
                    R.id.btn_vertical -> setRecycleViewLayout(defaultDataList, CardsType.VERTICAL)
                }
            }
        }
    }

    private fun setRecycleViewLayout(dataList: List<Int>, type: CardsType, isFavorite: Boolean? = false) {
        binding?.recyclerView?.adapter = CardRecyclerViewAdapter(
            dataList,
            favoriteDataList,
            type,
            isFavorite,
            setOnItemClickListener()
        )

        when (type) {
            CardsType.GRID -> binding?.recyclerView?.layoutManager =
                GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            CardsType.VERTICAL -> binding?.recyclerView?.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun setOnItemClickListener(): ((position: Int, isFavorite: Boolean?) -> Unit) {
        return { position, isFavorite ->
            Toast.makeText(context, "點擊第幾項目 : ${position+1}", Toast.LENGTH_SHORT).show()
            if (isFavorite != null && isFavorite == true) {
                // 收藏卡片
                favoriteDataList.add(position+1)
            } else if (isFavorite != null && isFavorite == false){
                favoriteDataList.remove(position+1)
            }
        }
    }

    private inner class OnMenuItemClick : androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
        override fun onMenuItemClick(item: MenuItem?): Boolean {
            return when (item?.itemId) {
                R.id.mainPage -> {
                    binding?.toggleButton?.visibility = View.VISIBLE
                    if (isCheckGridView()) {
                        setRecycleViewLayout(defaultDataList, CardsType.GRID)
                    } else {
                        setRecycleViewLayout(defaultDataList, CardsType.VERTICAL)
                    }
                    true
                }
                R.id.collect -> {
                    if (favoriteDataList.size != 0) {
                        binding?.toggleButton?.visibility = View.GONE

                        if (isCheckGridView()) {
                            setRecycleViewLayout(favoriteDataList, CardsType.GRID, true)
                        } else {
                            setRecycleViewLayout(favoriteDataList, CardsType.VERTICAL, true)
                        }
                        true

                    } else {
                        Toast.makeText(context, "沒有加入「我的最愛」的卡片", Toast.LENGTH_SHORT).show()
                        false
                    }
                }
                else -> false
            }
        }
    }

    // 判斷toggleButton目前切換的卡片類型
    private fun isCheckGridView(): Boolean {
       return binding?.toggleButton?.checkedButtonId == R.id.btn_grid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


