package com.multistacknavigation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.multistacknavigation.components.BottomBar
import com.multistacknavigation.ui.theme.MultiStackNavigationTheme
import com.multistacknavigation.utils.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultiStackNavigationTheme {
                ActivityWrapper(onBack = {
                    Toast.makeText(this, "Back, finishing activity..", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}

@Composable
fun ActivityWrapper(
    onBack: () -> Unit = {}
) {
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val currentRouteState = rememberSaveable { (mutableStateOf("")) }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: ""
    currentRouteState.value = currentRoute
    when (currentRoute) {
        Routes.SPLASH -> {
            bottomBarState.value = false
        }

        Routes.TABS, Routes.HOME, Routes.HOME_SCREEN, Routes.CONNECTS, Routes.CHASE, Routes.SCAN, Routes.SETTINGS -> {
            bottomBarState.value = true
        }
    }


    Scaffold(
        bottomBar = {
            BottomBar(
                modifier = Modifier.fillMaxWidth(),
                navController = navController,
                bottomBarState = bottomBarState,
                currentRouteState = currentRouteState
            )
        }
    ) { innerPadding ->
        NavigationManager(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            currentRouteState = currentRouteState,
            onBack = onBack
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultiStackNavigationTheme {

    }
}