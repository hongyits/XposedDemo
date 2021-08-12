package com.example.myapplication;


import java.io.File;
import java.lang.reflect.Method;

import android.util.Log;
import com.example.myapplication.module.MainModule;
import com.example.myapplication.utils.LogUtil;
import dalvik.system.PathClassLoader;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;


/**
 * TODO
 *
 * @author HuangHy
 * @date 2021/8/12 11:35 下午
 */


public class Main implements IXposedHookLoadPackage {


    public static final String TAG = "huanghy-xposed->";


    @Override
    public void handleLoadPackage(final LoadPackageParam param) throws Throwable {
        LogUtil.d("开始咯222" + BuildConfig.DEBUG);
        if (BuildConfig.DEBUG) {
            //通过反射实现热更新
            final String packageName = MainModule.class.getPackage().getName();
//            String filePath = String.format("/data/app/%s-%s.apk", packageName, 1);
            //进去adb shell 里面的 /data/app 里面自己找
//            String filePath = "/data/app/com.example.myapplication-iVhyFGXF45rWXwoJuqXRkg==/base.apk";
            String filesPath = "/data/app/";
            String filePath = "";

            File file = new File(filesPath);
            File[] files = file.listFiles();
            for (File subFile : files) {
                String name = subFile.getName();
                if (name.contains("com.example.myapplication")) {
                    filePath = subFile.getPath() + "/base.apk";
                    LogUtil.d("path:" + filePath);
                }
            }
            LogUtil.d("地址在" + filePath);
//            if (!new File(filePath).exists()) {
//                filePath = String.format("/data/app/%s-%s.apk", packageName, 2);
//                if (!new File(filePath).exists()) {
//                    filePath = String.format("/data/app/%s-%s/base.apk", packageName, 1);
//                    if (!new File(filePath).exists()) {
//                        filePath = String.format("/data/app/%s-%s/base.apk", packageName, 2);
//                        if (!new File(filePath).exists()) {
//                            LogUtil.d("lxb-Error:在/data/app找不到APK文件" + filePath);
//                            return;
//                        }
//                    }
//                }
//            }
            final PathClassLoader pathClassLoader = new PathClassLoader(filePath, ClassLoader.getSystemClassLoader());
            final Class<?> aClass = Class.forName(packageName + "." + MainModule.class.getSimpleName(), true, pathClassLoader);
            final Method aClassMethod = aClass.getMethod("handleMyHandleLoadPackage", XC_LoadPackage.LoadPackageParam.class);
            aClassMethod.invoke(aClass.newInstance(), param);
            Log.d(TAG, param.packageName);
        } else {
            Log.d(TAG, "not hot fix - >handleLoadPackage: " + param.packageName);
            try {
                LogUtil.d("6");
                MainModule.handleMyHandleLoadPackage(param);
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "handleLoadPackage: " + e.getMessage());
            }
        }

    }


    public void thislistFiles(File file) {

        if (file.isDirectory()) {
            thislistFiles(file);
        } else {
            LogUtil.d("nowName:" + file.getName() + "path:" + file.getPath());

        }


    }
}
