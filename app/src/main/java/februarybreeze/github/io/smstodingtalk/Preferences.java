package februarybreeze.github.io.smstodingtalk;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private SharedPreferences mPreference;

    Preferences(Context context) {
        mPreference = context.getSharedPreferences(Constant.SETTING_FILE_NAME, Context.MODE_PRIVATE);
    }

    public String getPhone() {
        return mPreference.getString(Constant.PHONE, "");
    }

    public void setPhone(String phone) {
        mPreference.edit().putString(Constant.PHONE, phone).apply();
    }

    public String getEmail() {
        return mPreference.getString(Constant.EMAIL, "");
    }

    public void setEmail(String email) {
        mPreference.edit().putString(Constant.EMAIL, email).apply();
    }

}
