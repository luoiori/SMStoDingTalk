package februarybreeze.github.io.smstodingtalk;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

public class EmailService extends IntentService {
    public EmailService() {
        super("EmailService");
    }

    public EmailService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

        String email = intent.getStringExtra(Constant.EMAIL);
        String message = intent.getStringExtra(Constant.SMS_Message);
        String number = intent.getStringExtra(Constant.SMS_NUMBER);
        String date = intent.getStringExtra(Constant.SMS_DATE);
        String to = intent.getStringExtra(Constant.SMS_TO);
        sendMessage(email, message, number, date, to);
    }

    private void sendMessage(String email, String message, String number, String date, String to) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(message)) {
            return;
        }
        try {
            String sub = message.length() >= 20 ? message.substring(0, 20) : message.substring(0, message.length() - 1);
            StringBuilder sb = new StringBuilder();
            sb.append("发件号码：").append(number).append("<br/>");
            sb.append("内容：").append(message).append("<br/>");
            sb.append("时间：").append(date).append("<br/>");
            if (to != null && to != "" && to.trim() != "") {
                sb.append("收件号码：").append(to).append("<br/>");
            }
            SendEmail.sendEmail(email, sub, sb.toString());
        } catch (Exception e) {

        }
    }

}
