package com.aplika.bathability

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

internal class BathabilityMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("BathabilityMessagingService", "New token generated: $token")
        super.onNewToken(token)
    }

}