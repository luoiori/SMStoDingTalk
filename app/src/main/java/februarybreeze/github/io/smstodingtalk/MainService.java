package februarybreeze.github.io.smstodingtalk;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MainService extends Service {
    private static final String TAG = MainService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
    }

    private Preferences preference;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()");
        preference = new Preferences(this);
        Notification.Builder builder = new Notification.Builder(
                this.getApplicationContext()
        );
        Intent activityIntent = new Intent(this, MainActivity.class);
        builder.setContentIntent(
                PendingIntent.getActivity(this,0,activityIntent, 0)
        ).setLargeIcon(BitmapFactory.decodeResource(
                this.getResources(), R.mipmap.ic_launcher)
        ).setContentTitle(
                "转发短信到邮箱"
        ).setSmallIcon(
                R.mipmap.ic_launcher
        ).setContentText(
                "Running "+preference.getEmail()
        ).setWhen(
                System.currentTimeMillis()
        );

        Notification notification = builder.build();
        startForeground(326, notification);

        return super.onStartCommand(intent, flags, startId);
    }

}
