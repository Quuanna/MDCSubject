# MDCSubject
Material Design 3 Component

# 壹. MDC Components的Button和Text Field，實作「登錄頁面」
![KLcxwen](https://user-images.githubusercontent.com/36694083/191690504-f61354dc-b1a5-4eaa-ad6f-6297369061bc.gif)

## 使用 MDC Android Components
![https://ithelp.ithome.com.tw/upload/images/20220917/20144469lVKEWPPvqB.png](https://ithelp.ithome.com.tw/upload/images/20220917/20144469lVKEWPPvqB.png)

# 實作介紹
## Add the XML
### 1. 新增兩個Textfield，分別是輸入名字、密碼
- 每個欄位由一個TextInputLayout元素和一個TextInputEditText子欄位組成，文字欄位中的提示文字在`android:hint`屬性中指定提示文字，提示文字是當點擊時會自動縮小往上跑。
![https://ithelp.ithome.com.tw/upload/images/20220917/20144469ivsGzqXMTH.png](https://ithelp.ithome.com.tw/upload/images/20220917/20144469ivsGzqXMTH.png)
```Xml
<com.google.android.material.textfield.TextInputLayout
        android:id="@+id/textInput_username"
        style="@style/Widget.Material3.TextInputLayout.FilledBox"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="@string/login_hint_username">

        <com.google.android.material.textfield.TextInputEditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    </com.google.android.material.textfield.TextInputLayout>

		<!--password-->
    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/textInput_password"
        style="@style/Widget.Material3.TextInputLayout.FilledBox"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="@string/login_hint_password">

        <com.google.android.material.textfield.TextInputEditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>

    </com.google.android.material.textfield.TextInputLayout> 
```

### 2. trailing icon 設定顯示密碼、屏蔽密碼
- 在密碼TextInputEditText元素上將android:inputType屬性設定為“textPassword”。 可讓密碼欄位中隱藏輸入文字。
    ```
    <com.google.android.material.textfield.TextInputLayout
        ...
                app:endIconMode="password_toggle"
        ...>

            <com.google.android.material.textfield.TextInputEditText
               ...
                android:inputType="textPassword" />

        </com.google.android.material.textfield.TextInputLayout> 
    ```

### 3. 密碼欄位設定counter限制用戶可輸入文字長度
   
    <com.google.android.material.textfield.TextInputLayout
        ...
        app:counterEnabled="true"
        app:counterMaxLength="8">
        ...
    </com.google.android.material.textfield.TextInputLayout>
   
    
### 4. 名字、密碼的欄位設定用戶為輸入時提示Errors訊息
- 在Password TextInputLayout元素上將app:errorEnabled屬性設定為true。 這將為文字欄位下方的錯誤訊息新增的錯提示。
    
    ```
    <com.google.android.material.textfield.TextInputLayout
        ...
        app:errorEnabled="true">
        ...
    </com.google.android.material.textfield.TextInputLayout>
    ```
    
### 5. Button (Filled button)
```
<Button
   style="@style/Widget.Material3.Button"
   android:layout_width="wrap_content"
   android:layout_height="wrap_content"
   android:text="Filled button" />
```


## Add input validation 顯示 error提示訊息的邏輯判斷
點擊按鈕判斷是否顯示Error提示情境
### 實現邏輯：
當用戶在未輸入時點擊登入按鈕，此時顯示Error提示訊息警示，用戶有輸入時透過`doOnTextChange` 動態偵測用戶輸入關閉Error警示提示。

### 判斷邏輯：
- UserName、Password皆輸入完成
- UserName、Password皆未輸入完成
- 只有輸入UserName，Password未輸入
- 只有輸入Password，UserName未輸入

![https://ithelp.ithome.com.tw/upload/images/20220917/201444692L6eV83Ck6.png](https://ithelp.ithome.com.tw/upload/images/20220917/201444692L6eV83Ck6.png)

`TextInputLayout`的ID.error方法設定自定義的提示訊息，清除error提示訊息時直接設定null
```kotlin
// Set error text
passwordInputText.error = getString(R.string.error)

// Clear error text
passwordInputText.error = null
```

`doOnTextChange` 動態偵測用戶輸入關閉error 警示提示，這個方法點進去看源碼實作其實是封裝TextWatcher的實作方法。
```kotlin
// Get input text
val passwordEditText = binding?.passwordEditText

passwordEditText.editText?.doOnTextChanged { text, _, _, _ ->
    // Respond to input text change
}
```

## Add a trailing icon 設定顯示密碼、屏蔽密碼的邏輯
![https://ithelp.ithome.com.tw/upload/images/20220917/20144469XBRuRNsW7C.png](https://ithelp.ithome.com.tw/upload/images/20220917/20144469XBRuRNsW7C.png)

官方預設的顯示密碼、屏蔽密碼的icon切換很奇怪所以，稍微自己改一下，讓眼睛打開時密碼明文，閉起來時密碼密文顯示
![https://ithelp.ithome.com.tw/upload/images/20220918/20144469jvbIRe2rUY.png](https://ithelp.ithome.com.tw/upload/images/20220918/20144469jvbIRe2rUY.png)

### 1. 先新增自定義的閉眼icon**  `app:endIconDrawable="自定義"`
```Xml
    <com.google.android.material.textfield.TextInputLayout
        ...
                app:endIconMode="password_toggle"
                app:endIconDrawable="@mipmap/icon_closed_eye"
        ...>

            <com.google.android.material.textfield.TextInputEditText
               ...
                android:inputType="textPassword" />

        </com.google.android.material.textfield.TextInputLayout> 
```

### 2.自定義icon點擊判斷密碼明文和密文的切換，以下說明
- 是否預設顯示密碼明文：預設是密文輸入，如需求是密碼欄位指定要明文輸入時可指定`InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD`，和指定顯示密碼的icon 的設定`passwordInputText?.endIconDrawable`
- 點擊icon判斷密碼明文和密文的切換： 透過`setEndIconOnClickListener`監聽點擊icon檢查`PasswordTransformationMethod`方法是否密文轉換，後續判斷`getTransformation`方法回傳明文為null張開眼睛icon，密文時閉眼icon，passwordInputText設定`endIconDrawable`讓icon進行切換。

```kotlin
  private fun passwordEyeVisibility() {

//        // 預設顯示密碼明文
//        passwordEditText?.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
//        passwordInputText?.endIconDrawable = context?.let { AppCompatResources.getDrawable(it, R.drawable.ic_open_eye) }

        passwordInputText?.setEndIconOnClickListener {

            // 檢查是否有 PasswordTransformationMethod 密碼轉換方法
            val hasPasswordTransformation = passwordEditText?.transformationMethod is PasswordTransformationMethod
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
		// getTransformation方法回傳明文為null張開眼睛icon，密文時閉眼icon
        val passwordSource = editText?.transformationMethod?.getTransformation(editText.text, editText)
				
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

```
