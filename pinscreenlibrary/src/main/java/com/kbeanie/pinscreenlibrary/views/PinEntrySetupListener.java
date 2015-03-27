package com.kbeanie.pinscreenlibrary.views;

/**
 * Created by kbibek on 3/27/15.
 */
public interface PinEntrySetupListener {
    public void onPinEntered(String pin);
    public void onPinConfirmed(String pin);
    public void onPinMismatch();
    public void onPinSet(String pin);
}
