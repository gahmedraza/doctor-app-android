package com.raza.medical.doctor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.raza.medical.doctor.patientdetails.PatientDetailsScreen
import com.raza.medical.doctor.patientlist.PatientListScreen

@Composable
fun DoctorNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = "patients"
    ) {

        composable("patients") {
            PatientListScreen(
                onPatientClick = { patientId ->
                    navController.navigate("patientDetails/$patientId")
                }
            )
        }

        composable(
            route = "patientDetails/{patientId}",
            arguments = listOf(
                navArgument("patientId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val patientId = backStackEntry
                .arguments
                ?.getString("patientId")!!

            PatientDetailsScreen(
                patientId = patientId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}