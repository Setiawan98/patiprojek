package projekpati.com.projekpati;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class myService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        setNotification(this,"aaa","bbbbb");
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet Implemented");
    }

    private void setNotification(Context context, String namaSparepart, String Stok) {
        NotificationCompat.Builder notification_builder;
        String chanel_id = "3000";
        CharSequence name = "Channel Name";
        String description = "Chanel Description";

        Intent open_activity_intent = new Intent(context, MainActivity.class);
        PendingIntent pending_intent = PendingIntent
                .getActivity(context, 0, open_activity_intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager notification_manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(chanel_id, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            notification_manager.createNotificationChannel(mChannel);
            notification_builder = new NotificationCompat.Builder(context, chanel_id);
        } else {
            notification_builder = new NotificationCompat.Builder(context);
        }
        notification_builder.setSmallIcon(R.drawable.iconawal)
                .setContentTitle(namaSparepart)
                .setContentText("Stok Sisa: " + Stok)
                .setAutoCancel(true)
                .setContentIntent(pending_intent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, notification_builder.build());
    }
}
