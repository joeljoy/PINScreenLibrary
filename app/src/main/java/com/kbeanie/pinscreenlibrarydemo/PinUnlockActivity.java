package com.kbeanie.pinscreenlibrarydemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.kbeanie.pinscreenlibrary.views.PinEntryAuthenticationListener;
import com.kbeanie.pinscreenlibrary.views.PinEntryView;
import com.kbeanie.pinscreenlibrary.views.PinKeyboardView;
import com.kbeanie.pinscreenlibrary.views.PinView;

/**
 * Created by kbibek on 3/27/15.
 */
public class PinUnlockActivity extends ActionBarActivity implements PinEntryAuthenticationListener {
    private PinView pinView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_setup);

        pinView = (PinView) findViewById(R.id.pinView);
        pinView.setModeAuthenticate(this);
    }

    @Override
    public void onPinCorrect() {
        finish();
    }

    @Override
    public void onPinWrong() {

    }
}
