package com.mehapp.android.monitor;

import java.util.*;

import com.mehapp.android.monitor.database.DatabaseHelper;

import android.app.Activity;
import android.app.ActivityManager;
//import android.app.ActivityManager.RunningTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.*;
import android.widget.TextView;


public class AppMonitor extends Activity {
	private static final String DB_NAME = "appMonitor";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLiteDatabase db = openOrCreateDatabase(
        		DB_NAME
           		, SQLiteDatabase.CREATE_IF_NECESSARY
           		, null
           		);
        db.delete(DatabaseHelper.APP_TABLE_NAME, null, null);
        
        Context context = getApplicationContext(); 
        //Resources appR = context.getResources(); 
        ActivityManager actmgr = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE); 
        List<RunningAppProcessInfo> appList = actmgr.getRunningAppProcesses(); 
        for (RunningAppProcessInfo app : appList) { 
            db.execSQL("INSERT INTO " +
            		DatabaseHelper.APP_TABLE_NAME +
                    " Values (null, '" + app.processName + "');");
        }  
        
        Cursor c = db.rawQuery("SELECT name FROM " +
        		DatabaseHelper.APP_TABLE_NAME, null);
        if (c != null ) {
        	if  (c.moveToFirst()) {
        		do {
        			Log.v("Process => ", c.getString(c.getColumnIndex("name")));
        		}while (c.moveToNext());
        	} 
        }
    }
}