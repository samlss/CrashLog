package com.android.sam.crash_log;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

class CrashStorage {
	public static final void save(Context context, String dirPath, Throwable ex) {
		Writer writer = null;
		PrintWriter printWriter = null;
		String stackTrace = "";

		try {
			writer = new StringWriter();
			printWriter = new PrintWriter(writer);
			ex.printStackTrace(printWriter);
			Throwable cause = ex.getCause();

			while (cause != null) {
				cause.printStackTrace(printWriter);
				cause = cause.getCause();
			}
			stackTrace = writer.toString();
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (printWriter != null) {
				printWriter.close();
			}
		}

        String signature = stackTrace.replaceAll("\\([^\\(]*\\)", "");
		String filename = MD5Util.MD5(signature);
		if(TextUtils.isEmpty(filename)) {
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		String timestamp = sdf.format(date);

		RandomAccessFile randomAccessFile = null;
		try {
			File logFile = new File(dirPath, filename + ".crashlog");
			randomAccessFile = new RandomAccessFile(logFile, "rw");

			int count = 1;
			if(logFile.exists()) {
                try {
					String line = randomAccessFile.readLine();
                    if(!TextUtils.isEmpty(line) && line.startsWith("total_crash_time")) {
                        int index = line.indexOf(":");
                        if(index != -1) {
                            String count_str = line.substring(++index);
                            if(count_str != null) {
                                count_str = count_str.trim();
                                count = Integer.parseInt(count_str);
                                count++;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
			}else{
				logFile.createNewFile();
			}

			String countLine = "total_crash_time:"+count;
			randomAccessFile.seek(0);
			randomAccessFile.writeBytes(countLine);
			randomAccessFile.writeBytes(System.getProperty("line.separator"));
			randomAccessFile.writeBytes(System.getProperty("line.separator"));

			randomAccessFile.seek(randomAccessFile.length());
			randomAccessFile.writeBytes(CrashInfoCollector.collect(context, timestamp, stackTrace, count));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (randomAccessFile != null) {
				try {
					randomAccessFile.close();
				} catch (IOException e) {
                    e.printStackTrace();
				}
			}
		}
	}
}
