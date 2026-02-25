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

                    FacebookSdk.setApplicationId("1592879428425992")
                    FacebookSdk.setClientToken("5e8d6d68ef9ca70dad66e1224a832b89")
                    FacebookSdk.setAutoInitEnabled(true)
                    FacebookSdk.sdkInitialize(context)
                    FacebookSdk.fullyInitialize()
                    AppEventsLogger.activateApp(context as Application)
                    initialized = true

                    /*if(FacebookSdk.isInitialized()) {

                    }*/
                }
            }
        }
    }
}