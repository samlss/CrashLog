# CrashLog
| Author        | Samlss           |
| ------------- |:-------------:|
| Email      | 729717222@qq.com |

<br>

安卓Crash日志收集器
当应用发生Crash的时候，会读取系统信息，并与crash信息一起存到本地。

在Application的onCreate()中进行初始化：
```Java
{
   CrashLogUtil.init(getBaseContext(), getApplicationContext().getExternalFilesDir("crash_log").getPath());
}
```

`通过File logDir = new File(CrashLogUtil.getInstance().getCrashLogDirPath());可以获取crash日志保存目录。`
 
![Crash日志图片](https://github.com/samlss/CrashLog/blob/master/screenshot/device-2018-03-28-175834.png)

## 下面为详细的Crash记录信息：

      total_crash_time:2

      current_time: 1
      time: 2018-03-29 20:38:46
      device: itel-itel P41
      android: 7.0
      system: P41-U258-7.0-IN-V001-20170306
      battery: 99 %
      rooted: no
      ram: 28.0% [884.00 MB]
      disk: 62.0% [4.00 GB]
      ver: 1
      network: WIFI

      java.lang.NullPointerException: Test null pointer exception.     
            at com.android.sam.crashlog.MainActivity$1.onClick(MainActivity.java:40)
            at android.view.View.performClick(View.java:5618)
            at android.view.View$PerformClick.run(View.java:22279)
            at android.os.Handler.handleCallback(Handler.java:751)
            at android.os.Handler.dispatchMessage(Handler.java:95)
            at android.os.Looper.loop(Looper.java:154)
            at android.app.ActivityThread.main(ActivityThread.java:6141)
            at java.lang.reflect.Method.invoke(Native Method)
            at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:865)
            at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:755)

      current_time: 2
      time: 2018-03-29 20:38:50
      device: itel-itel P41
      android: 7.0
      system: P41-U258-7.0-IN-V001-20170306
      battery: 99 %
      rooted: no
      ram: 29.0% [884.00 MB]
      disk: 62.0% [4.00 GB]
      ver: 1
      network: WIFI

      java.lang.NullPointerException: Test null pointer exception.
              at com.android.sam.crashlog.MainActivity$1.onClick(MainActivity.java:40)
              at android.view.View.performClick(View.java:5618)
              at android.view.View$PerformClick.run(View.java:22279)
              at android.os.Handler.handleCallback(Handler.java:751)
              at android.os.Handler.dispatchMessage(Handler.java:95)
              at android.os.Looper.loop(Looper.java:154)
              at android.app.ActivityThread.main(ActivityThread.java:6141)
              at java.lang.reflect.Method.invoke(Native Method)
              at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:865)
              at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:755)

        
<br><br>
[我的邮箱729717222@qq.com:cupid:](https://mail.qq.com/cgi-bin/loginpage?autologin=n&errtype=1&clientuin=729717222&param=&sp=&tfcont=22%20serialization%3A%3Aarchive%205%200%200%204%200%200%200%208%20authtype%201%204%209%20clientuin%209%20729717222%206%20domain%206%20qq.com%202%20vm%203%20wsk&r=3d7101e9d6b9c6d02d5ec94b0a1f427e)
