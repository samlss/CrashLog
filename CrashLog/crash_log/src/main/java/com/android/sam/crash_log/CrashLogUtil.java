package com.android.sam.crash_log;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.io.File;

public class CrashLogUtil {
    private static CrashLogUtil INSTANCE;
    private String logDirPath;

    private CrashLogUtil(Context context, String logDirPath){
        this.logDirPath = logDirPath;

        checkPermission(context);
        checkLogDir();

        CrashHandler.init(context, logDirPath);
    }

    /**
     * 检查是否有sd card读写权限
     * */
    private void checkPermission(Context context){
        boolean permissionWrite = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;

        boolean permissionRead = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;

        if (!permissionWrite){
            throw new SecurityException("Permission denied (missing WRITE_EXTERNAL_STORAGE permission?)");
        }

        if (!permissionRead){
            throw new SecurityException("Permission denied (missing READ_EXTERNAL_STORAGE permission?)");
        }
    }

    /**
     * 检查log文件夹是否存在
     * */
    private void checkLogDir(){
        File file = new File(logDirPath);
        if (file.exists()){
            if (!file.isDirectory()){
                throw new IllegalArgumentException("Please indicate a directory file path.");
            }
        }else{
            if (!file.mkdir()){
                new MkdirFailException(logDirPath);
            }
        }
    }

    /**
     * crash日志工具初始化
     *
     * @param context 上下文对象
     * @param logDirPath 保存的log文件夹路径
     * */
    public static void init(Context context, String logDirPath){
        if (INSTANCE == null){
            synchronized (CrashLogUtil.class){
                if (INSTANCE == null){
                    INSTANCE = new CrashLogUtil(context, logDirPath);
                }
            }
        }
    }

    public static CrashLogUtil getInstance() {
        return INSTANCE;
    }

    /**
     * 获取crash log防止的文件夹路径
     * */
    public String getCrashLogDirPath(){
        return logDirPath;
    }
}
