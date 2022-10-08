package com.anna.mdcsubject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anna.mdcsubject.ui.adapter.CardRecyclerViewAdapter
import com.anna.mdcsubject.CardsType
import com.anna.mdcsubject.NavigationHost
import com.anna.mdcsubject.R
import com.anna.mdcsubject.databinding.FragmentProductCardsBinding
import com.anna.mdcsubject.ui.bottomSheet.ModalBottomSheet
import com.anna.mdcsubject.ui.dialogFragment.FullScreenDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProductCardsFragment : Fragment() {

    private var _binding: FragmentProductCardsBinding? = null
    private val binding get() = _binding
    private val menuItemClick = OnMenuItemClick()
    private val defaultDataList = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8)
    private var favoriteDataList = arrayListOf<Int>()


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
                    if (activity != null) {
                        modalBottomSheet.show(
                            requireActivity().supportFragmentManager,
                            ModalBottomSheet.TAG
                        )
                    }
                    true
                }
                else -> false
            }
        }

        binding?.topAppBar?.setNavigationOnClickListener {
            // 新增 Navigation drawer
            binding?.drawerLayout?.open()
        }

        binding?.navigationView?.setNavigationItemSelectedListener { menuItem ->
            // menu點處理選定
            when (menuItem.itemId) {
                R.id.notification -> { //通知
                    if (activity != null) {
                        binding?.drawerLayout?.close()
                        (activity as NavigationHost).navigateTo(NotificationSetFragment(), false)
                    }
                }
                R.id.privacySet -> { // 隱私設定
                    Toast.makeText(context, "隱私設定", Toast.LENGTH_SHORT).show()
                }
                R.id.account -> { // 用戶
                    // 使用者資料點擊開啟Full-screen dialogs
                    if (activity != null) {
                        binding?.drawerLayout?.close()
                        requireActivity().supportFragmentManager.let {
                            FullScreenDialogFragment().show(it, "")
                        }
                    }
                }
                R.id.about -> { // 關於
                    Toast.makeText(context, "關於", Toast.LENGTH_SHORT).show()
                }
                R.id.logout -> { // 登出
                    Toast.makeText(context, "登出", Toast.LENGTH_SHORT).show()
                }
            }
            true
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

    private fun setRecycleViewLayout(
        dataList: List<Int>,
        type: CardsType,
        isFavorite: Boolean? = false
    ) {
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
            Toast.makeText(context, "點擊第幾項目 : ${position + 1}", Toast.LENGTH_SHORT).show()
            if (isFavorite != null && isFavorite == true) {
                // 收藏卡片
                favoriteDataList.add(position + 1)
            } else if (isFavorite != null && isFavorite == false) {
                favoriteDataList.remove(position + 1)
            }
        }
    }

    private inner class OnMenuItemClick :
        androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
        override fun onMenuItemClick(item: MenuItem?): Boolean {
            return when (item?.itemId) {
                R.id.mainPage -> { // 首頁
                    binding?.toggleButton?.visibility = View.VISIBLE
                    if (isCheckGridView()) {
                        setRecycleViewLayout(defaultDataList, CardsType.GRID)
                    } else {
                        setRecycleViewLayout(defaultDataList, CardsType.VERTICAL)
                    }
                    true
                }
                R.id.collect -> { // 收藏
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
                R.id.account -> { // 用戶
                    // 使用者資料點擊開啟Full-screen dialogs
                    if (activity != null) {
                        requireActivity().supportFragmentManager.let {
                            FullScreenDialogFragment().show(it, "")
                        }
                    }
                    true
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


