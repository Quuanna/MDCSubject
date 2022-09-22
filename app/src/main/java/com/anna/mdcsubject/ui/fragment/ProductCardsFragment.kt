package com.anna.mdcsubject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
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

class ProductCardsFragment : Fragment() {

    private var _binding: FragmentProductCardsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductCardsBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initView()
    }

    private fun initToolbar() {
        binding?.topAppBar?.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.search -> {
                    Toast.makeText(context, "測試 menu item search", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.filter -> {
                    Toast.makeText(context, "測試 menu item filter", Toast.LENGTH_LONG).show()
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
        binding?.recyclerView?.adapter = CardRecyclerViewAdapter(listOf(1, 2, 3, 4, 5, 6, 7, 8),CardsType.CARDS ,itemClickListener()) //2, 3, 4, 5, 6, 7, 8
        binding?.recyclerView?.layoutManager =
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

        binding?.toggleButton?.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btn_grid -> {
                        binding?.recyclerView?.adapter = CardRecyclerViewAdapter(listOf(1,2, 3, 4, 5, 6, 7, 8),CardsType.CARDS , itemClickListener())
                        binding?.recyclerView?.layoutManager =
                            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
                    }
                    R.id.btn_vertical -> {
                        binding?.recyclerView?.adapter = CardRecyclerViewAdapter(listOf(1,2, 3, 4, 5, 6, 7, 8),CardsType.VERTICAL , itemClickListener())
                        binding?.recyclerView?.layoutManager =
                            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    }
                }
            }
        }
    }

    private fun itemClickListener(): ((position: Int) -> Unit) {
        return { position ->

            Toast.makeText(context,"點擊第幾項目 : $position", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

