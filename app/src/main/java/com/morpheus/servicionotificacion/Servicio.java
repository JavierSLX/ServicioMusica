package com.morpheus.servicionotificacion;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Morpheus on 06/07/2018.
 */

public class Servicio extends Service
{
    private NotificationManager notificationManager;
    private static final int ID_NOTIFICACION = 1;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate()
    {
        super.onCreate();

        //Se instancia el Manager
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        //Trae el archivo, hace que se reproduzca automaticamente y que se repita
        mediaPlayer = MediaPlayer.create(this, R.raw.amor);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100, 100);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        //Se define el intent cuando se presiona la notificacion
        Intent myIntent = new Intent(this, ActivityCancion.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, myIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext())
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Musica")
                .setContentText("Reproduciendo")
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{0, 100, 100})
                .setWhen(System.currentTimeMillis());

        //Lanza la notificacion
        notificationManager.notify(ID_NOTIFICACION, builder.build());

        mediaPlayer.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        //Cancela la notificacion
        notificationManager.cancel(ID_NOTIFICACION);

        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();

        mediaPlayer.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
