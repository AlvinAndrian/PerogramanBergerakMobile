package com.example.notebook.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.notebook.R;
import com.example.notebook.databinding.ActivityUpdateNotesBinding;

public class NotificationJobService extends JobService {
    NotificationManager mNotifyManager;
    boolean reminder = true;

    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";

    private static final String TAG = "ExampleJobService";
    private boolean jobCancelled = false;

    public void createNotificationChannel() {

        // Define notification manager object.
        mNotifyManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Job Service notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifications from Job Service");

            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }
    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        //Create the notification channel
            createNotificationChannel();
            doBackgroundWork(jobParameters);
            PendingIntent contentPendingIntent = PendingIntent.getActivity
                    (this, 0, new Intent(this, MainActivity.class),
                            PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder
                    (this, PRIMARY_CHANNEL_ID)
                    .setContentTitle("CATATAN")
                    .setContentText("Silahkan cek catatan anda hari ini !")
                    .setContentIntent(contentPendingIntent)
                    .setSmallIcon(R.drawable.ic_job_running)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(0, builder.build());
        return false;
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < (24 * 3600) ; i++) {
                    Log.d(TAG, "run: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.d(TAG, "Job finished");
                onStartJob(params);
            }
        }).start();
    }
//    (24 * 3600)

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
