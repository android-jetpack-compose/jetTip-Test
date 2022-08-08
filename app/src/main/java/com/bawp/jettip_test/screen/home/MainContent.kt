package com.bawp.jettip_test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalComposeUiApi
@Preview
@Composable
fun MainContent() {
    val splitBy = remember {
        mutableStateOf(1)
    }

//    val sliderPosition: MutableState<Float> = remember {
//        mutableStateOf(0f)
//    }

    val totalTipAmt = remember {
        mutableStateOf(0.0)
    }
//    val totalTipAmt: MutableState<Double> = remember {
//        mutableStateOf(0.0)
//    }
    val totalPerPerson = remember {
        mutableStateOf(0.0)
    }
    BillForm(splitByState = splitBy,
        tipAmountState = totalTipAmt,
        totalPerPersonState = totalPerPerson

    ) {

    }
}