package com.anna.mdcsubject

import android.text.Editable

class LoginVerify {

    fun isUserNameValid(text: String): Boolean {
        return text.isNotEmpty() && text.length >= 8
    }

    fun isPasswordValid(text: String): Boolean {
        return text.isNotEmpty() && text.length >= 8
    }

    fun isUserNameAndPasswordValid(name: String, password: String): Boolean {
        return isUserNameValid(name) && isPasswordValid(password)
    }
}