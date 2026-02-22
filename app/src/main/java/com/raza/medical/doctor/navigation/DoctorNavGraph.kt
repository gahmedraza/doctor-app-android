package com.raza.medical.doctor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.raza.medical.doctor.login.ForgotPasswordScreen
import com.raza.medical.doctor.login.LoginPage
import com.raza.medical.doctor.login.OtpPage
import com.raza.medical.doctor.login.RegisterPage
import com.raza.medical.doctor.login.ResetPasswordScreen
import com.raza.medical.doctor.patientdetails.PatientDetailsScreen
import com.raza.medical.doctor.patientlist.PatientListScreen
import com.raza.medical.doctor.prescriptiondetail.PrescriptionDetailScreen
import com.raza.medical.doctor.prescriptionlist.PrescriptionListScreen

@Composable
fun DoctorNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = Destinations.ForgotPassword.value
    ) {

        composable(Destinations.ResetPassword.value) {
            ResetPasswordScreen()
        }

        composable(Destinations.ForgotPassword.value) {
            ForgotPasswordScreen()
        }

        composable(Destinations.VerifyOtp.value) {
            OtpPage(
                onOtpComplete = { otp ->
                    navController.navigate(Destinations.Login.value)
                }
            )
        }

        composable(Destinations.Register.value) {
            RegisterPage(
                onLogin = {
                    navController.navigate(Destinations.Login.value)
                },
                onRegisterSuccess = {
                    navController.navigate(Destinations.VerifyOtp.value)
                }
            )
        }

        composable(Destinations.Login.value) {
            LoginPage(
                onRegister = {
                    navController.navigate(Destinations.Register.value)
                }
            )
        }

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
    ForgotPassword("forgotPassword"),
    ResetPassword("resetPassword"),
    VerifyOtp("verifyOtp"),
    Register("register"),
    Login("login"),
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