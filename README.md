# OMAPI Bypass

**USE AT YOUR OWN RISK**  
**Need Xposed**  
Bypass ARA,ARF limit in `AccessControlEnforcer` and grant FullAccess.  

**How it works**  
It hooks `com.android.se.security.AccessControlEnforcer.readSecurityProfile`, disables `mUseArf` and `mUseAra`, and grants `mFullAccess`.  
Note: You may need to kill `com.android.se` by running `su -c killall com.android.se` in adb shell to activate it.

Logcat TAG: `OMAPI-Bypass`  
see: [AccessControlEnforcer.java](https://cs.android.com/android/platform/superproject/main/+/main:packages/apps/SecureElement/src/com/android/se/security/AccessControlEnforcer.java;l=129)  
