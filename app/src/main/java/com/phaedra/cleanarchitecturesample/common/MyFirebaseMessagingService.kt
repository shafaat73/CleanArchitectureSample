package com.phaedra.cleanarchitecturesample.common

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        //updateFcmToken(p0.toString())
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
        Log.d("notification_data", "del")
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d("notification_data", "${p0}")
    }
    /*override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("notification_data", "$remoteMessage")
        remoteMessage.data.isNotEmpty().let {

            if (!remoteMessage.data.isNullOrEmpty()) {
                sendOrderNotification(remoteMessage)
            }
        }

        // Check if message contains a notification payload.
        remoteMessage?.notification?.let {
            sendOrderNotification(remoteMessage)
        }
    }*/


}