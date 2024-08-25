package com.moviedbopenplay.ui.composables

import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap

@Composable
fun OrangeProgressIndicator(){


    LinearProgressIndicator(color = Color(0xFFFF8058), trackColor = Color.Black, strokeCap = StrokeCap.Round)
}