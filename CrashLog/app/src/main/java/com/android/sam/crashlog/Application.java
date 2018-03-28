package com.android.sam.crashlog;

import com.android.sam.crash_log.CrashLogUtil;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CrashLogUtil.init(getBaseContext(), getApplicationContext().getExternalFilesDir("crash_log").getPath());
    }
}
