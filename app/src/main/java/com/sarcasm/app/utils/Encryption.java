package com.sarcasm.app.utils;

import android.util.Base64;

import java.nio.charset.Charset;

public final class Encryption {

    /** Encrypts a string */
    public static final String encrypt(final String input){
        return new String(Base64.encode(input.getBytes(), Base64.DEFAULT));
    }

    /** Decrypts a string */
    public static final String decrypt(final String input){
        final String base64 = new String(Base64.decode(input, Base64.DEFAULT));
        return new String(base64.getBytes());
    }
}
