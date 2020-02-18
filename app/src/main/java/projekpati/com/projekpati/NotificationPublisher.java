package projekpati.com.projekpati;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static projekpati.com.projekpati.MainActivity.NOTIFICATION_CHANNEL_ID;

public class NotificationPublisher extends BroadcastReceiver {
    Context context;
    public NotificationPublisher(){}

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d("onReceive", "yey");
        intent = new Intent(context,myService.class);
        context.startService(intent);
    }


}
