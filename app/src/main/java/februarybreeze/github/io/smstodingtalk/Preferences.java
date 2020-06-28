package februarybreeze.github.io.smstodingtalk;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private SharedPreferences mPreference;

    Preferences(Context context) {
        mPreference = context.getSharedPreferences(Constant.SETTING_FILE_NAME, Context.MODE_PRIVATE);
    }

    public Boolean getCard1() {
        return mPreference.getBoolean(Constant.CARD1, false);
    }

    public Boolean getCard2() {
        return mPreference.getBoolean(Constant.CARD2, false);
    }

    public void setCard1(Boolean card1) {
        mPreference.edit().putBoolean(Constant.CARD1, card1).apply();
    }

    public void setCard2(Boolean card2) {
        mPreference.edit().putBoolean(Constant.CARD2, card2).apply();
    }

    public String getEmail() {
        return mPreference.getString(Constant.EMAIL, "");
    }

    public void setEmail(String email) {
        mPreference.edit().putString(Constant.EMAIL, email).apply();
    }

    public String getPhone1() {
        return mPreference.getString(Constant.PHONE1, "");
    }

    public String getPhone2() {
        return mPreference.getString(Constant.PHONE2, "");
    }

    public void setPhone1(String phone1) {
        mPreference.edit().putString(Constant.PHONE1, phone1).apply();
    }

    public void setPhone2(String phone2) {
        mPreference.edit().putString(Constant.PHONE2, phone2).apply();
    }

}
