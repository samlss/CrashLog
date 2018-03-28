package com.android.sam.crash_log;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

public class SystemInfoUtil {
	public static final String getOsInfo() {
		return Build.VERSION.RELEASE;
	}

	/**
	 * 获取设备制造商和获取手机的型号设备名称。
	 * */
	public static final String getPhoneModelWithManufacturer() {
		return Build.MANUFACTURER + "-" + Build.MODEL;
	}

	/**
	 * 获取版本号
	 * @param context 上下文
	 * */
	public static int getVerCode(Context context) {
		int verCode = -1;
		try {
			verCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return verCode;
	}

	/**
	 * 获取网络信息
	 * */
	public static String getNetworkInfo(Context context) {
		String info = "";
		ConnectivityManager connectivityManager;
		NetworkInfo networkInfo;

		connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		networkInfo = connectivityManager.getActiveNetworkInfo();

		if(connectivityManager != null && networkInfo != null) {
			if(networkInfo.getType() == 1) {
				info = networkInfo.getTypeName();
			} else {
				StringBuilder stringBuilder = new StringBuilder();
				TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
				stringBuilder.append(networkInfo.getTypeName());
				stringBuilder.append(" [");
				if(telephonyManager != null) {
					stringBuilder.append(telephonyManager.getNetworkOperatorName());
					stringBuilder.append("#");
				}

				stringBuilder.append(networkInfo.getSubtypeName());
				stringBuilder.append("]");
				info = stringBuilder.toString();
			}
		}
		return info;
	}
}
