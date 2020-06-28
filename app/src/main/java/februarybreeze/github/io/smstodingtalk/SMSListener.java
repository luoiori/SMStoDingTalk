package februarybreeze.github.io.smstodingtalk;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
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
                int slot = 1;
                for (SmsMessage message : messages) {
                    slot = bundle.getInt("slot");//目前该卡的卡位
                    Object phone = bundle.get("phone");
                    Object subscription = bundle.getInt("subscription");//subId
                    Log.d("收到短信", "slot : " + slot + " , phone : " + phone + " , subscription : " + subscription);
                    senderNumber = message.getDisplayOriginatingAddress();
                    date = getDate(message.getTimestampMillis());
                    body.append(message.getDisplayMessageBody());
                }

                if (send(String.valueOf(slot))) {
                    Log.d("LOG", senderNumber);
                    Log.d("LOG", body.toString());
                    startSmsService(context, senderNumber, body.toString(), date, getPhoneTo(slot));
                }
            }
        }
    }

    private String getPhoneTo(int slot) {
        if (slot == 0 && preference.getPhone1() != "") {
            return preference.getPhone1();
        }
        if (slot == 1 && preference.getPhone2() != "") {
            return preference.getPhone2();
        }
        return null;
    }

    private boolean send(String slot) {
        boolean card1 = preference.getCard1();
        boolean card2 = preference.getCard2();
        String email = preference.getEmail();
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        return ("0".equals(slot) && card1) || ("1".equals(slot) && card2);
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

    private void startSmsService(Context context, String num, String body, String date, String to) {
        Intent serviceIntent = new Intent(context, FilterService.class);

        serviceIntent.putExtra(Constant.SMS_Message, body);
        serviceIntent.putExtra(Constant.SMS_NUMBER, num);
        serviceIntent.putExtra(Constant.SMS_DATE, date);
        serviceIntent.putExtra(Constant.SMS_TO, to);
        context.startService(serviceIntent);
    }
}
