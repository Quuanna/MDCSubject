package com.anna.mdcsubject.ui.dialogFragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.anna.mdcsubject.databinding.FullScreenDialogFragmentBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class FullScreenDialogFragment : DialogFragment() {

    private var _binding: FullScreenDialogFragmentBinding? = null
    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        val dialog = super.onCreateDialog(savedInstanceState)
        val window = dialog.window
        window?.requestFeature(Window.FEATURE_NO_TITLE)
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar_Fullscreen)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FullScreenDialogFragmentBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding?.btnClose?.setOnClickListener {
            dialog?.dismiss()
        }

        binding?.btnSave?.setOnClickListener {
            Toast.makeText(context, "實作儲存", Toast.LENGTH_LONG).show()
        }


        val exampleCountryItem = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        (binding?.textInputCountry?.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(exampleCountryItem)

        val exampleCityItems = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        (binding?.textInputCity?.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(exampleCityItems)

    }
}