package com.yh.autodeep;

import android.content.Context;

import com.yh.autodeep.utils.LogUtils;

import java.lang.reflect.Field;

/**
 * Created by yh on 18-4-28.
 */
public class ResourcesUtils {
    
    private static final String LOG_TAG = "ResourcesUtils";
    
    public static String deeperColor(String originColor) {
        String deeperColor = "FFFFFF";
        if (originColor.length() < 6) {
            return deeperColor;
        } else if (originColor.length() == 8) {
            originColor = originColor.substring(2, 8);
        }
        String originR = originColor.substring(0, 2);
        String originG = originColor.substring(2, 4);
        String originB = originColor.substring(4, 6);
        int r = Integer.parseInt(originR, 16);
        int g = Integer.parseInt(originG, 16);
        int b = Integer.parseInt(originB, 16);
        
        int minChannel = 0;
        if (r > 0 && g > 0 && b > 0) {
            minChannel = Math.min(r, Math.min(g, b));
        }
        boolean ignoreR = false;
        boolean ignoreG = false;
        boolean ignoreB = false;
        if (0 == minChannel) {
            ignoreR = 0 == r;
            ignoreG = 0 == g;
            ignoreB = 0 == b;
        }
        
        String deepR = originR;
        String deepG = originG;
        String deepB = originB;
        
        float deepValue = minChannel * 0.673f;
        if (!ignoreR) {
            deepR = Integer.toHexString((int) (r - deepValue)).replace("0x", "");
            if (deepR.length() < 2) {
                deepR = "0" + deepR;
            }
        }
        if (!ignoreG) {
            deepG = Integer.toHexString((int) (g - deepValue)).replace("0x", "");
            if (deepG.length() < 2) {
                deepG = "0" + deepG;
            }
        }
        if (!ignoreB) {
            deepB = Integer.toHexString((int) (b - deepValue)).replace("0x", "");
            if (deepB.length() < 2) {
                deepB = "0" + deepB;
            }
        }
        
        deeperColor = deepR + deepG + deepB;
        
        LogUtils.d(LOG_TAG, "deeperColor: " + deeperColor);
        return deeperColor;
    }
    
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    
    public static int getResource(String sourceName, Class sourceType) {
        try {
            Field field = sourceType.getField(sourceName);
            return field.getInt(sourceName);
        } catch (NoSuchFieldException e) {
            return 0;
        } catch (IllegalAccessException e) {
            return 0;
        }
    }
}
