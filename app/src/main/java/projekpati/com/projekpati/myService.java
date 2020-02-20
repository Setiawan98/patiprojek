package projekpati.com.projekpati;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import projekpati.com.projekpati.API.API;
import projekpati.com.projekpati.API.RetrofitClientInstance;
import projekpati.com.projekpati.Model.Notifikasi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class myService extends Service {

    //Notifikasi notif;
    String judul, pesan;
    Context context = this;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        API api = RetrofitClientInstance.getRetrofitInstance().create(API.class);
        Call<Notifikasi> call = api.tampilNotifikasi();
        call.enqueue(new Callback<Notifikasi>() {
            @Override
            public void onResponse(Call<Notifikasi> call, Response<Notifikasi> response) {
                judul = response.body().getJudul();
                pesan = response.body().getPesan();
                setNotification(context,judul,pesan);

                Log.d("cobaYa", response.body().getJudul());
            }

            @Override
            public void onFailure(Call<Notifikasi> call, Throwable t) {

            }
        });

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
//        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();

        notification_builder.setSmallIcon(R.drawable.iconawal)
                .setContentTitle(namaSparepart)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(Stok))
                .setContentText(Stok)
                .setAutoCancel(true)
                .setContentIntent(pending_intent);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(0, notification_builder.build());
    }
}
