package februarybreeze.github.io.smstodingtalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Preferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("main", "into main: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preference = new Preferences(this);

        init();

        startService(new Intent(getBaseContext(), MainService.class));
    }

    private void init() {
        ((CheckBox) findViewById(R.id.card1)).setChecked(preference.getCard1());
        ((CheckBox) findViewById(R.id.card2)).setChecked(preference.getCard2());
        ((EditText) findViewById(R.id.phone1)).setText(preference.getPhone1());
        ((EditText) findViewById(R.id.phone2)).setText(preference.getPhone2());
        ((TextView) findViewById(R.id.email)).setText(preference.getEmail());
    }

    public void setCard1(View view) {
        CheckBox card1 = (CheckBox) findViewById(R.id.card1);
        preference.setCard1(card1.isChecked());
        Toast.makeText(this, "卡槽1保存成功", Toast.LENGTH_SHORT).show();
    }

    public void setCard2(View view) {
        CheckBox card2 = (CheckBox) findViewById(R.id.card2);
        preference.setCard2(card2.isChecked());
        Toast.makeText(this, "卡槽2保存成功", Toast.LENGTH_SHORT).show();
    }

    public void setEmail(View view) {
        preference.setEmail(((EditText) findViewById(R.id.email)).getText().toString());
        preference.setPhone1(((EditText) findViewById(R.id.phone1)).getText().toString());
        preference.setPhone2(((EditText) findViewById(R.id.phone2)).getText().toString());

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }
}
