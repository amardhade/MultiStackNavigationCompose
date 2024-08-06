package com.multistacknavigation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.multistacknavigation.R
import com.multistacknavigation.utils.Routes

@Composable
fun BottomBar(
    modifier: Modifier,
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
    currentRouteState: MutableState<String>
) {

    var tabIndex by remember { mutableIntStateOf(0) }

    val currentDestination = navController.currentDestination

    val tabs = listOf(
        NavigationItem.Home,
        NavigationItem.Connects,
        NavigationItem.Scan,
        NavigationItem.Chase,
        NavigationItem.Settings
    )

    tabs.forEachIndexed { index, navigationItem ->
        if (currentRouteState.value == navigationItem.route) tabIndex = index
    }

//    fun isSelected(route: String): Boolean {
//        return currentDestination?.hierarchy?.any { dest ->
//            dest.route.equals(route, false)
//        } ?: false
//    }

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        TabRow(modifier = modifier, selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, navigationItem ->
                Tab(selected = tabIndex == index, onClick = {
                    tabIndex = index
                    navController.navigate(navigationItem.route) {
//                        navController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//                        }
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
                    Column(modifier = Modifier) {
                        Icon(
                            painter = painterResource(id = navigationItem.icon),
                            contentDescription = navigationItem.title
                        )
                        Text(text = navigationItem.title, fontSize = 14.sp)
                    }
                }
            }
        }

    }

}

sealed class NavigationItem(val route: String, val icon: Int, val title: String) {

    data object Home :
        NavigationItem(route = Routes.HOME_SCREEN, icon = R.drawable.home_icon, title = "Home")

    data object Connects :
        NavigationItem(route = Routes.CONNECTS, icon = R.drawable.connects_icon, title = "Connects")

    data object Scan :
        NavigationItem(route = Routes.SCAN, icon = R.drawable.scanner_icon, title = "Scan")

    data object Chase :
        NavigationItem(route = Routes.CHASE, icon = R.drawable.chase_icon, title = "Chase")

    data object Settings :
        NavigationItem(route = Routes.SETTINGS, icon = R.drawable.settings_icon, title = "Settings")
}

@Preview
@Composable
fun BottomBarPreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    bottomBarState: MutableState<Boolean> = remember { mutableStateOf(true) }
) {
}