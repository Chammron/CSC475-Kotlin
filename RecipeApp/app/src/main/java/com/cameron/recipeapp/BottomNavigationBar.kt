package com.cameron.recipeapp

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    //All navigation items for bottom bar (Home, Search, Favorites).
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Favorites
    )

    //Creates the navigation bar.
    NavigationBar {
        //Add a navigation item for each screen.
        items.forEach { screen ->
            NavigationBarItem(
                label = { Text(screen.title) },
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                selected = navController.currentBackStackEntryAsState().value?.destination?.route == screen.route,
                onClick = {
                    //Navigate to the selected screen and manage back stack behavior.
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            //Save the state of previous screens.
                            saveState = true
                        }
                        //Avoid multiple copies of the same screen in the stack.
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}