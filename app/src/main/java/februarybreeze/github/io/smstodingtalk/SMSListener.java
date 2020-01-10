package februarybreeze.github.io.smstodingtalk;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class SMSListener extends BroadcastReceiver {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private Preferences preference;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("broadcast", "into receiver: ");
        preference = new Preferences(context);
        if (Objects.equals(intent.getAction(), SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                StringBuilder body = new StringBuilder();
                String senderNumber = "";
                String date = "";
                int subscription = 1;
                for (SmsMessage message : messages) {
                    Object slot = bundle.get("slot");//目前该卡的卡位
                    Object phone = bundle.get("phone");
                    subscription = bundle.getInt("subscription");//subId
                    Log.d("收到短信" , "slot : " + slot + " , phone : " + phone + " , subscription : " + subscription);
                    senderNumber = message.getDisplayOriginatingAddress();
                    date = getDate(message.getTimestampMillis());
                    body.append(message.getDisplayMessageBody());
                }

                if(send(String.valueOf(subscription))){
                Log.d("LOG", senderNumber);
                Log.d("LOG", body.toString());
                    startSmsService(context,senderNumber,body.toString(),date);
                }
            }
        }
    }

    private boolean send(String subscription){
        String phone = preference.getPhone();
        String email = preference.getEmail();
        if(TextUtils.isEmpty(email)){
            return false;
        }
        if(TextUtils.isEmpty(phone)){
            return false;
        }
        String[] phones = phone.split(",");
        return Arrays.asList(phones).contains(subscription);
    }

    private String getDate(long time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            Date date = new Date(time);
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void startSmsService(Context context,String num,String body,String date) {
        Intent serviceIntent = new Intent(context, FilterService.class);

        serviceIntent.putExtra(Constant.SMS_Message, body);
        serviceIntent.putExtra(Constant.SMS_NUMBER,num );
        serviceIntent.putExtra(Constant.SMS_DATE,date );
        context.startService(serviceIntent);
    }
}
