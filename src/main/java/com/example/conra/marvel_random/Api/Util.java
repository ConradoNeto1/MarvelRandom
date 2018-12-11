package com.example.conra.marvel_random.Api;

import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

    public static String getMd5Hash(String s){
        try{
            MessageDigest m=MessageDigest.getInstance("MD5");
            m.update(s.getBytes(),0,s.length());
            return new BigInteger(1,m.digest()).toString(16);
        }catch (NoSuchAlgorithmException e){
            Log.i("EXCEPTION", e.getMessage());
        }
        return "";
    }
}
