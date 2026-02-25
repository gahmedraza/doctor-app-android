package com.raza.auth.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raza.auth.theme.AuthTheme

@Composable
fun GoogleLoginButton(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = viewModel(),
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
        modifier = modifier,
        onClick = {
            launcher.launch(googleManager.client.signInIntent)
        }
    ) {
        Text("Continue with Google")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGoogleButton() {
    AuthTheme {
        GoogleLoginButton(onLogin = { },)
    }
}