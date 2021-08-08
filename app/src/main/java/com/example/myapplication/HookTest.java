package com.example.myapplication;

/**
 * TODO
 *
 * @author HuangHy
 * @date 2021/8/9 2:13 上午
 */

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookTest implements IXposedHookLoadPackage {

    //com.example.myapplication.HookTest
    //com.example.myapplication.HookTest
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.d("muyang", "muyang");
        Log.d("muyang", " fuckfuck!!!!!!!!!");
        XposedBridge.log(" fuckfuck!!!!!!!!!");
        if (loadPackageParam.packageName.equals("com.muyang.xposeddemo")) {
            XposedBridge.log(" has Hooked!");
            Class clazz = loadPackageParam.classLoader.loadClass("com.");
            XposedHelpers.findAndHookMethod(clazz, "publicFunc", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    //XposedBridge.log(" has Hooked!");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult("你已被劫持");
                }
            });
        }
    }
}
