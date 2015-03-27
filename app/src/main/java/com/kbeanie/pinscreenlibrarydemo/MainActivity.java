package com.kbeanie.pinscreenlibrarydemo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kbeanie.pinscreenlibrary.storage.PINPreferences;


public class MainActivity extends ActionBarActivity {

    private TextView tvStatus;
    private Button buttonSetup;
    private Button buttonClearPIN;
    private Button buttonUnlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeUI();
    }

    private void setupUI() {
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        buttonSetup = (Button) findViewById(R.id.buttonSetup);
        buttonSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPINSetupScreen();
            }
        });
        buttonClearPIN = (Button) findViewById(R.id.buttonClearPIN);
        buttonClearPIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearPIN();
                initializeUI();
            }
        });
        buttonUnlock = (Button) findViewById(R.id.buttonUnlock);
        buttonUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPinUnlockScreen();
            }
        });
    }

    private void showPINSetupScreen() {
        Intent intent = new Intent(this, PinSetupActivity.class);
        startActivity(intent);
    }

    private void showPinUnlockScreen() {
        Intent intent = new Intent(this, PinUnlockActivity.class);
        startActivity(intent);
    }

    private void clearPIN() {
        PINPreferences preferences = new PINPreferences(this);
        preferences.clearPIN();
    }

    private void initializeUI() {
        boolean isPinSetup = isPinSetup();
        tvStatus.setText("PIN SETUP = " + isPinSetup);


        buttonSetup.setEnabled(!isPinSetup);
        buttonClearPIN.setEnabled(isPinSetup);
        buttonUnlock.setEnabled(isPinSetup);
    }

    private boolean isPinSetup() {
        PINPreferences preferences = new PINPreferences(this);
        return preferences.isPinSetup();
    }
}
