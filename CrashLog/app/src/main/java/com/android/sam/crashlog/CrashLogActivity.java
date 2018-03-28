package com.android.sam.crashlog;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.sam.crash_log.CrashLogUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CrashLogActivity extends AppCompatActivity {
    private TextView tvCrashLog;
    private ProgressDialog progressDialog;
    private final static String TAG = "CrashLog";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_log);
        tvCrashLog = findViewById(R.id.tv_crash_log);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在读取...");
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File logDir = new File(CrashLogUtil.getInstance().getCrashLogDirPath());
                    File[] files = logDir.listFiles();
                    if (files.length == 0){
                        showLog("当前还未发生crash，请在MainActivity中产生Crash事件");
                    }else{
                        for (File file : files){
                            try{
                                String log = "";
                                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

                                String line = null;
                                showLog("文件名："+file.getName());
                                showLog(System.getProperty("line.separator"));
                                showLog(System.getProperty("line.separator"));
                                while ((line = br.readLine()) != null){
                                    showLog(line);
                                    showLog(System.getProperty("line.separator"));
                                }

                                showLog(System.getProperty("line.separator"));
                                showLog(System.getProperty("line.separator"));
                                showLog(System.getProperty("line.separator"));
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }catch (final Exception e){
                    Log.e(TAG,"error: "+e.toString());
                    e.printStackTrace();
                    tvCrashLog.post(new Runnable() {
                        @Override
                        public void run() {
                           showLog("获取Crash日志失败: "+e.getMessage());
                        }
                    });
                }finally {
                    tvCrashLog.post(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        }).start();
    }


    private void showLog(final String log){
        tvCrashLog.post(new Runnable() {
            @Override
            public void run() {
                tvCrashLog.append(log);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing()){
            progressDialog.dismiss();
        }
    }
}
