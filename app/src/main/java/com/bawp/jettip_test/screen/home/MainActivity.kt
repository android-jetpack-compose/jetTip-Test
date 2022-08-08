package com.bawp.jettip_test.screen.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bawp.bmi_copy.ui.widgets.RoundIconButton
import com.bawp.jettip_test.components.InputField
import com.bawp.jettip_test.data.CounterViewModel
import com.bawp.jettip_test.ui.theme.JetTipTestTheme
import com.bawp.jettip_test.util.calculateTotalPerPerson
import com.bawp.jettip_test.util.calculateTotalTip
import androidx.compose.foundation.layout.Column
import com.bawp.jettip_test.MainContent

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                TipCalculator()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    /*
      content: @Composable ... it's called a container function
      which makes MyApp more flexible to deal with
     */
    JetTipTestTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
            //ScreenDemo(model = CounterViewModel())
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun TipCalculator() {
    Surface(modifier = Modifier.padding(12.dp)) {
        Column() {
            MainContent()
        }
    }
}


@Composable
fun ScreenDemo(model: CounterViewModel) {

    //source: https://www.rockandnull.com/jetpack-compose-viewmodel/
    Column(modifier = Modifier.padding(14.dp)) {
        Demo("This is ${model.getCounter()}", counterViewModel = model) {
            if (it) {
                model.increaseCounter()
            } else {
                model.decreaseCounter()
            }
        }

        if (model.getCounter() > 12) {

            Text(text = "I love this so much!!")


        }
    }



}

@Composable
fun Demo(
    text: String,
    counterViewModel: CounterViewModel,
    onclick: (Boolean) -> Unit = {},
        ) {
    val isVal = remember {
        mutableStateOf(false)
    }
    Column(verticalArrangement = Arrangement.Center) {
        var isRange by remember {
            mutableStateOf(false)
        }
        isRange = counterViewModel.getCounter() == 12
        Text(text = text, color = if (isRange) Color.Red else Color.LightGray)


        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {
                    isVal.value = true
                    onclick(isVal.value)
                },
                  ) {

                BasicText(text = "Add 1")
            }

            Button(
                onClick = {
                    isVal.value = false
                    onclick(isVal.value)
                },
                  ) {

                BasicText(text = "Minus 1")
            }

        }

    }


}


@Composable
private fun TipSlider(
    modifier: Modifier = Modifier,
    sliderState: MutableState<Float>,
    totalTipState: MutableState<Double>,
    totalBillState: MutableState<String>,
                     ) {
    val tipPercentage = (sliderState.value.toInt())

    val percentage = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 32.sp)) { append(tipPercentage.toString()) }
        append(" %")
    }
    //Slider
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = percentage.toString())
        Spacer(modifier = Modifier.height(14.dp))
        Slider(value = sliderState.value,
            onValueChange = {
                sliderState.value = it
                totalTipState.value = calculateTotalTip(totalBill = totalBillState.value.toDouble(),
                    tipPercent = tipPercentage)
                // Log.d("AMT", "TipSlider: $tipPercentage")

//                totalTipState.value = calculateTotalTip(tota
//                    tipPercent = tipPercentage).roundToInt().toString()
            },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            steps = 5,
            valueRange = (0f..100f))

    }

}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        TipCalculator()
    }
}