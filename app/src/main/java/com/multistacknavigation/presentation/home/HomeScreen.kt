package com.multistacknavigation.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.multistacknavigation.components.AppTopBar
import com.multistacknavigation.utils.Routes

@Composable
fun HomeScreen(
    onBackPressed: () -> Unit, currentRoute: MutableState<String>
) {

//    AppBackHandler {
//        onBackPressed()
//    }

    val homeNavController = rememberNavController()

    fun navigateTo(route: String) {
        homeNavController.navigate(route) { launchSingleTop = true }
    }

    Scaffold(topBar = {
        AppTopBar(titles = listOf("Tab_1", "Tab_2", "Tab_3")) { route ->
            navigateTo(route)
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color.LightGray)
                .fillMaxSize()
        ) {
            HomeNavigation(homeNavController, onBackPressed)
        }
    }


}

@Composable
fun HomeNavigation(homeNavController: NavHostController, onBackPressed: () -> Unit) {
    NavHost(navController = homeNavController, startDestination = "tab_1") {
        composable(route = "tab_1") {
            Tab1Screen(onBackPress = { onBackPressed() })
        }

        composable(route = "tab_2") {
            Tab2Screen(onBackPress = { homeNavController.popBackStack() })
        }

        composable(route = "tab_3") {
            Tab3Screen(onBackPress = { homeNavController.popBackStack() })
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(
    onBackPressed: () -> Unit = {}, currentRoute: MutableState<String> = mutableStateOf(Routes.TAB1)
) {
}