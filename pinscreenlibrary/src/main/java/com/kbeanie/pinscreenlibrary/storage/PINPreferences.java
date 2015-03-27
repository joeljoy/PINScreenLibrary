package com.kbeanie.pinscreenlibrary.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kbibek on 3/27/15.
 */
public class PINPreferences {
    private final static String FILE = "pin_preferences";
    private SharedPreferences sharedPreferences;

    private final static String KEY_PIN_SETUP = "key_pin_setup";
    private final static String KEY_PIN = "key_pin";

    public PINPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
    }

    public boolean isPinSetup() {
        return sharedPreferences.getBoolean(KEY_PIN_SETUP, false);
    }

    public void setPIN(String pin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PIN, pin);
        editor.putBoolean(KEY_PIN_SETUP, true);
        editor.commit();
    }

    public String getPIN() {
        return sharedPreferences.getString(KEY_PIN, null);
    }

    public void clearPIN() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
