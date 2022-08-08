package com.bawp.jettip_test

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.bawp.bmi_copy.ui.widgets.RoundIconButton
import com.bawp.jettip_test.components.InputField
import com.bawp.jettip_test.screen.home.TipSlider
import com.bawp.jettip_test.util.calculateTotalPerPerson
import com.bawp.jettip_test.util.calculateTotalTip

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FormControl(
    range: IntRange = 1..100,
    splitByState: MutableState<Int>,
    tipAmountState: MutableState<Double>,
    totalPerPersonState: MutableState<Double>,
    onValChange: (String) -> Unit = {},
) {
    val sliderPosition = remember {
        mutableStateOf(0f)
    }

    val tipPercentage = (sliderPosition.value * 100).toInt()

    val totalBill = rememberSaveable { mutableStateOf("") } //or just remember {}

    val valid = remember(totalBill.value) {
        totalBill.value.trim().isNotEmpty()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(),
        shape = CircleShape.copy(all = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {

        Column(modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start) {

            // accept amount from edit text
            AmountEditText(
                totalBill,
                valid,
                { totalBillString ->
                    run {
                        totalPerPersonState.value =
                            calculateTotalPerPerson(
                                totalBill = totalBillString.toDouble(),
                                splitBy = splitByState.value,
                                tipPercent = tipPercentage
                            )
                    }
                },
                keyboardController,
            )

            if (valid) {

                SplitButtons(
                    range, splitByState, totalPerPersonState, totalBill, tipPercentage
                )

                TipInfo(tipAmountState)

                TipSlider(
                    totalBill = totalBill,
                    tipAmountState = tipAmountState,
                    totalPerPersonState = totalPerPersonState,
                    splitByState = splitByState,
                    tipPercentage = tipPercentage,
                    sliderPosition = sliderPosition,
                )

            }


        }
    }//end isValid
}