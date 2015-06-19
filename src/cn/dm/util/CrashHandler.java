package cn.dm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import android.content.Context;
import android.os.Environment;
import android.os.Looper;

/**
 * @Copyright © 2015 sanbo Inc.. All rights reserved.
 * @Title: CrashHandler.java
 * @Description: 在Application中统一捕获异常，保存到文件中下次再打开时上传
 * @Version: 1.0
 * @Create: 2015年6月19日 下午12:46:04
 * @Author: sanbo
 */
public class CrashHandler implements UncaughtExceptionHandler {
    /**
     * 是否开启日志输出,在Debug状态下开启, 在Release状态下关闭以提示程序性能
     * */
    public static final boolean DEBUG = true;
    /** 系统默认的UncaughtException处理类 */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    /** CrashHandler实例 */
    private static volatile CrashHandler INSTANCE;

    /** 程序的Context对象 */
    // private Context mContext;
    /** 保证只有一个CrashHandler实例 */
    private CrashHandler() {
    }

    /** 获取CrashHandler实例 ,单例模式 */
    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            synchronized (CrashHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CrashHandler();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 初始化,注册Context对象, 获取系统默认的UncaughtException处理器, 设置该CrashHandler为程序的默认处理器
     *
     * @param ctx
     */
    public void init(Context ctx) {
        // mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else { // 如果自己处理了异常，则不会弹出错误对话框，则需要手动退出app
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @return true代表处理该异常，不再向上抛异常， 简单来说就是true不会弹出那个错误提示框，false就会弹出
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                // 打印
                L.e(ex);
                // writeFile(ex);
                Looper.loop();
            }

        }.start();
        return true;
    }

    /**
     * write file to file
     *
     * @param ex
     */
    private void writeFile(final Throwable ex) {
        // final String msg = ex.getLocalizedMessage();
        final StackTraceElement[] stack = ex.getStackTrace();
        final String message = ex.getMessage();

        // Toast.makeText(mContext, "程序出错啦:" + message,
        // Toast.LENGTH_LONG).show();
        // 可以只创建一个文件，以后全部往里面append然后发送，这样就会有重复的信息，个人不推荐

        // TODO Auto-generated method stub
        String fileName = "crash-" + System.currentTimeMillis() + ".log";
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(message.getBytes());
            for (int i = 0; i < stack.length; i++) {
                fos.write(stack[i].toString().getBytes());
            }
            fos.flush();
            fos.close();
        } catch (Exception e) {
        }
    }

    // TODO 使用HTTP Post 发送错误报告到服务器 这里不再赘述
    // private void postReport(File file) {
    // 在上传的时候还可以将该app的version，该手机的机型等信息一并发送的服务器，
    // Android的兼容性众所周知，所以可能错误不是每个手机都会报错，还是有针对性的去debug比较好
    // }
}
