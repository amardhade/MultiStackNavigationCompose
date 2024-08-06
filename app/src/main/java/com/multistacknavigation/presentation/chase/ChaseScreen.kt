package com.multistacknavigation.presentation.chase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.multistacknavigation.components.AppTopBar

@Composable
fun ChaseScreen(
    onBackPressed: () -> Unit = {}
) {

    val chaneNavController = rememberNavController()

    fun onBack() {
        chaneNavController.popBackStack(
            destinationId = chaneNavController.graph.startDestinationId,
            inclusive = true,
            saveState = false
        )
        onBackPressed()
    }

    Scaffold(
        topBar = {
            AppTopBar(
                titles = listOf("A", "B", "C"),
                navigateTo = { route ->
                    chaneNavController.navigate(route = route) {
                        launchSingleTop = true
                    }
                })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = Color.LightGray)
                .fillMaxSize()
        ) {
            NavHost(navController = chaneNavController, startDestination = "a") {
                composable(route = "a") {
                    A(onBackPress = {
                        onBack()
                    })
                }
                composable(route = "b") { B(onBackPress = { onBack() }) }
                composable(route = "c") { C(onBackPress = { onBack() }) }
            }
        }

    }



//    AppBackHandler {
//        onBackPressed()
//    }
//    Text(text = "Chase Screen", fontSize = 16.sp)

}