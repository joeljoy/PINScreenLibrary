package com.kbeanie.pinscreenlibrarydemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.kbeanie.pinscreenlibrary.views.PinEntryAuthenticationListener;
import com.kbeanie.pinscreenlibrary.views.PinEntryView;
import com.kbeanie.pinscreenlibrary.views.PinKeyboardView;

/**
 * Created by kbibek on 3/27/15.
 */
public class PinUnlockActivity extends ActionBarActivity implements PinEntryAuthenticationListener {

    private PinEntryView pinEntryView;
    private PinKeyboardView pinKeyboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_setup);

        pinKeyboardView = (PinKeyboardView) findViewById(R.id.pinKeyboardView);
        pinEntryView = (PinEntryView) findViewById(R.id.pinEntryView);
        pinEntryView.setModeAuthenticate();
        pinKeyboardView.setPinEntryView(pinEntryView);
        pinEntryView.setupAuthenticationListener(this);
    }

    @Override
    public void onPinCorrect() {
        finish();
    }

    @Override
    public void onPinWrong() {

    }
}
