package com.raza.auth.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.raza.auth.bean.GoogleSignInRequest
import com.raza.auth.networking.makeGoogleSignInCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun GoogleLoginButton(

) {
    val context = LocalContext.current
    val googleManager = remember {
        GoogleAuthManager(context)
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(
                ApiException::class.java
            )
            account.idToken?.let {
                val request = GoogleSignInRequest()
                request.idToken = it

                CoroutineScope(Dispatchers.IO).launch {
                    makeGoogleSignInCall(request)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            launcher.launch(googleManager.client.signInIntent)
        }
    ) {
        Text("Continue with Google")
    }
}