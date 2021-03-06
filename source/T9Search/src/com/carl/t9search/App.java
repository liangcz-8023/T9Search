package com.carl.t9search;

import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Sprinkles;
import android.app.Application;

import com.carl.t9search.framework.app.AppInfo;
import com.carl.t9search.framework.app.AppManager;

public class App extends Application {
    
    private static App sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        initDb();
        initManagers();
    }

    private void initDb(){
        Sprinkles sprinkles = Sprinkles.init(this, "search_info.db", 0);
        // database version 0
        Migration version0 = new Migration();
        version0.createTable(AppInfo.class);         // 应用信息
        sprinkles.addMigration(version0);
        
    }
    
    private void initManagers(){
        AppManager appManager = AppManager.init(this);
    }
    
    public static App getContext(){
        return sApp;
    }
}
