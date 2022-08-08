package com.bawp.jettip_test.screen.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.bawp.jettip_test.ui.theme.JetTipTestTheme

class DetailActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetTipTestTheme {
                HelloWorldScreen()
            }
        }
    }
}

@Preview
@Composable
fun HelloWorldScreen() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Cyan),
        color = Color.Cyan,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hello World! Click Me!!!",
            )

            Spacer(
                modifier = Modifier
                    .height(100.dp)
            )

            Text(
                text = "Hi! Nice to meet you",
            )
        }
    }
}