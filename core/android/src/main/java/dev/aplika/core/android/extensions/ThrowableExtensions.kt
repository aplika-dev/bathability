package dev.aplika.core.android.extensions

import com.google.firebase.crashlytics.FirebaseCrashlytics

fun Throwable.monitor() {
    printStackTrace()
    FirebaseCrashlytics.getInstance().recordException(this)
}