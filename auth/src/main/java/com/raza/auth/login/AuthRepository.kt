package com.raza.auth.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*class AuthRepository {
    suspend fun loginWithGoogle(
        idToken: String
    ): GoogleSignInReponse {

        return withContext(Dispatchers.IO) {
            val request = GoogleSignInRequest()
            request.idToken = idToken

            makeLoginCall()
        }
    }
}*/

class GoogleSignInRequest: Request {
    var idToken: String? = null
}

class GoogleSignInResponse {
    var userId: String? = null
    var accessToken: String? = null
    var refreshToken: String? = null
}