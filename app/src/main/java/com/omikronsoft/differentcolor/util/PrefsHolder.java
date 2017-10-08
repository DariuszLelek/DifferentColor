package com.omikronsoft.differentcolor.util;

import android.content.SharedPreferences;

import com.omikronsoft.differentcolor.R;

/**
 * Created by Dariusz Lelek on 10/8/2017.
 * dariusz.lelek@gmail.com
 */

public class PrefsHolder {
    private static SharedPreferences prefs;


    public static SharedPreferences getPrefs() {
        return prefs;
    }

    public static void setPrefs(SharedPreferences prefs) {
        prefs = prefs;
    }
}
