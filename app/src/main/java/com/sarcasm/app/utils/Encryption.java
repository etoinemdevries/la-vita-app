package com.sarcasm.app.utils;

import android.util.Base64;

import java.nio.charset.Charset;

public final class Encryption {

    /** Encrypts a string */
    public static final String encrypt(final String input){
        return new String(Base64.encode(input.getBytes(getCharset()), Base64.DEFAULT));
    }

    /** Decrypts a string */
    public static final String decrypt(final String input){
        final String base64 = new String(Base64.decode(input, Base64.DEFAULT));
        return new String(base64.getBytes(getCharset()));
    }

    /** Gets the charset used for encrypting and decrypting */
    public static final Charset getCharset() {
        if(Charset.isSupported("UTF32")) return Charset.forName("UTF32");
        return Charset.defaultCharset();
    }
}
