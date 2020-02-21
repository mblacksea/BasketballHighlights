package com.oxygenmobile.basketballhighlights.utils;

import android.content.Context;
import android.content.Intent;

public final class NavigateUtils {

    /**
     * @param context current application context
     * @param key     key for Intent
     * @param value   value for Intent
     * @param clazz   destination Activity
     */
    public static void navigateToActivity(Context context, String key, String value, Class<?> clazz) {
        Intent toActivity = new Intent(context, clazz);
        if (key != null && value != null) {
            toActivity.putExtra(key, value);
        }
        toActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(toActivity);
    }

    /**
     * @param context current application context
     * @param clazz   destination Activity
     */
    public static void navigateToActivityWithoutValue(Context context, Class<?> clazz) {
        navigateToActivity(context, null, null, clazz);
    }
}
