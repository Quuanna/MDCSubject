package com.anna.mdcsubject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.anna.mdcsubject.NavigationHost
import com.anna.mdcsubject.R
import com.anna.mdcsubject.databinding.FragmentNotificationSettingBinding
import com.anna.mdcsubject.databinding.FragmentProductCardsBinding

class NotificationSetFragment: Fragment() {

    private var _binding: FragmentNotificationSettingBinding? = null
    private val binding  get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNotificationSettingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding?.btnBack?.setOnClickListener {
            (activity as NavigationHost).navigateTo(ProductCardsFragment(), false)
        }


        // 推播通知
        binding?.switchPause?.setOnCheckedChangeListener{ _ , isChecked->
            if (isChecked) {
                // 開啟時
                binding?.switchPause?.thumbIconDrawable=
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_check)
            } else {
                // 關閉時
                binding?.switchPause?.thumbIconDrawable=
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_close)
            }
        }

        // 電子郵件通知
        binding?.switchEmail?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(requireContext(), "點擊電子郵件通知", Toast.LENGTH_SHORT).show()
            }
        }
    }
}