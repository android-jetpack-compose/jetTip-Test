package com.bawp.jettip_test.screen.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bawp.jettip_test.util.calculateTotalPerPerson
import com.bawp.jettip_test.util.calculateTotalTip

@Composable
fun TipSlider(
    totalBill: MutableState<String>,
    tipAmountState: MutableState<Double>,
    totalPerPersonState: MutableState<Double>,
    splitByState: MutableState<Int>,
    sliderPosition: MutableState<Float>,
    tipPercentage: Int,
) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "$tipPercentage %")
        Spacer(modifier = Modifier.height(14.dp))
        Slider(value = sliderPosition.value,
            onValueChange = { newVal ->
                sliderPosition.value = newVal
                tipAmountState.value =
                    calculateTotalTip(totalBill = totalBill.value.toDouble(),
                        tipPercent = tipPercentage)

                totalPerPersonState.value =
                    calculateTotalPerPerson(totalBill = totalBill.value.toDouble(),
                        splitBy = splitByState.value,
                        tipPercent = tipPercentage)
                Log.d("Slider",
                    "Total Bill-->: ${"%.2f".format(totalPerPersonState.value)}")

            },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            steps = 5,
            onValueChangeFinished = {
                Log.d("Finished", "BillForm: $tipPercentage")
                //This is were the calculations should happen!
            })

    }
}