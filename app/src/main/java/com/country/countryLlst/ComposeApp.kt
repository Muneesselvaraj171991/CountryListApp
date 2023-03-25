package com.country.countryLlst

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.country.countryLlst.data.Country
import com.country.countryLlst.navigation.Actions
import com.country.countryLlst.navigation.Destination
import com.country.countryLlst.ui.DashboardScreen
import com.country.countryLlst.ui.LoginScreen


@Composable
fun ComposeApp(list : List<Country>) {
    val navController = rememberNavController()

    val actions = remember(navController) { Actions(navController) }
    MaterialTheme {
        NavHost(navController = navController, startDestination = Destination.Login) {

            composable(Destination.Login) {
                LoginScreen(actions.openDashboard)
            }

            composable(Destination.DashBoard) {
                DashboardScreen(list)
            }
        }
    }
}