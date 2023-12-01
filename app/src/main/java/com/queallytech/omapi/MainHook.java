package com.queallytech.omapi;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {

    private static final String LOG_TAG = "OMAPI-Bypass";
    private static final String SE_PACKAGE = "com.android.se.security.AccessControlEnforcer";
    private static final String SE_CLASS = "readSecurityProfile";
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        Log.d(LOG_TAG, "handleLoadPackage()");
        try{
            XposedHelpers.findAndHookMethod(SE_PACKAGE, lpparam.classLoader, SE_CLASS, BypassOMAPI());

//            XposedHelpers.findAndHookMethod(SE_PACKAGE, lpparam.classLoader, SE_CLASS, new XC_MethodReplacement() {
//                @Override
//                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                    Log.d(LOG_TAG, "replaceHookedMethod()");
//                    XposedHelpers.setBooleanField(param.thisObject, "mUseArf", false);
//                    XposedHelpers.setBooleanField(param.thisObject, "mUseAra", false);
//                    XposedHelpers.setBooleanField(param.thisObject, "mFullAccess", true);
//                    Log.d(LOG_TAG, "Bypassed");
//                    return null;
//                }
//            });

        } catch (Throwable t) {
                Log.e(LOG_TAG, "Failed to hook AccessControlEnforcer", t);
        }
    }
    private static XC_MethodHook BypassOMAPI() {
        return new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Log.d(LOG_TAG, "afterHookedMethod()");
                XposedHelpers.setBooleanField(param.thisObject, "mUseArf", false);
                XposedHelpers.setBooleanField(param.thisObject, "mUseAra", false);
                XposedHelpers.setBooleanField(param.thisObject, "mFullAccess", true);
                Log.i(LOG_TAG, "Bypassed");
            }
        };
    }
}