
package com.example.getposition.tool.screen;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;

import com.example.getposition.tool.component.ActivityComponent;


/**
 * 获取屏幕点击处的坐标
 */
public class GetClickPostion extends ActivityComponent {
    /**
     * 显示当前Activity
     */
    public static void Show(Context context) {


        Intent intent = new Intent(context, GetClickPostion.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);


        // context.startActivityForResult(intent, CODE_ClickPostion);
    }

    // ----------------

    @Override
    public void Init(Bundle savedInstanceState) {
        this.setContentView("screen_postion");
        Click("buttonReset");
    }

    @Override
    public void Click(String viewId) {
        if (viewId.equals("buttonReset")) {
            this.LinearLayout("linear1").setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setPosition((int) event.getX(), (int) event.getY());
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(event);

    }

    boolean isSet = false;
    int x;
    int y;
    public static final int CODE_ClickPostion = 50001;

    public void setPosition(int x, int y) {
        LinearLayout("linear1").setVisibility(View.VISIBLE);
        this.TextView("tv_position").setText("" + x + ", " + y);
    }

}
