package com.raza.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    Scaffold { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(top = 200.dp,
                start = 100.dp)) {

            Text("Signing you in...")
        }
    }
}