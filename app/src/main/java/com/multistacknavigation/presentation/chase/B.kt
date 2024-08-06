package com.multistacknavigation.presentation.chase

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.multistacknavigation.components.AppBackHandler

@Composable
fun B(
    onBackPress: () -> Unit
) {
    AppBackHandler {
        onBackPress()
    }

    Text(text = "B Screen")
}