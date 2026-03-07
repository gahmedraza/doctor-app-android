package com.raza.zikr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raza.zikr.home.HomePage

const val startDestination = "home"

@Composable
fun ZikrNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(
            route = "home"
        ) {

            HomePage(
                zikrs = emptyList(),
                onZikrClick = {

                }
            )
        }
    }
}