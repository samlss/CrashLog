package com.android.sam.crashlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "main";
    private Button btnCrashNullPointer;
    private Button btnCrashRuntime;
    private Button btnCrashIllegal;

    private Button btnShowCrashLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCrashNullPointer = findViewById(R.id.btn_crash_null_pointer);
        btnCrashRuntime = findViewById(R.id.btn_crash_runtime);
        btnCrashIllegal = findViewById(R.id.btn_crash_illegal_pointer);
        btnShowCrashLog = findViewById(R.id.btn_show_crash_log);

        btnCrashNullPointer.setOnClickListener(clickListener);
        btnCrashRuntime.setOnClickListener(clickListener);
        btnCrashIllegal.setOnClickListener(clickListener);
        btnShowCrashLog.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_crash_null_pointer:
                    Log.e(TAG, "throw null pointer exception.");
                    throw new NullPointerException("Test null pointer exception.");

                case R.id.btn_crash_runtime:
                    Log.e(TAG,"throw runtime exception.");
                    throw new RuntimeException("Test runtime exception.");

                case R.id.btn_crash_illegal_pointer:
                    throw new IllegalArgumentException("Test illegal exception.");

                case R.id.btn_show_crash_log:
                    Intent intent = new Intent(getBaseContext(), CrashLogActivity.class);
                    startActivity(intent);
                    break;

                default: break;
            }
        }
    };
}
