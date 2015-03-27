package com.kbeanie.pinscreenlibrary.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.kbeanie.pinscreenlibrary.R;

/**
 * Created by kbibek on 3/27/15.
 */
public class PinKeyboardView extends LinearLayout {

    private PinKeyboardListener listener;

    Button pin0;
    Button pin1;
    Button pin2;
    Button pin3;
    Button pin4;
    Button pin5;
    Button pin6;
    Button pin7;
    Button pin8;
    Button pin9;
    Button pinDot;
    Button pinDelete;

    public PinKeyboardView(Context context) {
        super(context);
        init();
    }

    public PinKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PinKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        View view = inflate(getContext(), R.layout.pin_keyboard, null);
        setupButtons(view);
        setupListeners();
        addView(view, params);
    }

    private void setupButtons(View view) {
        pin0 = (Button) view.findViewById(R.id.pin0);
        pin1 = (Button) view.findViewById(R.id.pin0);
        pin2 = (Button) view.findViewById(R.id.pin0);
        pin3 = (Button) view.findViewById(R.id.pin0);
        pin4 = (Button) view.findViewById(R.id.pin0);
        pin5 = (Button) view.findViewById(R.id.pin0);
        pin6 = (Button) view.findViewById(R.id.pin0);
        pin7 = (Button) view.findViewById(R.id.pin0);
        pin8 = (Button) view.findViewById(R.id.pin0);
        pin9 = (Button) view.findViewById(R.id.pin0);
        pinDelete = (Button) view.findViewById(R.id.pinDelete);
        pinDot = (Button) view.findViewById(R.id.pinDot);
    }

    public void setListener(PinKeyboardListener listener) {
        this.listener = listener;
    }

    private void setupListeners() {
        pin0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(PinButtons.BUTTON_0);
            }
        });
        pin1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(PinButtons.BUTTON_1);
            }
        });
        pin2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(PinButtons.BUTTON_2);
            }
        });
        pin3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(PinButtons.BUTTON_3);
            }
        });
        pin4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(PinButtons.BUTTON_4);
            }
        });
        pin5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(PinButtons.BUTTON_5);
            }
        });
        pin6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(PinButtons.BUTTON_6);
            }
        });
        pin7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(PinButtons.BUTTON_7);
            }
        });
        pin8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(PinButtons.BUTTON_8);
            }
        });
        pin9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(PinButtons.BUTTON_9);
            }
        });
        pinDot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(PinButtons.BUTTON_DOT);
            }
        });
        pinDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(PinButtons.BUTTON_DELETE);
            }
        });

    }

    private void setListener(PinButtons which) {
        if (listener != null) {
            listener.onButtonClick(which);
        }
    }
}
