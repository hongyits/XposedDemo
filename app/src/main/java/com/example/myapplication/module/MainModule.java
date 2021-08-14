package com.example.myapplication.module;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


import com.example.myapplication.utils.LogUtil;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by lanxiaobin on 2018/1/29.
 */

public class MainModule extends BaseModule {

    /**
     * 入口，通过反射调用
     *
     * @param lpp
     * @throws ClassNotFoundException
     */
    public static void handleMyHandleLoadPackage(final XC_LoadPackage.LoadPackageParam lpp) throws ClassNotFoundException {
        LogUtil.d("handle packageName:" + lpp.packageName);
        if (lpp.packageName.contains("com.tencent.mm")) {
            LogUtil.d("进来微信包");
            XposedHelpers.findAndHookMethod("com.tencent.mm.ui.chatting.d.bd", lpp.classLoader, "ctM", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    LogUtil.d("invoke1");
                    String msg = (String) param.args[0];
                    LogUtil.d("获取到消息:" + msg);
                }

            });

        }


    }

}
