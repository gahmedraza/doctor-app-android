package com.raza.auth.login

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GithubCallbackScreen(
    code: String?,
    onGithubSuccess: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {

    LaunchedEffect(code) {
        code?.let {
            viewModel.loginWithGithub(
                code,
                onGithubSuccess
            )
        }
    }

    Text("Signing you in...")
}