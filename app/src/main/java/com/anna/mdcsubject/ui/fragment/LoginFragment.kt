package com.anna.mdcsubject.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.anna.mdcsubject.NavigationHost
import com.anna.mdcsubject.R
import com.anna.mdcsubject.databinding.FragmentLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding

    private var btnLogin: Button? = null
    private var usernameEditText: TextInputEditText? = null
    private var usernameInputText: TextInputLayout? = null
    private var passwordEditText: TextInputEditText? = null
    private var passwordInputText: TextInputLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        inputTextValid()
        passwordEyeVisibility()
    }

    override fun onResume() {
        super.onResume()

        usernameEditText?.doOnTextChanged { text, _, _, _ ->
            if (text != null && text.isNotEmpty()) {
                usernameInputText?.error = null
            }
        }

        passwordEditText?.doOnTextChanged { text, _, _, _ ->
            if ( text != null && text.length >= 8) {
                passwordInputText?.error = null
            }
        }
    }

    private fun initView() {
        btnLogin = binding?.btnLogin
        usernameEditText = binding?.usernameEditText
        usernameInputText = binding?.usernameInputText
        passwordEditText = binding?.passwordEditText
        passwordInputText = binding?.passwordInputText
    }


    private fun inputTextValid() {

        btnLogin?.setOnClickListener {
            if (isUserNameValid(usernameEditText?.text) && isPasswordValid(passwordEditText?.text)) {
                // ????????????
                (activity as NavigationHost).navigateTo(ProductCardsFragment(), false)
            } else {
                when {
                    isUserNameValid(usernameEditText?.text) -> {
                        // ????????????UserName
                        passwordInputText?.error = "???????????????8????????????"
                    }
                    isPasswordValid(passwordEditText?.text) -> {
                        // ????????????Password
                        usernameInputText?.error = "?????????????????????????????????"
                    }
                    else -> {
                        // ???????????????
                        usernameInputText?.error = "?????????????????????????????????"
                        passwordInputText?.error = "???????????????8????????????"
                    }
                }
            }
        }
    }

    private fun passwordEyeVisibility() {
//        val editText = passwordInputText?.editText

//        // ????????????????????????
//        editText?.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
//        passwordInputText?.endIconDrawable = context?.let { AppCompatResources.getDrawable(it, R.drawable.ic_open_eye) }

        passwordInputText?.setEndIconOnClickListener {

            // ??????????????? PasswordTransformationMethod ??????????????????
            val hasPasswordTransformation =
                passwordEditText?.transformationMethod is PasswordTransformationMethod
            if (hasPasswordTransformation) {
                passwordEditText?.transformationMethod = null
            } else {
                passwordEditText?.transformationMethod = PasswordTransformationMethod.getInstance()
            }

            // ???????????????????????????????????? endIconDrawable
            setCustomizeEndIcon(passwordEditText)

        }
    }


    private fun setCustomizeEndIcon(editText: EditText?) {
        val passwordSource =
            editText?.transformationMethod?.getTransformation(editText.text, editText)

        if (passwordSource == null) {
            // ????????????
            passwordInputText?.endIconDrawable = context?.let {
                AppCompatResources.getDrawable(it, R.drawable.ic_open_eye)
            }
        } else {
            // ????????????
            passwordInputText?.endIconDrawable = context?.let {
                AppCompatResources.getDrawable(it, R.mipmap.icon_closed_eye)
            }
        }

    }


    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }

    private fun isUserNameValid(text: Editable?): Boolean {
        return text != null && text.isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}