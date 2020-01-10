package februarybreeze.github.io.smstodingtalk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Preferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("main", "into main: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preference = new Preferences(this);

        String phone = preference.getPhone();
        if (!TextUtils.isEmpty(phone)) {
            TextView phoneView = (TextView) findViewById(R.id.textValuePhone);
            phoneView.setText(phone);
        }

        String email = preference.getEmail();
        if (!TextUtils.isEmpty(email)) {
            TextView emailView = (TextView) findViewById(R.id.textValueEmail);
            emailView.setText(email);
        }

        startService(new Intent(getBaseContext(), MainService.class));
    }

    public void setPhone(View view) {
        EditText phone = (EditText) findViewById(R.id.textValue);
        String value = phone.getText().toString();
        preference.setPhone(value);

        TextView tokenView = (TextView) findViewById(R.id.textValuePhone);
        tokenView.setText(value);
    }

    public void setEmail(View view) {
        EditText email = (EditText) findViewById(R.id.textValue);
        String value = email.getText().toString();
        preference.setEmail(value);

        TextView tokenView = (TextView) findViewById(R.id.textValueEmail);
        tokenView.setText(value);
    }
}
