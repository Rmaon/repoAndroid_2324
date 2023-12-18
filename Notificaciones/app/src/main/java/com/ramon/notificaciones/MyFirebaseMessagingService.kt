package com.ramon.notificaciones

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "MyFirebaseMsgService"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Implementa el código para manejar los mensajes recibidos
        Log.d(TAG, "From: ${remoteMessage.from}")

        // Verifica si el mensaje contiene datos
        remoteMessage.data.isNotEmpty().let {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
            // Aquí puedes manejar los datos del mensaje
        }

        // Verifica si el mensaje contiene una notificación
        remoteMessage.notification?.let {
            Log.d(TAG, "Notification Message Body: ${it.body}")
            // Aquí puedes manejar la notificación
        }
    }
}
