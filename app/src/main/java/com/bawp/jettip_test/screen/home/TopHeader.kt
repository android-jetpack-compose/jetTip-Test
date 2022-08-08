package com.bawp.jettip_test

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TopHeader(totalPerPers: Double = 0.0) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .padding(12.dp)
        .clip(shape = CircleShape.copy(all = CornerSize(12.dp))), color = Color(0xFFe9d7f7)
    ) {
        Column(modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Total Per Person", style = MaterialTheme.typography.subtitle1)
            val total = "%.2f".format(totalPerPers)
            Text(text = "\$$total", style = MaterialTheme.typography.h4)


        }

    }

}