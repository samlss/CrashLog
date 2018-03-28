package com.android.sam.crash_log;

public class MkdirFailException extends Exception {
    public MkdirFailException(String path){
        super("Failure to create a folder: "+path);
    }
}
