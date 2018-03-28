package com.android.sam.crash_log;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;

public class CrashHandler {
	private Context context;
	private static CrashHandler INSTANCE;
	private String dirPath;

	private CrashHandler(Context context, String dirPath) {
		this.context = context;
		this.dirPath = dirPath;

		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread thread, final Throwable ex) {
				CrashStorage.save(CrashHandler.this.context, CrashHandler.this.dirPath, ex);
				//退出程序
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(1);
			}
		});
	}

	public static void init(Context context, String dirPath) {
		if(INSTANCE == null) {
			synchronized (CrashHandler.class) {
				if (INSTANCE == null) {
					INSTANCE = new CrashHandler(context, dirPath);
				}
			}
		}
	}
}
