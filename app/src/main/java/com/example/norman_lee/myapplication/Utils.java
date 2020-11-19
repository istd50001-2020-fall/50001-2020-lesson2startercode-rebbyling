package com.example.norman_lee.myapplication;

public class Utils {

    static boolean checkValidString (String in) throws NumberFormatException, IllegalArgumentException{

            Double d = Double.valueOf(in);
            if( d < 0) throw new IllegalArgumentException(
                    "Negative value not allowed");
            return true;
    }
}
