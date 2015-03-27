package com.kbeanie.pinscreenlibrary.views;

import android.content.Context;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kbeanie.pinscreenlibrary.BuildConfig;
import com.kbeanie.pinscreenlibrary.R;

/**
 * Created by kbibek on 3/27/15.
 */
public class PinEntryView extends LinearLayout {

    private final static String TAG = "PinEntryView";

    public final static int MODE_AUTHENTICATE = 1;
    public final static int MODE_SETUP = 2;

    private int mode = -1;
    private String pin;

    private int state;
    private final static int STATE_INITIAL = 1;
    private final static int STATE_CONFIRM = 2;

    private int[] pinArray = new int[4];
    private int[] pinConfirmArray = new int[4];
    private int charIndex = -1;

    private TextView tvMessage;

    private PinImageView[] imgViews = new PinImageView[4];

    private PinEntrySetupListener setupListener;

    public PinEntryView(Context context) {
        super(context);
        init();
    }

    public PinEntryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PinEntryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setModeSetup() {
        unsetVariables();
        tvMessage.setText(R.string.enter_pin);
        this.mode = MODE_SETUP;
        this.state = STATE_INITIAL;
    }

    public void setModeAuthenticate(String pin) {
        this.mode = MODE_AUTHENTICATE;
        this.pin = pin;
    }

    public void setSetupListener(PinEntrySetupListener listener) {
        this.setupListener = listener;
    }

    public void sendKey(PinButtons key) throws Exception {
        if (mode == -1) {
            throw new Exception("Mode is not set");
        } else {
            processKey(key);
        }
    }

    private void init() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        View view = inflate(getContext(), R.layout.pin_entry, null);
        imgViews[0] = (PinImageView) view.findViewById(R.id.pe0);
        imgViews[1] = (PinImageView) view.findViewById(R.id.pe1);
        imgViews[2] = (PinImageView) view.findViewById(R.id.pe2);
        imgViews[3] = (PinImageView) view.findViewById(R.id.pe3);
        tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        addView(view, params);
    }

    private void processKey(PinButtons key) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "Mode : " + mode + " , KEY : " + key.name());
        }
        if (mode == MODE_SETUP) {
            processKeyForSetup(key);
        } else if (mode == MODE_AUTHENTICATE) {
            processKeyForAuthentication(key);
        }
    }

    private void processKeyForSetup(PinButtons key) {
        switch (key) {
            case BUTTON_0:
            case BUTTON_1:
            case BUTTON_2:
            case BUTTON_3:
            case BUTTON_4:
            case BUTTON_5:
            case BUTTON_6:
            case BUTTON_7:
            case BUTTON_8:
            case BUTTON_9:
                if (charIndex == -1 && state == STATE_INITIAL) {
                    tvMessage.setText(R.string.enter_pin);
                }
                if (charIndex >= -1 && charIndex <= 2) {
                    charIndex++;
                    imgViews[charIndex].setSelected(true);

                    if (state == STATE_INITIAL) {
                        pinArray[charIndex] = key.ordinal();
                    } else if (state == STATE_CONFIRM) {
                        pinConfirmArray[charIndex] = key.ordinal();
                    }
                }
                if (charIndex == 3) {
                    processKeyEntryComplete();
                }
                break;
            case BUTTON_DOT:
                break;
            case BUTTON_DELETE:
                if (charIndex > -1) {
                    imgViews[charIndex].setSelected(false);
                    charIndex--;
                }
                break;
        }
    }

    private void processKeyEntryComplete() {
        if (mode == MODE_SETUP) {
            if (state == STATE_INITIAL) {
                tvMessage.setText(R.string.confirm_pin);
                state = STATE_CONFIRM;
                unsetAllPins();
            } else if (state == STATE_CONFIRM) {
                boolean pinEqual = true;
                String setPin = "";
                for (int i = 0; i < 4; i++) {
                    setPin = setPin + pinArray[i];
                    if (pinArray[i] != pinConfirmArray[i]) {
                        pinEqual = false;
                        break;
                    }
                }
                if (pinEqual) {
                    if (BuildConfig.DEBUG) {
                        Log.i(TAG, "Pin Setup : " + setPin);
                    }
                    if (setupListener != null) {
                        setupListener.onPinConfirmed(setPin);
                    }
                } else {
                    if (BuildConfig.DEBUG) {
                        Log.i(TAG, "Pin Setup : Pin Mismatch");
                    }
                    presentErrorUI();
                    if (setupListener != null) {
                        setupListener.onPinMismatch();
                    }
                    state = STATE_INITIAL;
                    tvMessage.setText(R.string.pin_mismatch);
                    unsetAllPins();
                    unsetVariables();
                }
            }
        }
    }

    private void processKeyForAuthentication(PinButtons key) {

    }

    private void presentErrorUI() {
        for (PinImageView iv : imgViews) {
            iv.animateError();
        }
        Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {100, 200};
        v.vibrate(pattern, -1);
    }

    private void unsetAllPins() {
        charIndex = -1;
        postDelayed(new Runnable() {
            @Override
            public void run() {
                for (ImageView ivs : imgViews) {
                    ivs.setSelected(false);
                }
            }
        }, 200);
    }

    private void unsetVariables() {
        for (int i = 0; i < 4; i++) {
            pinArray[i] = -1;
            pinConfirmArray[i] = -1;
        }
    }
}
