package februarybreeze.github.io.smstodingtalk;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MySingleton {
    private static MySingleton mInstance;
    private Queue emailQueue;

    private MySingleton(Context context) {
        emailQueue = getRequestQueue();
    }

    private Queue getRequestQueue() {
        if (emailQueue == null) {
            emailQueue = new LinkedBlockingQueue();
        }
        return emailQueue;
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public <T> void addToRequestQueue(Email email) {
        emailQueue.add(email);
    }
}
