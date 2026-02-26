package com.raza.auth.login

import com.raza.auth.navigation.AuthDestinations
import com.raza.auth.navigation.Params

object ResetPasswordRoute {
    val DESTINATION = AuthDestinations.ResetPassword.value

    const val PARAM_TOKEN = "token"

    const val SCHEME = "myapp"

    val routePattern = "$DESTINATION?$PARAM_TOKEN={$PARAM_TOKEN}"

    val deepLinkPattern = "$SCHEME://$DESTINATION?$PARAM_TOKEN={$PARAM_TOKEN}"

    fun createRoute(token: String?): String {
        return if (token.isNullOrEmpty()) {
            DESTINATION
        } else {
            "$DESTINATION?$PARAM_TOKEN=$token"
        }
    }
}