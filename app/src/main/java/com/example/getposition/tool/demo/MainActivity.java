
package com.example.getposition.tool.demo;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.example.getposition.R;
import com.example.getposition.tool.service.FloatService;


public class MainActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        show();
    }

    private void show() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                findViewById(R.id.button_show).setVisibility(View.GONE);
                findViewById(R.id.button_hide).setVisibility(View.GONE);
                FloatService.ShowFloat(this);
            } else {
                requestSettingCanDrawOverlays();
            }

        } else {
            FloatService.ShowFloat(this);
        }
        finish();
    }

    private void requestSettingCanDrawOverlays() {
        Toast.makeText(MainActivity.this, "请打开显示悬浮窗开关!", Toast.LENGTH_LONG).show();
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= Build.VERSION_CODES.O) {//8.0以上
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            startActivityForResult(intent, 666);
        } else if (sdkInt >= Build.VERSION_CODES.M) {//6.0-8.0
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 666);
        } else {

        }
    }


    // 显示悬浮窗
    public void Show(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                findViewById(R.id.button_show).setVisibility(View.GONE);
                findViewById(R.id.button_hide).setVisibility(View.GONE);
                FloatService.ShowFloat(this);
            } else {
                requestSettingCanDrawOverlays();
            }

        } else {
            FloatService.ShowFloat(this);
        }
        finish();
    }

    // 隐藏悬浮窗
    public void Hide(View view) {
        FloatService.HideFloat();
        findViewById(R.id.button_show).setVisibility(View.VISIBLE);
        findViewById(R.id.button_hide).setVisibility(View.VISIBLE);
    }

    public void finish() {
//        FloatService.HideFloat();    // 若不调用，Activity结束时依然显示悬浮窗
        super.finish();
    }
}
