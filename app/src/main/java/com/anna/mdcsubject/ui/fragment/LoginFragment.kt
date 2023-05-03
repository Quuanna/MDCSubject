package com.anna.mdcsubject.ui.fragment

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.anna.mdcsubject.LoginVerify
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
    private val verifty by lazy { LoginVerify() }

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
        val userName = usernameEditText?.text.toString()
        val password = passwordEditText?.text.toString()

        btnLogin?.setOnClickListener {
            if (verifty.isUserNameValid(userName)
                && verifty.isPasswordValid(password)) {
                // 都有輸入
                (activity as NavigationHost).navigateTo(ProductCardsFragment(), false)
            } else {
                when {
                    verifty.isUserNameValid(userName) -> {
                        // 只有輸入UserName
                        passwordInputText?.error = "請輸入長度8碼的密碼"
                    }
                    verifty.isPasswordValid(password) -> {
                        // 只有輸入Password
                        usernameInputText?.error = "請輸入最少一個字的名字"
                    }
                    verifty.isUserNameAndPasswordValid(userName, password) -> {
                        // 都沒有輸入
                        usernameInputText?.error = "請輸入最少一個字的名字"
                        passwordInputText?.error = "請輸入長度8碼的密碼"
                    }
                }
            }
        }
    }

    private fun passwordEyeVisibility() {
//        val editText = passwordInputText?.editText

//        // 預設顯示密碼明文
//        editText?.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
//        passwordInputText?.endIconDrawable = context?.let { AppCompatResources.getDrawable(it, R.drawable.ic_open_eye) }

        passwordInputText?.setEndIconOnClickListener {

            // 檢查是否有 PasswordTransformationMethod 密碼轉換方法
            val hasPasswordTransformation =
                passwordEditText?.transformationMethod is PasswordTransformationMethod
            if (hasPasswordTransformation) {
                passwordEditText?.transformationMethod = null
            } else {
                passwordEditText?.transformationMethod = PasswordTransformationMethod.getInstance()
            }

            // 取得密碼文字來設定字定義 endIconDrawable
            setCustomizeEndIcon(passwordEditText)

        }
    }


    private fun setCustomizeEndIcon(editText: EditText?) {
        val passwordSource =
            editText?.transformationMethod?.getTransformation(editText.text, editText)

        if (passwordSource == null) {
            // 密碼明文
            passwordInputText?.endIconDrawable = context?.let {
                AppCompatResources.getDrawable(it, R.drawable.ic_open_eye)
            }
        } else {
            // 密碼密文
            passwordInputText?.endIconDrawable = context?.let {
                AppCompatResources.getDrawable(it, R.mipmap.icon_closed_eye)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}