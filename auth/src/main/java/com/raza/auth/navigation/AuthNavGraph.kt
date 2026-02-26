package com.raza.auth.navigation

import androidx.core.net.toUri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.raza.auth.auth.ForgotPasswordScreen
import com.raza.auth.auth.GithubCallbackRoute
import com.raza.auth.auth.GithubCallbackScreen
import com.raza.auth.auth.LoginPage
import com.raza.auth.auth.OtpPage
import com.raza.auth.auth.RegisterPage
import com.raza.auth.auth.ResetPasswordRoute
import com.raza.auth.auth.ResetPasswordScreen

const val AUTH_NAV_GRAPH_HOME = "auth"
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    homeAddress: String?) {

    navigation(
        route = AUTH_NAV_GRAPH_HOME,
        startDestination = AuthDestinations.Login.value
    ) {

        composable(
            route = GithubCallbackRoute.routePattern,
            arguments = listOf(
                navArgument(GithubCallbackRoute.PARAM_CODE) {
                    type = NavType.StringType
                }),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = GithubCallbackRoute.deepLinkPattern
                })
        ) { backStackEntry ->
            val code = backStackEntry.arguments?.getString(GithubCallbackRoute.PARAM_CODE)
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
            ResetPasswordRoute.routePattern,
            arguments = listOf(
                navArgument(
                    ResetPasswordRoute.PARAM_TOKEN
                ) {
                    type = NavType.StringType
                    nullable = true
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = ResetPasswordRoute.deepLinkPattern
                }
            )
        ) { backStackEntry ->

            val token = backStackEntry.arguments?.getString(ResetPasswordRoute.PARAM_TOKEN)
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