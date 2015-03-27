package com.kbeanie.pinscreenlibrarydemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.kbeanie.pinscreenlibrary.views.PinEntrySetupListener;
import com.kbeanie.pinscreenlibrary.views.PinEntryView;
import com.kbeanie.pinscreenlibrary.views.PinKeyboardView;
import com.kbeanie.pinscreenlibrary.views.PinView;

/**
 * Created by kbibek on 3/27/15.
 */
public class PinSetupActivity extends ActionBarActivity implements PinEntrySetupListener {

    private PinView pinView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_setup);
        pinView = (PinView) findViewById(R.id.pinView);
        pinView.setModeSetup(this);
    }

    @Override
    public void onPinEntered(String pin) {

    }

    @Override
    public void onPinConfirmed(String pin) {

    }

    @Override
    public void onPinMismatch() {

    }

    @Override
    public void onPinSet(String pin) {
        finish();
    }
}
