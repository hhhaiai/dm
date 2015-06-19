package cn.dm.applica;

import android.app.Application;
import cn.dm.util.CrashHandler;

/**
 * @Copyright © 2015 sanbo Inc.. All rights reserved.
 * @Title: Appl.java
 * @Description: custom application 程序初始化的时候，触发错误收集
 * @Version: 1.0
 * @Create: 2015年6月19日 下午1:08:13
 * @Author: sanbo
 */
public class Appl extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        // 注册crashHandler
        crashHandler.init(getApplicationContext());
    }

}