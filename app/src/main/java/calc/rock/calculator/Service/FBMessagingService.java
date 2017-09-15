package calc.rock.calculator.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import calc.rock.calculator.R;


public class FBMessagingService extends FirebaseMessagingService {
    public FBMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getNotification() != null){
            String title = remoteMessage.getNotification().getTitle();
            String content = remoteMessage.getNotification().getBody();
            if(title == null){
                title = "Calculator Notification";
            }
            Context ctx = getApplicationContext();
        NotificationCompat.Builder b = new NotificationCompat.Builder(ctx);
        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(title)
                .setContentText(content)
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setContentInfo("Calculator");
        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b.build());
        }
//
    }
}