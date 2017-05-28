package bombey77.sa93;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ромашка on 28.05.2017.
 */

public class MyService extends Service {

    private static final String LOG = "myLogs";

    private int time;

    ExecutorService es;

    Object myObj;

    @Override
    public void onCreate() {
        super.onCreate();

        es = Executors.newFixedThreadPool(4);
        myObj = new Object();
        Log.d(LOG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        time = intent.getIntExtra("time", 1);
        final MyRun mr = new MyRun(time, startId);
        es.execute(mr);
        Log.d(LOG, "onStartService#" + startId + ", time = " + time);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myObj = null;
        Log.d(LOG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class MyRun implements Runnable {

        private int time;
        private int startId;

        MyRun(final int time, final int startId) {
            this.time = time;
            this.startId = startId;
            Log.d(LOG, "create MyRun#" + startId + ", time = " + time);
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Log.d(LOG, "run #" + startId + myObj.getClass());
            } catch (NullPointerException e) {
                Log.d(LOG, "run#" + startId +", nullPointer");
            }
            stop();
        }

        private void stop() {
            stopSelf(startId);
            Log.d(LOG, "stopSelf#" + startId + ", time = " + time);
        }
    }

}
