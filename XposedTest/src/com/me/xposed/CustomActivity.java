package com.me.xposed;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.xposedtest.R;

import de.robv.android.xposed.XSharedPreferences;

public class CustomActivity extends Activity implements View.OnClickListener{

	private Button randomAll;
	private Button save;
	private Button seriesNumRandom;
	private Button ISPNameRandom;
	private Button NetworkTypeRandom;
	private Button mobileRandom;
	private Button imeiRandom;
	private Button imsiRandom;
	private Button ipRandom;
	private Button wlanMacRandom;
	private Button bluetoothMacRandom;
	private Button wifiSSIDRandom;
	
	private EditText seriesNum;
	private EditText ISPName;
	private EditText NetworkType;
	private EditText mobile;
	private EditText imei;
	private EditText imsi;
	private EditText ip;
	private EditText wlanMac;
	private EditText bluetoothMac;
	private EditText wifiSSID;
	
	private XSharedPreferences preference;
	public static final String PREF_NAME = "custom_prefs";
	public static final String SERIES_NUM = "series_num";
	public static final String ISP_NAME = "isp_name";
	public static final String NETWORK_TYPE = "network_type";
	public static final String MOBILE = "mobile";
	public static final String IMEI = "imei";
	public static final String IMSI = "imsi";
	public static final String IP = "ip";
	public static final String WLAN_MAC = "wlan_mac";
	public static final String BLUETOOTH_MAC = "bluetooth_mac";
	public static final String WIFI_SSID = "wifi_ssid";
	
	private TelephonyManager tm;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom);
		initControl();
	}

	private void initControl() {
		tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);		 
		WifiInfo info = wifi.getConnectionInfo();
		BluetoothAdapter bAdapt= BluetoothAdapter.getDefaultAdapter();  
		preference = new XSharedPreferences(this.getClass()
						.getPackage().getName(), PREF_NAME);/*getSharedPreferences(PREF_NAME,Context.MODE_WORLD_READABLE);*/
		seriesNum = (EditText)findViewById(R.id.seriesNum);
		ISPName = (EditText)findViewById(R.id.ISPName);
		NetworkType = (EditText)findViewById(R.id.NetworkType);
		mobile = (EditText)findViewById(R.id.mobile);
		imei = (EditText)findViewById(R.id.imei);
		imsi = (EditText)findViewById(R.id.imsi);
		ip = (EditText)findViewById(R.id.ip);
		wlanMac = (EditText)findViewById(R.id.wlanMac);
		bluetoothMac = (EditText)findViewById(R.id.bluetoothMac);
		wifiSSID = (EditText)findViewById(R.id.wifiSSID);
		randomAll = (Button)findViewById(R.id.randomAll);
		save = (Button)findViewById(R.id.save);
		seriesNumRandom = (Button)findViewById(R.id.seriesNumRandom);
		ISPNameRandom = (Button)findViewById(R.id.ISPNameRandom);
		NetworkTypeRandom = (Button)findViewById(R.id.NetworkTypeRandom);
		mobileRandom = (Button)findViewById(R.id.mobileRandom);
		imeiRandom = (Button)findViewById(R.id.imeiRandom);
		imsiRandom = (Button)findViewById(R.id.imsiRandom);
		ipRandom = (Button)findViewById(R.id.ipRandom);
		wlanMacRandom = (Button)findViewById(R.id.wlanMacRandom);
		bluetoothMacRandom = (Button)findViewById(R.id.bluetoothMacRandom);
		wifiSSIDRandom = (Button)findViewById(R.id.wifiSSIDRandom);
		randomAll.setOnClickListener(this);
		save.setOnClickListener(this);
		seriesNumRandom.setOnClickListener(this);
		ISPNameRandom.setOnClickListener(this);
		NetworkTypeRandom.setOnClickListener(this);
		mobileRandom.setOnClickListener(this);
		imeiRandom.setOnClickListener(this);
		imsiRandom.setOnClickListener(this);
		ipRandom.setOnClickListener(this);
		wlanMacRandom.setOnClickListener(this);
		bluetoothMacRandom.setOnClickListener(this);
		wifiSSIDRandom.setOnClickListener(this);
		
		try {
			seriesNum.setText(Build.SERIAL);
			ISPName.setText(tm.getNetworkOperatorName());
			NetworkType.setText(tm.getNetworkType());
			mobile.setText(tm.getLine1Number());
			imei.setText(tm.getDeviceId());
			imsi.setText(tm.getSubscriberId());
			wlanMac.setText(info.getMacAddress());
			bluetoothMac.setText(bAdapt.getAddress());
			wifiSSID.setText(info.getSSID());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.randomAll:
			seriesNum.setText(Utils.randomNum(19)+"a");
			ISPName.setText(Utils.randomISP());
			NetworkType.setText(Utils.randomnetworktype());
			mobile.setText(Utils.randomPhone());
			imei.setText(Utils.randomNum(15));
			imsi.setText(Utils.randomNum(15));
			ip.setText(Utils.randomIP());
			wlanMac.setText(Utils.randomMac());
			bluetoothMac.setText(Utils.randomMac());
			wifiSSID.setText(Utils.randomChar());
			break;
		case R.id.save:
			Editor editor = preference.edit();
			editor.putString(SERIES_NUM, seriesNum.getText().toString());
			editor.putString(ISP_NAME, ISPName.getText().toString());
			editor.putString(NETWORK_TYPE, NetworkType.getText().toString());
			editor.putString(MOBILE, mobile.getText().toString());
			editor.putString(IMEI, imei.getText().toString());
			editor.putString(IMSI, imsi.getText().toString());
//			editor.putString(IP, ip.getText().toString());
			editor.putString(WLAN_MAC, wlanMac.getText().toString());
			editor.putString(BLUETOOTH_MAC, bluetoothMac.getText().toString());
			editor.putString(WIFI_SSID, wifiSSID.getText().toString());
			editor.apply();
			break;
		case R.id.seriesNumRandom:
			seriesNum.setText(Utils.randomNum(19)+"a");
			break;
		case R.id.ISPNameRandom:
			ISPName.setText(Utils.randomISP());
			break;
		case R.id.NetworkTypeRandom:
			NetworkType.setText(Utils.randomnetworktype());
			break;
		case R.id.mobileRandom:
			mobile.setText(Utils.randomPhone());
			break;
		case R.id.imeiRandom:
			imei.setText(Utils.randomNum(15));
			break;
		case R.id.imsiRandom:
			imsi.setText(Utils.randomNum(15));
			break;
		case R.id.ipRandom:
			ip.setText(Utils.randomIP());
			break;
		case R.id.wlanMacRandom:
			wlanMac.setText(Utils.randomMac());
			break;
		case R.id.bluetoothMacRandom:
			bluetoothMac.setText(Utils.randomMac());
			break;
		case R.id.wifiSSIDRandom:
			wifiSSID.setText(Utils.randomChar());
			break;			
		}		
	}	
}
