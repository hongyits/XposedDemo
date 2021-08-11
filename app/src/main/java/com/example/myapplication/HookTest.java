package com.example.myapplication;

/**
 * TODO
 *
 * @author HuangHy
 * @date 2021/8/9 2:13 上午
 */

import android.util.Log;
import dalvik.system.PathClassLoader;
import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import java.io.File;
import java.lang.reflect.Method;

public class HookTest implements IXposedHookLoadPackage {
    private static String MODULE_PATH = null;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam param) throws Throwable {
        Log.d("muyang", "muyang123123");
        Log.d("muyang", "heihe1112i221hei");

        final String packageName = HookTest.class.getPackage().getName();
        String filePath = String.format("/data/app/%s-%s.apk", packageName, 1);
        if (!new File(filePath).exists()) {
            filePath = String.format("/data/app/%s-%s.apk", packageName, 2);
            if (!new File(filePath).exists()) {
                filePath = String.format("/data/app/%s-%s/base.apk", packageName, 1);
                if (!new File(filePath).exists()) {
                    filePath = String.format("/data/app/%s-%s/base.apk", packageName, 2);
                    if (!new File(filePath).exists()) {
                        XposedBridge.log("Error:在/data/app找不到APK文件" + packageName);
                        return;
                    }
                }
            }
        }
        final PathClassLoader pathClassLoader = new PathClassLoader(filePath, ClassLoader.getSystemClassLoader());
        final Class<?> aClass = Class.forName(packageName + "." + HookTest.class.getSimpleName(), true, pathClassLoader);
        final Method aClassMethod = aClass.getMethod("handleMyHandleLoadPackage", XC_LoadPackage.LoadPackageParam.class);
        aClassMethod.invoke(aClass.newInstance(), param);
    }

    private void xLog(String content) {
        XposedBridge.log("*******************************************************************************************************************************");
        XposedBridge.log(content);
        XposedBridge.log("----------------------------------------------------------------------------------------------------------------------");
    }

    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
        final String packageName = HookTest.class.getPackage().getName();
        String filePath = String.format("/data/app/%s-%s.apk", packageName, 1);
        if (!new File(filePath).exists()) {
            filePath = String.format("/data/app/%s-%s.apk", packageName, 2);
            if (!new File(filePath).exists()) {
                filePath = String.format("/data/app/%s-%s/base.apk", packageName, 1);
                if (!new File(filePath).exists()) {
                    filePath = String.format("/data/app/%s-%s/base.apk", packageName, 2);
                    if (!new File(filePath).exists()) {
                        XposedBridge.log("Error:在/data/app找不到APK文件" + packageName);
                        return;
                    }
                }
            }
        }
        final PathClassLoader pathClassLoader = new PathClassLoader(filePath, ClassLoader.getSystemClassLoader());
        final Class<?> aClass = Class.forName(packageName + "." + HookTest.class.getSimpleName(), true, pathClassLoader);
        final Method aClassMethod = aClass.getMethod("handleMyInitPackageResources", XC_InitPackageResources.InitPackageResourcesParam.class);
        aClassMethod.invoke(aClass.newInstance(), resparam);
    }

    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
    }

    public void handleMyHandleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) {
    }

    public void handleMyInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam){
    }



    public void releaseHook(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable{

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
