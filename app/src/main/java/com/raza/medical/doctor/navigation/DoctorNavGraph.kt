package com.raza.medical.doctor.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.raza.medical.doctor.patientdetails.PatientDetailsScreen
import com.raza.medical.doctor.patientlist.PatientListScreen
import com.raza.medical.doctor.prescriptiondetail.PrescriptionDetailScreen
import com.raza.medical.doctor.prescriptionlist.PrescriptionListScreen

const val home: String = "doctor"

fun NavGraphBuilder.doctorNavGraph(navController: NavHostController) {
    navigation(
        route = home,
        startDestination = Destinations.Patients.value
    ) {

        composable(Destinations.Patients.value) {
            PatientListScreen(
                onPatientClick = { patientId ->
                    navController.navigate("${Destinations.PatientDetails.value}/$patientId")
                }
            )
        }

        composable(
            route = "${Destinations.PatientDetails.value}/{${Params.PatientId.value}}",
            arguments = listOf(
                navArgument("${Params.PatientId.value}") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val patientId = backStackEntry
                .arguments
                ?.getString("${Params.PatientId.value}")!!

            PatientDetailsScreen(
                patientId = patientId,
                onBack = {
                    navController.popBackStack()
                },
                onViewPrescription = { id ->
                    navController.navigate("${Destinations.Prescriptions.value}/$id")
                }
            )
        }

        composable(
            route = "${Destinations.Prescriptions.value}/{${Params.Id.value}}",
            arguments = listOf(
                navArgument(
                    name = "${Params.Id.value}"
                ) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val patientId = backStackEntry
                .arguments
                ?.getString("${Params.Id.value}")!!

            PrescriptionListScreen(
                patientId,
                onBack = {
                    navController.popBackStack()
                },
                onPrescriptionClick = { prescription ->
                    navController.navigate(
                        "${Destinations.PrescriptionDetails.value}/${prescription.id}"
                    )
                }
            )
        }

        composable(
            route = "${Destinations.PrescriptionDetails.value}/{${Params.PrescriptionId.value}}",
            arguments = listOf(
                navArgument(
                    name = "${Params.PrescriptionId.value}"
                ) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val prescriptionId =
                backStackEntry
                    .arguments
                    ?.getString("${Params.PrescriptionId.value}")

            PrescriptionDetailScreen(
                prescriptionId!!
            ) {
                navController.popBackStack()
            }
        }
    }
}

enum class Destinations(val value: String) {
    Patients("patients"),
    PatientDetails("patientDetails"),
    Prescriptions("prescriptions"),
    PrescriptionDetails("prescriptionDetails"),
}

enum class Params(val value: String) {
    PatientId("patientId"),
    Id("id"),
    PrescriptionId("prescriptionId"),
}