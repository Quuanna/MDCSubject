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
import com.anna.mdcsubject.LoginVerifyManager
import com.anna.mdcsubject.NavigationHost
import com.anna.mdcsubject.R
import com.anna.mdcsubject.databinding.FragmentLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding

    private var btnLogin: Button? = null
    private var usernameEditText: TextInputEditText? = null
    private var usernameInputText: TextInputLayout? = null
    private var passwordEditText: TextInputEditText? = null
    private var passwordInputText: TextInputLayout? = null
    private val mVerify by lazy { LoginVerifyManager() }

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
            if (!text.isNullOrEmpty()) {
                usernameInputText?.error = null
            }
        }

        passwordEditText?.doOnTextChanged { text, _, _, _ ->
            if (mVerify.checkLength(text.toString())) {
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
        val userName = usernameEditText?.text
        val password = passwordEditText?.text

        btnLogin?.setOnClickListener {
            mVerify.checkLoginResult(userName.toString(), password.toString(), object : LoginVerifyManager.VerifyListener{
                override fun success() {
                    (activity as NavigationHost).navigateTo(ProductCardsFragment(), false)
                }

                override fun fail() {
                    usernameInputText?.error = mVerify.checkEnterNameState(name = userName.toString())
                    passwordInputText?.error = mVerify.checkEnterPasswordState(password = password.toString())
                }
            })
        }
    }

    private fun passwordEyeVisibility() {
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