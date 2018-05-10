package com.yh.autodeep.utils;

import android.util.Log;

import com.yh.autodeep.BuildConfig;

/**
 * Created by Clistery on 18-5-10.
 */
public class LogUtils {
    
    private static final boolean DEBUG_FLAG = BuildConfig.DEBUG;
    
    public static void d(String tag, String msg) {
        if (DEBUG_FLAG) {
            Log.d(tag, msg);
        }
    }
    
    public static void e(String tag, String msg) {
        if (DEBUG_FLAG) {
            Log.e(tag, msg);
        }
    }
}
