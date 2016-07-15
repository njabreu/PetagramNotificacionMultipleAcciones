package com.example.nelson.petagram;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
//import android.support.v4.app.NotificationCompat;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;

import android.util.Log;
import android.view.Gravity;

import com.example.nelson.petagram.fragment.PerfilFragment;
import com.example.nelson.petagram.notificaciones.ConstanteRestAPINotificaciones;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Nelson Abreu on 7/9/2016.
 */
public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "FIREBASE";

    private static final int NOTIFICATION_ID = 011;
    private static final int REQUEST_CODE_ID_PERFIL = 001;
    private static final int REQUEST_CODE_ID_FOLLOW = 002;
    private static final int REQUEST_CODE_ID_USUARIO = 003;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intentVerUsuario = new Intent(this, MainActivity.class);
            intentVerUsuario.setAction(ConstanteRestAPINotificaciones.ACCION_VER_USUARIO);

        Intent intentFollow = new Intent(this, DarFollowActivity.class);
            intentFollow.setAction(ConstanteRestAPINotificaciones.ACCION_DAR_FOLLOW);

        Intent intentVerPerfil = new Intent(this, VerUsuarioActivity.class);
            intentVerPerfil.setAction(ConstanteRestAPINotificaciones.ACCION_VER_PERFIL);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,REQUEST_CODE_ID_PERFIL,intentVerPerfil, PendingIntent.FLAG_ONE_SHOT );

        NotificationCompat.Action accionVerPefil =
                new NotificationCompat.Action.Builder(R.drawable.ver_perfil,
                        "Ver Perfil",
                        pendingIntent.getBroadcast(this, REQUEST_CODE_ID_PERFIL, intentVerPerfil, PendingIntent.FLAG_UPDATE_CURRENT))
                        .build();

        NotificationCompat.Action accionDarFollow =
                new NotificationCompat.Action.Builder(R.drawable.dar_follow,
                        "Dar Follow",
                        pendingIntent.getBroadcast(this, REQUEST_CODE_ID_FOLLOW, intentFollow, PendingIntent.FLAG_UPDATE_CURRENT))
                        .build();

        NotificationCompat.Action accionVerUsuario =
                new NotificationCompat.Action.Builder(R.drawable.ver_usuario,
                        "Ver Usuario",
                        pendingIntent.getBroadcast(this, REQUEST_CODE_ID_USUARIO, intentVerUsuario, PendingIntent.FLAG_UPDATE_CURRENT))
                        .build();



        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.fondo_notificacion))
                        .setHintHideIcon(true)
                        .setGravity(Gravity.CENTER_VERTICAL);


        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_alert)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender.addAction(accionVerPefil))
                .extend(wearableExtender.addAction(accionVerUsuario))
                .extend(wearableExtender.addAction(accionDarFollow))
                .setAutoCancel(true);

        NotificationManagerCompat nm = NotificationManagerCompat.from(this);
        nm.notify(NOTIFICATION_ID, notificacion.build());

    }
}
