package com.example.myapplication.utils;

import android.util.Log;

import de.robv.android.xposed.XposedBridge;

/**
 * Created by lanshifu on 2018/9/29.
 */

public class LogUtil {
    

    public static void d(String tag,String content){
        Log.d("huanghy-xposed->" + tag, content);
        XposedBridge.log("huanghy******************************");
        XposedBridge.log(content);
        XposedBridge.log("huanghy------------------------------");
    }

    public static void d(String content){
        Log.d("huanghy-xposed->", content);
        XposedBridge.log("huanghy******************************");
        XposedBridge.log(content);
        XposedBridge.log("huanghy------------------------------");
    }

    public static void e(String content){
        Log.e("huanghy-xposed->", content);
    }
}
