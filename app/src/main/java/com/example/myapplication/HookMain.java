package com.example.myapplication;


import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
/**
 * TODO
 *
 * @author HuangHy
 * @date 2021/7/4 5:05 下午
 */

public class HookMain implements IXposedHookLoadPackage {


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        //这里填写要拦截的包名
        if (lpparam.packageName.equals("com.xposed.demo")){
            //这里获取要拦截具体类
            Class clazz = lpparam.classLoader.loadClass("com.xposed.demo.MainActivity");
            //这里拦截具体的方法，参数1：欲拦截的类对象，参数二：欲拦截类中的方法名，参数三：欲拦截方法形参类型，参数四：拦截的业务代码
            XposedHelpers.findAndHookMethod(clazz, "testMsg", String.class, new XC_MethodHook() {
                //在拦截前执行,修改传入参数
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    System.out.println("拦截到参数：" + param.args[0]);
                    param.args[0] = "已经HOOK成功";
                    super.beforeHookedMethod(param);
                }
            });
            //这里拦截具体的方法，参数1：欲拦截的类对象，参数二：欲拦截类中的方法名，参数三：拦截的业务代码
            XposedHelpers.findAndHookMethod(clazz, "toastMessage", new XC_MethodHook() {
                //在拦截前执行
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }
                //在拦截后执行，修改方法返回参数
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    System.out.println("劫持成功");
                    param.setResult("你已经被劫持");
                }
            });
        }
    }
}

