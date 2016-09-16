package com.qq.qzone.a1336892373.breath;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private String[] st = { "", "跟 着 节 奏", "深 呼 吸", "放 松", "感 受 自 己", "我 很 快 乐"};
    private int textFlag = 1;
    private TextView text;
    private Handler myhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1 :
                    wenzi();
                    break;
                case 2 :
                    text.setVisibility(View.VISIBLE);
                    text.setText(st[textFlag]);
                    break;
                case 5 :
                    startActivity( new Intent(MainActivity.this, FinishActivity.class) );
                    finish();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.main_text);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try { Thread.sleep(1500); } catch (Exception e) { }
                Message message1 = new Message(); message1.what = 1;    myhandler.sendMessage(message1);         //文字
                try { Thread.sleep(20000); } catch (Exception e) { }
                Message message2 = new Message(); message2.what = 1;    myhandler.sendMessage(message2);         //文字
                try { Thread.sleep(20000); } catch (Exception e) { }
                Message message3 = new Message(); message3.what = 1;    myhandler.sendMessage(message3);         //文字
                try { Thread.sleep(20000); } catch (Exception e) { }
                Message message4 = new Message(); message4.what = 1;    myhandler.sendMessage(message4);         //文字
                try { Thread.sleep(20000); } catch (Exception e) { }
                Message message5 = new Message(); message5.what = 5;    myhandler.sendMessage(message5);         //activity跳转
            }
        }).start();
    }

    private void wenzi(){
        text.setVisibility(View.VISIBLE);
        ObjectAnimator textalp1 = ObjectAnimator.ofFloat(text, "alpha", 1f, 0f).setDuration(1000);
        ObjectAnimator textalp2 = ObjectAnimator.ofFloat(text, "alpha", 0f, 1f).setDuration(1000);
        AnimatorSet set = new AnimatorSet();
        set.play(textalp1);     set.play(textalp2).after(1200);   set.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try { Thread.sleep(1100); } catch (Exception e) { }
                Message message2 = new Message(); message2.what = 2;    myhandler.sendMessage(message2);
            }
        }).start();
        textFlag++;
    }
    
}
