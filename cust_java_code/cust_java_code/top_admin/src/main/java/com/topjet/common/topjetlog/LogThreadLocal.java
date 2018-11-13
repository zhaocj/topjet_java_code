package com.topjet.common.topjetlog;

/**
 * 用于保存每个线程的个日志
 * Created by pengtao on 9/15/16.
 */
public class LogThreadLocal {

    public static ThreadLocal<String> logMessage = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "";
        }

        @Override
        public void set(String value) {
            super.set(  get() + value);
        }

        @Override
        public String get() {
            return super.get();
        }
    };


    public static ThreadLocal<String> log4jMessage = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "";
        }

        @Override
        public void set(String value) {
            super.set(  get() + value);
        }

        @Override
        public String get() {
            return super.get();
        }
    };


}
