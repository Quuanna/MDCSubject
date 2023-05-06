package com.anna.mdcsubject

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoginVerifyManagerTest {

    lateinit var loginVerify: LoginVerifyManager

    @Before
    fun setup() {
        loginVerify = LoginVerifyManager()
    }

    @Test
    fun 輸入密碼驗證() {
        val actual = loginVerify.checkLength("12345678")
        Assert.assertTrue(actual)
    }

    @Test
    fun 沒有輸入帳號() {
        val actual = loginVerify.checkLength("")
        Assert.assertFalse(actual)
    }

    @Test
    fun 沒有輸入密碼() {
        val actual = loginVerify.checkLength("")
        Assert.assertFalse(actual)
    }

    @Test
    fun 輸入密碼長度不對() {
        val actual = loginVerify.checkLength("1234")
        Assert.assertFalse(actual)
    }

    @Test
    fun 沒有輸入帳號和密碼() {
        val actual = loginVerify.isUserNameAndPasswordValid("", "")
        Assert.assertFalse(actual)
    }

    @Test
    fun 帳號和密碼都輸入但長度度不對() {
        val actual = loginVerify.isUserNameAndPasswordValid("測試", "123")
        Assert.assertFalse(actual)
    }

    @Test
    fun 帳號和密碼都輸入驗證正確() {
        val actual = loginVerify.isUserNameAndPasswordValid("測試", "12345678")
        Assert.assertTrue(actual)
    }

    @Test
    fun 沒有輸入帳號的錯誤訊息() {
        val actual = loginVerify.checkEnterNameState("")
        val expected = "請輸入帳號"
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun 沒有輸入密碼的錯誤訊息() {
        val actual = loginVerify.checkEnterPasswordState("")
        val expected = "請輸入密碼"
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun 輸入密碼的長度錯誤訊息() {
        val actual = loginVerify.checkEnterPasswordState("1234")
        val expected = "請輸入正確長度密碼"
        Assert.assertEquals(expected, actual)
    }
}