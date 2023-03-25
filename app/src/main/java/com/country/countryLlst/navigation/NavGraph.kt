package com.country.countryLlst.navigation;

import androidx.navigation.NavHostController
import com.country.countryLlst.navigation.Destination.DashBoard

object Destination {
    const val Login = "Login"
    const val DashBoard = "DashBoard"
}

class Actions(navController: NavHostController) {

    val openDashboard: () -> Unit = {
        navController.navigate(DashBoard)
    }

    val addTask: () -> Unit = {
        navController.navigate(DashBoard)
    }

    val upPress: () -> Unit = {
        navController.popBackStack()
    }
}
