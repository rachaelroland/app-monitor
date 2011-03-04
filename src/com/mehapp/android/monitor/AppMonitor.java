package com.mehapp.android.monitor;

import java.util.*;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.*;
import android.widget.TextView;


public class AppMonitor extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        setContentView(tv);
        Context context = getApplicationContext(); 
        Resources appR = context.getResources(); 
        ActivityManager actmgr = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE); 
        List<RunningAppProcessInfo> appList = actmgr.getRunningAppProcesses(); 
        for (int i=0;i<appList.size();i++) { 
        	RunningAppProcessInfo rti = (RunningAppProcessInfo)appList.get(i);
        	Log.v("Process", rti.processName);
        	tv.append(rti.processName + "\n");
            //Process p = new Process(rti.pid,rti.processName,rti.pkgList); 
            //tv.setText(e);
            //allProcesses.add(p); 
            //items[i] = p.getProcessName(); 
        }  
    }
}