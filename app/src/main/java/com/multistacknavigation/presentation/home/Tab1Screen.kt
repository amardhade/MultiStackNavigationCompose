package com.multistacknavigation.presentation.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.multistacknavigation.components.AppBackHandler

@Composable
fun Tab1Screen(
    onBackPress: () -> Unit
) {

    AppBackHandler {
        onBackPress()
    }
    Text(text = "Tab1 Screen")
}