package com.multistacknavigation.presentation.chase

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.multistacknavigation.components.AppBackHandler

@Composable
fun C(
    onBackPress: () -> Unit
) {
    AppBackHandler {
        onBackPress()
    }

    Text(text = "C Screen")
}