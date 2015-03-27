package com.kbeanie.pinscreenlibrary.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.kbeanie.pinscreenlibrary.R;

/**
 * Created by kbibek on 3/27/15.
 */
public class PinView extends LinearLayout {
    private PinEntryView pinEntryView;
    private PinKeyboardView pinKeyboardView;

    public PinView(Context context) {
        super(context);
        init();
    }

    public PinView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PinView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        View view = inflate(getContext(), R.layout.pin_view, null);
        pinEntryView = (PinEntryView) view.findViewById(R.id.pinEntryView);
        pinKeyboardView = (PinKeyboardView) view.findViewById(R.id.pinKeyboardView);
        pinKeyboardView.setPinEntryView(pinEntryView);
        addView(view, params);
    }

    public void setModeSetup(PinEntrySetupListener listener) {
        pinEntryView.setModeSetup();
        pinEntryView.setSetupListener(listener);
    }

    public void setModeAuthenticate(PinEntryAuthenticationListener listener) {
        pinEntryView.setModeAuthenticate();
        pinEntryView.setupAuthenticationListener(listener);
    }
}
