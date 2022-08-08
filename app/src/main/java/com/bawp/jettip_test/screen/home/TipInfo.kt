package com.bawp.jettip_test

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TipInfo(
    tipAmountState: MutableState<Double>,
) {
    Row(modifier = Modifier
        .padding(horizontal = 3.dp)
        .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.End) {

        Text(text = "Tip",
            modifier = Modifier.align(alignment = Alignment.CenterVertically))

        Spacer(modifier = Modifier.width(200.dp))

        Text(text = "$${tipAmountState.value}",
            modifier = Modifier.align(alignment = Alignment.CenterVertically))

    }
}