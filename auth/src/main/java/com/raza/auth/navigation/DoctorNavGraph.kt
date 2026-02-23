package com.raza.auth.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.raza.auth.login.ForgotPasswordScreen
import com.raza.auth.login.LoginPage
import com.raza.auth.login.OtpPage
import com.raza.auth.login.RegisterPage
import com.raza.auth.login.ResetPasswordScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    homeAddress: String?) {

    navigation(
        route = "auth",
        startDestination = AuthDestinations.Login.value
    ) {

        composable(
             "${AuthDestinations.ResetPassword.value}?token={token}",
            arguments = listOf(
                navArgument(
                    "token"
                ) {
                    type = NavType.StringType
                    nullable = true
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "myapp://${AuthDestinations.ResetPassword.value}?token={token}"
                }
            )
            ) { backStackEntry ->

            val token = backStackEntry.arguments?.getString("token")
            ResetPasswordScreen(token = token ?: "")
        }

        composable(AuthDestinations.ForgotPassword.value) {
            ForgotPasswordScreen(
                onResetPassword = { resetLink ->
                    navController.navigate(
                        //"${Destinations.ResetPassword.value}?token=$token")
                        Uri.parse(resetLink)
                    )
                }
            )
        }

        composable(AuthDestinations.VerifyOtp.value) {
            OtpPage(
                onOtpComplete = { otp ->
                    navController.navigate(AuthDestinations.Login.value)
                }
            )
        }

        composable(AuthDestinations.Register.value) {
            RegisterPage(
                onLogin = {
                    navController.navigate(AuthDestinations.Login.value)
                },
                onRegisterSuccess = {
                    navController.navigate(AuthDestinations.VerifyOtp.value)
                }
            )
        }

        composable(AuthDestinations.Login.value) {
            LoginPage(
                onLoginSuccess = {
                    homeAddress?.let { home ->
                        navController.navigate(home)
                    }

                },
                onRegister = {
                    navController.navigate(AuthDestinations.Register.value)
                }
            )
        }
    }
}

enum class AuthDestinations(val value: String) {
    ForgotPassword("forgotPassword"),
    ResetPassword("resetPassword"),
    VerifyOtp("verifyOtp"),
    Register("register"),
    Login("login")
}