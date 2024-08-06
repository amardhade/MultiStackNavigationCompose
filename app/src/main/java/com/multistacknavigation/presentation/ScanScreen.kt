package com.multistacknavigation.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.multistacknavigation.components.AppBackHandler

@Composable
fun ScanScreen(
    onBackPressed: () -> Unit
) {

    AppBackHandler {
        onBackPressed()
    }
    Text(text = "Scan Screen", fontSize = 16.sp)

}