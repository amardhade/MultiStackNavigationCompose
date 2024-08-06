package com.multistacknavigation.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun ConnectScreen(
    onBackPressed: () -> Unit
) {

//    AppBackHandler {
//        onBackPressed()
//    }

    Text(text = "Connect Screen", fontSize = 16.sp)
}