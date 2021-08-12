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
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpp) throws Throwable {
        Log.d("muyang", " mmuyang fuck!!!!!!!!!");
        if (lpp.packageName.contains("com.tencent.mm")) {
            Log.d("muyang", " 我进来了fuck!!!!!!!!!");
            XposedBridge.log(" 我进来了fuck!!!!!!!!!");
            XposedHelpers.findAndHookMethod("com.tencent.mm.ui.chatting.h.b", lpp.classLoader, "a", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    String arg = (String) param.args[0];
                    Log.d("muyang", "before" + arg);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    String arg = (String) param.args[0];
                    Log.d("muyang", "after" + arg);
                }
            });

        }


    }
}
