package com.raza.auth.login

import android.content.Context
import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.net.toUri

@Composable
fun GithubLoginButton(
    modifier: Modifier = Modifier,
    context: Context
) {
    Button(
        modifier = modifier,
        onClick = {

        val url = "https://github.com/login/oauth/authorize" +
                "?client_id=Ov23liyxZnBd9TvKkud6" +
                "&scope=user:email"

        val intent = Intent(
            Intent.ACTION_VIEW,
            url.toUri()
        )
        context.startActivity(intent)
    }) {

        Text(
            text =
                "Continue with Github"
        )
    }
}