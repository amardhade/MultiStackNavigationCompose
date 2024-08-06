package com.multistacknavigation.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateTo: () -> Unit
) {

    Text(text = "Splash Screen", fontSize = 16.sp)

    LaunchedEffect(key1 = true) {
        delay(1500)
        navigateTo()
    }
}