package com.raza.medical.doctor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.raza.medical.doctor.patientdetails.PatientDetailsScreen
import com.raza.medical.doctor.patientlist.PatientListScreen
import com.raza.medical.doctor.prescriptiondetail.PrescriptionDetailScreen
import com.raza.medical.doctor.prescriptionlist.PrescriptionListScreen

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
                },
                onViewPrescription = { id ->
                    navController.navigate("prescriptions/$id")
                }
            )
        }

        composable(
            route = "prescriptions/{id}",
            arguments = listOf(
                navArgument(
                    name = "id"
                ) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val patientId = backStackEntry
                .arguments
                ?.getString("id")!!

            PrescriptionListScreen(
                patientId,
                onBack = {
                    navController.popBackStack()
                },
                onPrescriptionClick = { prescription ->
                    navController.navigate(
                        "prescriptionDetails/${prescription.id}"
                    )
                }
            )
        }

        composable(
            route = "prescriptionDetails/{prescriptionId}",
            arguments = listOf(
                navArgument(
                    name = "prescriptionId"
                ) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val prescriptionId =
                backStackEntry
                    .arguments
                    ?.getString("prescriptionId")

            PrescriptionDetailScreen(
                prescriptionId!!
            ) {
                navController.popBackStack()
            }
        }
    }
}