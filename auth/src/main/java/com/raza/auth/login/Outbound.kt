package com.raza.auth.login

const val API_KEY = "reqres_1377da5d7b4e4620b44f573493bca1a9"

//const val BASE_URL = "https://reqres.in/api"

//const val BASE_URL = "http://10.0.2.2:8080"
const val BASE_URL = "http://192.168.1.3:8080"
const val AUTH = "auth"
const val REGISTER = "register"
const val LOGIN = "login"

const val GOOGLE = "google"

const val FORGOT_PASSWORD = "forgot-password"

const val RESET_PASSWORD = "reset-password"

const val REGISTER_URL = "$BASE_URL/$AUTH/$REGISTER"
const val LOGIN_URL = "$BASE_URL/$AUTH/$LOGIN"

const val FORGOT_PASSWORD_URL = "$BASE_URL/$AUTH/$FORGOT_PASSWORD"

const val RESET_PASSWORD_URL = "$BASE_URL/$AUTH/$RESET_PASSWORD"

const val GOOGLE_AUTH_URL = "$BASE_URL/$AUTH/$GOOGLE"