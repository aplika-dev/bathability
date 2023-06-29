package com.aplika.core.android.handler

import com.google.firebase.crashlytics.FirebaseCrashlytics

class ThrowableHandler(
    private val firebaseCrashlytics: FirebaseCrashlytics = FirebaseCrashlytics.getInstance()
) {

    fun handle(throwable: Throwable) {
        throwable.printStackTrace()
        firebaseCrashlytics.recordException(throwable)
    }

}