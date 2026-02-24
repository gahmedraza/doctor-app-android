package com.raza.auth.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

@Composable
fun FacebookLoginButton(
    onTokenReceived: (String) -> Unit
) {

    val context = LocalContext.current
    val callbackManager = remember { CallbackManager.Factory.create() }

    FacebookInitializer.ensureInitialized(context)

    val launcher = rememberLauncherForActivityResult(
        contract = LoginManager.getInstance()
            .createLogInActivityResultContract()
    ) { result ->
        callbackManager.onActivityResult(
            requestCode = 0,
            resultCode = result.resultCode,
            data = result.data)
    }

    LaunchedEffect(Unit) {
        LoginManager.getInstance().registerCallback(
            callbackManager,
            object: FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    onTokenReceived(result.accessToken.token)
                }

                override fun onError(error: FacebookException) {
                    error.printStackTrace()
                }

                override fun onCancel() {}
            }
        )
    }

    Button(
        onClick = {
            launcher.launch(listOf(
                "email", "public_profile"
            ))
        }
    ) {

        Text("Continue with Facebook")
    }
}