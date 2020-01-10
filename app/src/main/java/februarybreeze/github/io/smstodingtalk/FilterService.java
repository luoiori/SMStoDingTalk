package februarybreeze.github.io.smstodingtalk;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class FilterService extends IntentService {
    public FilterService() {
        super("FilterService");
    }

    public FilterService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

        String message = intent.getStringExtra(Constant.SMS_Message);
        String number = intent.getStringExtra(Constant.SMS_NUMBER);
        String date = intent.getStringExtra(Constant.SMS_DATE);
        Preferences preferences = new Preferences(this);

        String email = preferences.getEmail();

        Intent serviceIntent = new Intent(this.getApplicationContext(), EmailService.class);
        serviceIntent.putExtra(Constant.EMAIL, email);
        serviceIntent.putExtra(Constant.SMS_Message, message);
        serviceIntent.putExtra(Constant.SMS_NUMBER, number);
        serviceIntent.putExtra(Constant.SMS_DATE, date);
        this.startService(serviceIntent);
    }
}
