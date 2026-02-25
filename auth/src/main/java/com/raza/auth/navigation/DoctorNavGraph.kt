package com.raza.auth.navigation

import androidx.core.net.toUri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.raza.auth.login.ForgotPasswordScreen
import com.raza.auth.login.GithubCallbackScreen
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
            route = "github_callback?code={code",
            arguments = listOf(
                navArgument("code") {
                    type = NavType.StringType
                }),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "yourapp://oauth?code={code}"
                })
        ) { backStackEntry ->
            val code = backStackEntry.arguments?.getString("code")
            GithubCallbackScreen(
                code,
                onGithubSuccess = {
                    homeAddress?.let { home ->
                        navController.navigate(home)
                    }
                })
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
                },
                onForgotPassword = {
                    navController.navigate(AuthDestinations.ForgotPassword.value)
                }
            )
        }

        composable(AuthDestinations.Register.value) {
            RegisterPage(
                onRegisterSuccess = {
                    navController.navigate(AuthDestinations.VerifyOtp.value)
                },
                onLogin = {
                    navController.navigate(AuthDestinations.Login.value)
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

        composable(AuthDestinations.ForgotPassword.value) {
            ForgotPasswordScreen(
                onResetPassword = { resetLink ->
                    navController.navigate(
                        resetLink.toUri()
                    )
                }
            )
        }

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
            ResetPasswordScreen(token = token ?: "",
                onResetSuccess = {
                    navController.navigate(
                        AuthDestinations.Login.value
                    )
                })
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