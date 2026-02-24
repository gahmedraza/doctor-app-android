package com.raza.auth.login

import android.app.Application
import android.content.Context
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

object FacebookInitializer {

    @Volatile
    private var initialized = false

    fun ensureInitialized(context: Context) {

        if(!initialized) {
            synchronized(this) {
                if(!initialized) {

                    FacebookSdk.sdkInitialize(context)
                    AppEventsLogger.activateApp(context as Application)

                    initialized = true
                }
            }
        }
    }
}