package com.raza.medical.doctor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.raza.auth.navigation.AUTH_NAV_GRAPH_HOME
import com.raza.auth.navigation.authNavGraph

@Composable
fun RootNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AUTH_NAV_GRAPH_HOME
    ) {

        authNavGraph(navController, DOCTOR_NAV_GRAPH_HOME)
        doctorNavGraph(navController)
    }
}