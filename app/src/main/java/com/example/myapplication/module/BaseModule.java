package com.example.myapplication.module;

import android.util.Log;


import com.example.myapplication.Config;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class BaseModule {

    protected static void hook_method(String className, ClassLoader classLoader, String methodName,
                             Object... parameterTypesAndCallback) {
        try {
            XposedHelpers.findAndHookMethod(className, classLoader, methodName, parameterTypesAndCallback);
        } catch (Exception e) {
            XposedBridge.log(e);
            xLog(e.getMessage());
        }
    }


    protected static void xLog(String content) {
        XposedBridge.log("lxb*******************************************************************************************************************************");
        XposedBridge.log(content);
        XposedBridge.log("lxb----------------------------------------------------------------------------------------------------------------------");

        if (Config.isDebug) {
        }
    }
}
