package com.raza.medical.doctor.logger

import android.util.Log

/**
 * Ignore from test reports
 */

const val DEFAULT_TAG = "debug"

object Logger {
    fun d(
        message: String,
        tag: String = DEFAULT_TAG
    ) {

        Log.w(tag, message)
    }
}