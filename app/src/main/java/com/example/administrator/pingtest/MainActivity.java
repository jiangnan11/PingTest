package com.example.administrator.pingtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.stealthcopter.networktools.Ping;
import com.stealthcopter.networktools.ping.PingResult;
import com.stealthcopter.networktools.ping.PingStats;

import java.io.IOException;
import java.net.UnknownHostException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

//    https://github.com/stealthcopter/AndroidNetworkTools

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @InjectView(R.id.tv_test)
    Button tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.tv_test)
    public void onViewClicked() {
//        if(pingIpAddress("192.168.40.14")){
////        if(pingIpAddress("https://www.baidu.com/")){
//            Log.d(TAG, "Ping成功");
//        }else {
//            Log.d(TAG, "Ping失败");
//
//        }

        // Synchronously
//        try {
//            PingResult pingResult = Ping.onAddress("119.75.217.109").setTimeOutMillis(1000).doPing();
////            PingResult pingResult = Ping.onAddress("192.168.40.14").setTimeOutMillis(1000).doPing();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }

        // Asynchronously
        try {
//            Ping.onAddress("192.168.40.14").setTimeOutMillis(1000).setTimes(3).doPing(new Ping.PingListener() {
            Ping.onAddress("192.168.21.1").setTimeOutMillis(1000).setTimes(3).doPing(new Ping.PingListener() {
//            Ping.onAddress("119.75.217.109").setTimeOutMillis(1000).setTimes(3).doPing(new Ping.PingListener() {
                @Override
                public void onResult(PingResult pingResult) {
                    Log.d(TAG, "onResult: "+pingResult.toString());//isReachable=true
                    if(pingResult.toString().contains("isReachable=true")){
                        Log.d("PingPing", "Ping成功");
                    }
                    if(pingResult.toString().contains("isReachable=false")){
                        Log.d("PingPing", "Ping失败");
                    }
                }

                @Override
                public void onFinished(PingStats pingStats) {
                    Log.d(TAG, "onFinished: "+pingStats.toString());
                }
            });
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private boolean pingIpAddress(String ipAddress) {
        try {
            Process process = Runtime.getRuntime().exec("/system/bin/ping -c 3 -w 100 " + ipAddress);
            int status = process.waitFor();
            if (status == 0) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
