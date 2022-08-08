package com.bawp.jettip_test

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bawp.bmi_copy.ui.widgets.RoundIconButton
import com.bawp.jettip_test.util.calculateTotalPerPerson

@Composable
fun SplitButtons(
    range: IntRange,
    splitByState: MutableState<Int>,
    totalPerPersonState: MutableState<Double>,
    totalBill: MutableState<String>,
    tipPercentage: Int,
) {
    Row(modifier = Modifier.padding(3.dp), horizontalArrangement = Arrangement.Start) {

        Text(text = "Split",
            modifier = Modifier.align(alignment = Alignment.CenterVertically))

        Spacer(modifier = Modifier.width(120.dp))

        Row(modifier = Modifier.padding(horizontal = 3.dp),
            horizontalArrangement = Arrangement.End) {

            RoundIconButton(imageVector = Icons.Default.Remove, onClick = {
                splitByState.value =
                    if (splitByState.value > 1) splitByState.value - 1 else 1
                totalPerPersonState.value =
                    calculateTotalPerPerson(totalBill = totalBill.value.toDouble(),
                        splitBy = splitByState.value,
                        tipPercent = tipPercentage)
            })

            Text(text = "${splitByState.value}",
                Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(start = 9.dp, end = 9.dp))

            RoundIconButton(imageVector = Icons.Default.Add, onClick = {
                if (splitByState.value < range.last) {
                    splitByState.value = splitByState.value + 1

                    totalPerPersonState.value =
                        calculateTotalPerPerson(totalBill = totalBill.value.toDouble(),
                            splitBy = splitByState.value,
                            tipPercent = tipPercentage)
                }
            })

        }
    }
}