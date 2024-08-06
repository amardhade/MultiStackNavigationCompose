package com.multistacknavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.multistacknavigation.presentation.ConnectScreen
import com.multistacknavigation.presentation.ScanScreen
import com.multistacknavigation.presentation.SettingScreen
import com.multistacknavigation.presentation.SplashScreen
import com.multistacknavigation.presentation.chase.ChaseScreen
import com.multistacknavigation.presentation.home.HomeScreen
import com.multistacknavigation.utils.Routes

@Composable
fun NavigationManager(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Routes.SPLASH,
    currentRouteState: MutableState<String>,
    onBack: () -> Unit = {},
//    builder: NavGraphBuilder.() -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Routes.SPLASH) {
            SplashScreen(navigateTo = {
                navController.navigate(Routes.HOME_SCREEN) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            })
        }

        composable(route = Routes.HOME_SCREEN) {
            HomeScreen(
                onBackPressed = { onBack() },
                currentRoute = currentRouteState
//                    navigateTo = { route -> navigateToHomeTab(navController, route) }
            )
        }

        composable(route = Routes.CONNECTS) {
            ConnectScreen(
                onBackPressed = { navigateToHome(navController) }
            )
        }

        composable(route = Routes.SCAN) {
            ScanScreen(
                onBackPressed = { navigateToHome(navController) }
            )
        }

        composable(route = Routes.CHASE) {
            ChaseScreen(
                onBackPressed = { navigateToHome(navController) }
            )
        }

        composable(route = Routes.SETTINGS) {
            SettingScreen(
                onBackPressed = { navigateToHome(navController) }
            )
        }


        navigation(
            startDestination = Routes.HOME_SCREEN,
            route = Routes.TABS
        ) {


            // Nested Graph Home Screen
//            navigation(
//                startDestination = Routes.TAB1,
//                route = Routes.HOME
//            ) {
//                composable(route = Routes.TAB1) {
//                    Tab1Screen(onBackPress = { navController.popBackStack() })
//                }
//
//                composable(route = Routes.TAB2) {
//                    Tab2Screen(onBackPress = { navController.popBackStack() })
//                }
//
//                composable(route = Routes.TAB3) {
//                    Tab3Screen(onBackPress = { navController.popBackStack() })
//                }
//            }
        }


    }

}

fun navigateToHomeTabs(route: String, navController: NavHostController) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

fun navigateToHome(navController: NavHostController) {
    navController.navigate(Routes.HOME) {
        popUpTo(navController.graph.id) {
            inclusive = false
        }
    }
}

fun navigateToHomeTab(navController: NavHostController, route: String) {
    navController.navigate(route) {
        popUpTo(navController.graph.id) {
            inclusive = false
        }
        launchSingleTop = true
        restoreState = true
    }
}

// Shared view model in nested graph
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}