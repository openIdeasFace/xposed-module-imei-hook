package com.me.xposed;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import android.bluetooth.BluetoothAdapter;
import android.net.wifi.WifiInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class MeXposedHook implements IXposedHookLoadPackage {

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		
		try
		{
			if ("".equals(lpparam.packageName))// System
			{
				return;
			}
			Log.d("MeXposedHook","handleLoadPackage " + lpparam.packageName);
			XSharedPreferences pre = new XSharedPreferences(this.getClass()
					.getPackage().getName(), CustomActivity.PREF_NAME);
			HookMethod(TelephonyManager.class.getName(),lpparam.classLoader,"getDeviceId",pre.getString(CustomActivity.IMEI, ""));
			HookMethod(TelephonyManager.class.getName(),lpparam.classLoader,"getSubscriberId",pre.getString(CustomActivity.IMSI, ""));
			HookMethod(TelephonyManager.class.getName(),lpparam.classLoader,"getLine1Number",pre.getString(CustomActivity.MOBILE, ""));
			HookMethod(TelephonyManager.class.getName(),lpparam.classLoader,"getNetworkOperatorName",pre.getString(CustomActivity.ISP_NAME, ""));
			HookMethod(TelephonyManager.class.getName(),lpparam.classLoader,"getNetworkType",pre.getString(CustomActivity.NETWORK_TYPE, ""));
			HookMethod(WifiInfo.class.getName(),lpparam.classLoader,"getMacAddress",pre.getString(CustomActivity.WLAN_MAC, ""));
			HookMethod(WifiInfo.class.getName(),lpparam.classLoader,"getSSID",pre.getString(CustomActivity.WIFI_SSID, ""));
//			HookMethod(WifiInfo.class.getName(),lpparam.classLoader,"getIpAddress",pre.getInt(CustomActivity.IP,0));
			HookMethod(BluetoothAdapter.class.getName(),lpparam.classLoader,"getAddress",pre.getString(CustomActivity.BLUETOOTH_MAC, ""));
			XposedHelpers.findField(android.os.Build.class, "SERIAL").set(null, pre.getString(CustomActivity.SERIES_NUM, ""));
			
		} catch (Throwable e)
		{
			Log.d("MeXposedHook","failed to change getDeviceId" + e.getMessage());
		}
		
	}
	
	public void HookMethod(String className,ClassLoader classLoader,String method,
			final Object result)
	{
		findAndHookMethod(className, classLoader, method, new XC_MethodHook() {

			@Override
			protected void afterHookedMethod(MethodHookParam param) throws Throwable {
				param.setResult(result);
			}
			
		});
	}

	
	
	
}
