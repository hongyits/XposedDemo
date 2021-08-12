package com.example.myapplication.module;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


import com.example.myapplication.utils.LogUtil;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by lanxiaobin on 2018/1/29.
 */

public class MainModule extends BaseModule{


    public static Handler mHandler = new Handler();


    /**
     * 入口，通过反射调用
     *
     * @param param
     */
    public static void handleMyHandleLoadPackage(final XC_LoadPackage.LoadPackageParam param) throws ClassNotFoundException {

        LogUtil.d(" handle packageName " + param.packageName);

//        if (param.packageName.equals("com.lanshifu.xposeddemo")) {
//            hook_method("com.lanshifu.xposeddemo.ui.MainActivity", param.classLoader, "isOpen", new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    Log.d("test", "hookMainActivity -- >initView");
//                    param.setResult(true);
//
//                    Toast.makeText((Context) param.thisObject, "模块已经启动", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }





    }

}
