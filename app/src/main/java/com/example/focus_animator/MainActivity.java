package com.example.focus_animator;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ImageView ivWifi, ivBlueT, ivUsb;

    private WifiManager wifiManager;
    private ConnectivityManager connectivityManager;

    //TODO 定义广播接收器，监听设备连接情况变化时需要进行的操作


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取imageview并读取系统权限
        ivWifi = findViewById(R.id.wifi);
        ivBlueT = findViewById(R.id.bt);
        ivUsb = findViewById(R.id.usb);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        initViews();
    }

    private void initViews() {
        //获取设备的wifi、bluetooth、usb的连接情况并初始化ui
        //获取wifi信号
        if (wifiManager.isWifiEnabled()) {
            WifiInfo info = wifiManager.getConnectionInfo();
            //获取信号强度
            if (info != null) {
                int strong = WifiManager.calculateSignalLevel(info.getRssi(), 3);
                ivWifi.setImageResource(getResIdByStrength(strong));
            }
        } else {
            //wifi未连接
            ivWifi.setImageResource(R.drawable.ic_wifi_off);
        }

        //获取蓝牙信号
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null || !btAdapter.isEnabled()) {
            // 蓝牙未开启或设备不支持蓝牙
            ivBlueT.setImageResource(R.drawable.ic_bluetooth_off);
            Log.d("Bluetooth", "Bluetooth is not enabled or device does not support Bluetooth");
        } else {
            Set<BluetoothDevice> bondedDevices = btAdapter.getBondedDevices();

            if (bondedDevices != null && !bondedDevices.isEmpty()) {
                for (BluetoothDevice b : bondedDevices
                ) {
                    if (b.isConnected()) {
                        Log.d(TAG, "initViews: device connected" + b.getAddress());
                        ivBlueT.setImageResource(R.drawable.ic_bluetooth);
                        break;
                    }

                }
                //TODO 蓝牙未连接
            }
        }


        //TODO 获取usb信号
    }

    private int getResIdByStrength(int strong) {
        switch (strong) {
            case 0:
                return R.drawable.ic_wifi1;
            case 1:
                return R.drawable.ic_wifi2;
            case 2:
                return R.drawable.ic_wifi3;
            default:
                return R.drawable.ic_wifi_off;
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //需要截断返回按钮将注释解开即可
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            return true;
        }

        return super.onKeyDown(keyCode, event);

    }
}