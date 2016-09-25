package com.qq.qzone.a1336892373.breath;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qq.qzone.a1336892373.breath.tools.myColor;

import java.util.Timer;
import java.util.TimerTask;

public class welcome extends Activity {

    public Timer timer = new Timer();
    AnimatorSet set = new AnimatorSet();
    private myColor color;
    private TextView textView;
    private RelativeLayout layout;
    private ImageView button;
    private Handler myhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1 :
                    wenzi();
                    break;
                case 2 :
                    anniu();
                    break;
                case 3 :
                    huxi_init();
                    huxi();
                    break;
                case 4 :
                    go();
                    break;
                case 5 :
                    set.start();
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
        setContentView(R.layout.activity_welcome);

        layout = (RelativeLayout) findViewById(R.id.welcome_layout);
        color = new myColor(welcome.this);
        layout.setBackgroundColor(getResources().getColor(color.getSHEN()));

        button = (ImageView) findViewById(R.id.welcome_button);
        button.setImageResource(color.getTUPIAN());
        textView = (TextView) findViewById(R.id.welcome_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(welcome.this, MainActivity.class));
                finish();
            }
        });

        startAnimator();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try { Thread.sleep(5600); } catch (Exception e) { }
                Message message1 = new Message();   message1.what = 1;  myhandler.sendMessage(message1);
                try { Thread.sleep(500); } catch (Exception e) { }
                Message message2 = new Message();   message2.what = 2;  myhandler.sendMessage(message2);
                try { Thread.sleep(500); } catch (Exception e) { }
                Message message3 = new Message();   message3.what = 3;  myhandler.sendMessage(message3);
                try { Thread.sleep(800); } catch (Exception e) { }
                Message message4 = new Message();   message4.what = 4;  myhandler.sendMessage(message4);

            }
        }).start();
    }

    private void startAnimator() {
        ObjectAnimator layouthu = ObjectAnimator
                .ofInt(layout, "backgroundColor", getResources().getColor(color.getSHEN()), getResources().getColor(color.getQIAN()))
                .setDuration(3000);
        layouthu.setEvaluator(new ArgbEvaluator());
        ObjectAnimator layoutxi = ObjectAnimator
                .ofInt(layout, "backgroundColor", getResources().getColor(color.getQIAN()), getResources().getColor(color.getSHEN()))
                .setDuration(2000);
        layoutxi.setEvaluator(new ArgbEvaluator());
        ObjectAnimator layouthu2 = ObjectAnimator
                .ofInt(layout, "backgroundColor", getResources().getColor(color.getSHEN()), getResources().getColor(color.getQIAN()))
                .setDuration(3000);
        layouthu2.setEvaluator(new ArgbEvaluator());
        ObjectAnimator textalp = ObjectAnimator
                .ofFloat(textView, "alpha", 1f, 0f)
                .setDuration(500);
        ObjectAnimator textalp2 = ObjectAnimator
                .ofFloat(textView, "alpha", 0f, 1f)
                .setDuration(500);
        ObjectAnimator textmove = ObjectAnimator
                .ofFloat(textView, "translationY", textView.getBottom(), textView.getBottom() -100 )
                .setDuration(900);
        textmove.setInterpolator(new DecelerateInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(layouthu).before(layoutxi);
        animatorSet.play(textalp).after(layoutxi).with(layouthu2);
        animatorSet.play(textalp2).after(textalp).before(textmove);
        animatorSet.start();
    }

    private void wenzi(){

        TextView xiao = (TextView) findViewById(R.id.welcome_xiaotext);
        xiao.setVisibility(View.VISIBLE);
        textView.setTextSize(18);
        textView.setText(getResources().getString(R.string.changpian));
    }

    private void anniu(){
        button.setVisibility(View.VISIBLE);
        ObjectAnimator buttonX = ObjectAnimator.ofFloat(button, "scaleX", 0.1f, 1f)
                .setDuration(900);
        ObjectAnimator buttonY = ObjectAnimator.ofFloat(button, "scaleY", 0.1f, 1f)
                .setDuration(900);

        buttonX.setInterpolator(new DecelerateInterpolator());          buttonY.setInterpolator(new DecelerateInterpolator());
        buttonX.start();    buttonY.start();
    }

    private void go(){
        ImageView go;
        go = (ImageView) findViewById(R.id.welcome_go);
        go.setVisibility(View.VISIBLE);
        ObjectAnimator goalp = ObjectAnimator.ofFloat(go, "alpha", 0.1f, 1f).setDuration(1200);
        goalp.start();
    }

    private void huxi() {

        TimerTask task = new TimerTask(){
            public void run() {
                Message message = new Message();    message.what = 5;   myhandler.sendMessage(message);
            }
        };
        timer.schedule(task, 400, 4300);
    }

    private void huxi_init(){
        ObjectAnimator suoX = ObjectAnimator.ofFloat(button, "scaleX", 1f, 0.92f).setDuration(1000);
        ObjectAnimator suoY = ObjectAnimator.ofFloat(button, "scaleY", 1f, 0.92f).setDuration(1000);
        ObjectAnimator fangX = ObjectAnimator.ofFloat(button, "scaleX", 0.92f, 1f).setDuration(1000);
        ObjectAnimator fangY = ObjectAnimator.ofFloat(button, "scaleY", 0.92f, 1f).setDuration(1000);
        set.play(suoX).with(suoY);
        set.play(fangX).with(fangY).after(1200);
    }

}
