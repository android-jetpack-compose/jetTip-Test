package com.bawp.jettip_test

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.SoftwareKeyboardController
import com.bawp.jettip_test.components.InputField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AmountEditText(
    totalBill: MutableState<String>,
    valid: Boolean,
    onValChange: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?
) {
    InputField(
        valueState = totalBill, labelId = "Enter Bill",
        enabled = true,
        onAction = KeyboardActions {
            //The submit button is disabled unless the inputs are valid. wrap this in if statement to accomplish the same.
            if (!valid) return@KeyboardActions

            onValChange(totalBill.value.trim())

            //totalBill.value = ""

            keyboardController?.hide() //(to use this we need to use @ExperimentalComposeUiApi
        },
    )
}