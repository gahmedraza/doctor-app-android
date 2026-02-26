package com.raza.auth.auth

object GithubCallbackRoute {
    const val ROUTE = "github_callback"
    const val PARAM_CODE = "code"

    const val SCHEME = "yourapp"
    const val HOST = "oauth"

    val routePattern = "$ROUTE?$PARAM_CODE={$PARAM_CODE}"

    val deepLinkPattern = "$SCHEME://$HOST?$PARAM_CODE={$PARAM_CODE}"

    fun createRoute(code: String): String =
        "$ROUTE?$PARAM_CODE=$code"
}