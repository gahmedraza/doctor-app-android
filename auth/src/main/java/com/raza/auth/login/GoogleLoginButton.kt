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
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GoogleLoginButton(
    viewModel: AuthViewModel= viewModel(),
    onLogin: () -> Unit
) {
    val context = LocalContext.current
    val googleManager = remember {
        GoogleAuthManager(context)
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        viewModel.loginWithGoogle(
            result.data,
            onLogin = {
                onLogin()
            })
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