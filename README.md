# MDCSubject
Material Design 3 Component

# MDC Components的Button和Text Field，實作一個「登錄頁面」
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
