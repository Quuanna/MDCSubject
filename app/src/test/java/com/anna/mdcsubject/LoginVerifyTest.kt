package com.anna.mdcsubject

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoginVerifyTest {

    lateinit var loginVerify: LoginVerify

    @Before
    fun setup() {
        loginVerify = LoginVerify()
    }


    @Test
    fun 單獨只輸入名稱驗證() {
        val actual = loginVerify.isUserNameValid("俊錡是豬豬豬豬豬")
        Assert.assertTrue(actual)
    }

    @Test
    fun 單獨只輸入密碼驗證() {
        val actual = loginVerify.isPasswordValid("12345678")
        Assert.assertTrue(actual)
    }

    @Test
    fun 沒有輸入名稱() {
        val actual = loginVerify.isUserNameValid("")
        Assert.assertFalse(actual)
    }


    @Test
    fun 沒有輸入密碼() {
        val actual = loginVerify.isPasswordValid("")
        Assert.assertFalse(actual)
    }


    @Test
    fun 輸入名稱長度不對() {
        val actual = loginVerify.isPasswordValid("俊錡是豬")
        Assert.assertFalse(actual)
    }


    @Test
    fun 輸入密碼長度不對() {
        val actual = loginVerify.isPasswordValid("1234")
        Assert.assertFalse(actual)
    }


    @Test
    fun 沒有輸入名稱和密碼() {
        val actual = loginVerify.isUserNameAndPasswordValid("", "")
        Assert.assertFalse(actual)
    }

    @Test
    fun 名稱和密碼都輸入但長度度不對() {
        val actual = loginVerify.isUserNameAndPasswordValid("俊錡是豬", "123")
        Assert.assertFalse(actual)
    }

    @Test
    fun 名稱和密碼都輸入驗證正確() {
        val actual = loginVerify.isUserNameAndPasswordValid("俊錡是豬豬豬豬豬", "12345678")
        Assert.assertTrue(actual)
    }
}