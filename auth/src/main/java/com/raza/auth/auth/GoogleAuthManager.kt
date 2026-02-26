package com.raza.auth.auth

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.raza.auth.R

class GoogleAuthManager(
    context: Context
) {
    private val gso =
        GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

    val client: GoogleSignInClient =
        GoogleSignIn.getClient(context, gso)
}