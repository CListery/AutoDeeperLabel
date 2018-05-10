package com.yh.autodeep;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yh.autodeep.utils.LogUtils;

import java.util.ArrayList;

/**
 * Created by Clistery on 18-5-9.
 */
public class LabelAdder {
    
    private static final String LOG_TAG = "LabelAdder";
    
    public static void additionalLabel(Context context, LinearLayout labelParent, ArrayList<LabelInfo> labels) {
        labelParent.removeAllViews();
        if (labels == null || labels.isEmpty()) {
            LogUtils.e(LOG_TAG, "additionalLabel: Labels is empty!");
            return;
        }
        
        for (int index = 0; index < labels.size(); index++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (index != 0) {
                layoutParams.leftMargin = ResourcesUtils.dip2px(context, 5);
            } else if (index == labels.size() - 1) {
                layoutParams.rightMargin = ResourcesUtils.dip2px(context, 5);
            }
            LabelInfo labelInfo = labels.get(index);
            LogUtils.d(LOG_TAG, "additionalLabel: " + labelInfo);
            labelParent.addView(createTxtLabel(context, labelInfo), index, layoutParams);
        }
    }
    
    @NonNull
    private static TextView createTxtLabel(Context context, LabelInfo labelInfo) {
        TextView textView = new TextView(context);
        int color = loadColor(labelInfo);
        textView.setBackground(getLabelDrawable(context, color));
        textView.setText(labelInfo.name);
        textView.setTextSize(12);
        textView.setTextColor(Color.parseColor(String.format("#%s", colorString(ResourcesUtils.deeperColor(labelInfo.color)))));
        return textView;
    }
    
    private static int loadColor(LabelInfo labelInfo) {
        String colorString = colorString(labelInfo.color);
        String normalColorString = String.format("#7F%s", colorString);
        LogUtils.d(LOG_TAG, "loadColor: " + normalColorString);
        return Color.parseColor(normalColorString);
    }
    
    @NonNull
    private static Drawable getLabelDrawable(Context context, int color) {
        Drawable drawable = context.getResources().getDrawable(R.drawable.label_background);
        drawable = DrawableCompat.wrap(drawable);
        ColorStateList stateList = ColorStateList.valueOf(color);
        DrawableCompat.setTintList(drawable, stateList);
        return drawable;
    }
    
    private static String colorString(String color) {
        if (color.contains("0x")) {
            color = color.replace("0x", "");
        } else if (color.contains("0X")) {
            color = color.replace("0X", "");
        }
        LogUtils.d(LOG_TAG, "colorString: " + color);
        return color.trim();
    }
}
