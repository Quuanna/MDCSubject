package com.anna.mdcsubject

class LoginVerifyManager {

    interface VerifyListener{
        fun success()
        fun fail()
    }

    enum class State(val message: String) {
        WRONG_NAME("請輸入帳號"),
        WRONG_PASSWORD("請輸入密碼"),
        WRONG_ENTER_PASSWORD_ERROR("請輸入正確長度密碼");
    }

    fun checkLoginResult(name: String, password: String, listener: VerifyListener) {
        return if (isUserNameAndPasswordValid(name, password)){
            listener.success()
        } else {
            listener.fail()
        }
    }

    fun checkEnterNameState(name: String?): String? {
        return when {
            name.isNullOrEmpty() -> State.WRONG_NAME.message
            else -> null
        }
    }

    fun checkEnterPasswordState(password: String?): String? {
        return when {
            password.isNullOrEmpty() -> State.WRONG_PASSWORD.message
            !checkLength(password) -> State.WRONG_ENTER_PASSWORD_ERROR.message
            else -> null
        }
    }

    fun checkLength(text: String?): Boolean {
        return text?.isNotEmpty() == true && text.length >= 8
    }

    fun isUserNameAndPasswordValid(name: String, password: String): Boolean {
        return name.isNotBlank() && checkLength(password)
    }
}