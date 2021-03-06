package edu.ncu.safe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import edu.ncu.safe.service.FLoatDesktopWindow;
import edu.ncu.safe.service.FlowsRecordService;
import edu.ncu.safe.util.MyLog;

public class RelifeReceiver extends BroadcastReceiver {
    private static final String TAG = "RelifeReceiver";

    @Override
    public void onReceive(final Context context, Intent intent) {
        MyLog.i(TAG, "RelifeReceiver 开启了");
        // 首次开启

        //浮动窗口网速条
        FLoatDesktopWindow.startService(context);
        //开启流量监控service
        FlowsRecordService.startService(context);
    }
}
