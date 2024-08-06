package com.multistacknavigation.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.multistacknavigation.components.AppBackHandler

@Composable
fun SettingScreen(
    onBackPressed: () -> Unit
) {

    AppBackHandler {
        onBackPressed()
    }
    Text(text = "Setting Screen", fontSize = 16.sp)

}