
package com.example.getposition.tool.service;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getposition.R;
import com.example.getposition.tool.screen.GetClickPostion;


// AbdroidManifest.xml中添加以下配置:

//<!-- 悬浮窗所需权限 -->
//<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />

// 注册当前悬浮窗服务
//<service
//    android:name="sc.tool.service.FloatService"
//    android:enabled="true"
//    android:exported="true" >
//</service>

/**
 * FloatService.java: 安卓悬浮窗 ----- 2018-6-15 上午11:49:11
 */
public class FloatService extends Service {
    /**
     * 显示悬浮窗
     */
    public static void ShowFloat(Context context) {
        if (Instance == null) {
            Intent intent = new Intent(context, FloatService.class);
            try {
                context.startService(intent);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "自己去开启悬浮框权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 关闭悬浮窗
     */
    public static void HideFloat() {
        // Intent intent = new Intent(context, FloatService.class);
        // context.stopService(intent);

        if (Instance != null) {
            Instance.Hide();
        }
    }

    // -----------------------

    private static FloatService Instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;

        SetFloatView();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 移除悬浮窗，停止服务
     */
    public void Hide() {
        Instance = null;

        manager.removeView(mFloatLayout);    // 移除悬浮窗
        this.stopSelf();                    // 停止服务
        this.onDestroy();
    }

    WindowManager manager;

    LinearLayout mFloatLayout;
    TextView tv_choose;


    LayoutParams params;



    private void SetFloatView() {
        // 从布局文件，生成悬浮窗
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        mFloatLayout = (LinearLayout) inflater.inflate(R.layout.float_view, null);

        // 添加悬浮窗至系统服务
        params = getParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        manager = (WindowManager) getApplication().getSystemService(WINDOW_SERVICE);
        manager.addView(mFloatLayout, params);

        // 浮动窗口按钮
        tv_choose = (TextView) mFloatLayout.findViewById(R.id.tools);

        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        tv_choose.setOnTouchListener(touchListener);    //
        tv_choose.setOnClickListener(clickListener);    // 添加setting按钮响应逻辑，其他按钮可以类似添加





    }

    // 拖动浮标时修改浮标位置
    OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            params.x = (int) event.getRawX() - tv_choose.getMeasuredWidth() / 2;
            params.y = (int) event.getRawY() - tv_choose.getMeasuredHeight();

            manager.updateViewLayout(mFloatLayout, params);

            return false;  // 此处必须返回false，否则OnClickListener获取不到监听
        }
    };

    OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
                GetClickPostion.Show(FloatService.this);  // 切换悬浮窗显示效果
            // Toast.makeText(FloatService.this, "onClick", Toast.LENGTH_SHORT).show();
        }
    };

    private LayoutParams getParams() {
        LayoutParams wmParams = new LayoutParams();
        wmParams.type = LayoutParams.TYPE_PHONE;            // 设置window type
        wmParams.format = PixelFormat.RGBA_8888;            // 设置图片格式，效果为背景透明
        wmParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE;    // 设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;        // 调整悬浮窗显示的停靠位置为左侧置顶

        // 以屏幕左上角为原点，设置x、y初始值（10,10），相对于gravity
        wmParams.x = 10;
        wmParams.y = 10;

        // 设置悬浮窗口长宽数据
        wmParams.width = LayoutParams.WRAP_CONTENT;
        wmParams.height = LayoutParams.WRAP_CONTENT;

        return wmParams;
    }
}
