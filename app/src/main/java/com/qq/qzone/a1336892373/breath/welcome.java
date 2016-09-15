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

public class welcome extends Activity {

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

        textView = (TextView) findViewById(R.id.welcome_text);
        layout = (RelativeLayout) findViewById(R.id.welcome_layout);
        button = (ImageView) findViewById(R.id.welcome_button);
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
                try { Thread.sleep(6600); } catch (Exception e) { }
                Message message1 = new Message();   message1.what = 1;  myhandler.sendMessage(message1);
                try { Thread.sleep(550); } catch (Exception e) { }
                Message message2 = new Message();   message2.what = 2;  myhandler.sendMessage(message2);
            }
        }).start();
    }

    private void startAnimator() {
        ObjectAnimator layouthu = ObjectAnimator
                .ofInt(layout, "backgroundColor", getResources().getColor(R.color.welcome_shen), getResources().getColor(R.color.welcome_qian))
                .setDuration(3000);
        layouthu.setEvaluator(new ArgbEvaluator());
        ObjectAnimator layoutxi = ObjectAnimator
                .ofInt(layout, "backgroundColor", getResources().getColor(R.color.welcome_qian), getResources().getColor(R.color.welcome_shen))
                .setDuration(3000);
        layoutxi.setEvaluator(new ArgbEvaluator());
        ObjectAnimator layouthu2 = ObjectAnimator
                .ofInt(layout, "backgroundColor", getResources().getColor(R.color.welcome_shen), getResources().getColor(R.color.welcome_qian))
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
        textView.setText("每天忙碌的生活使得我们身心疲惫, 我们又曾何时停下脚步静静地放松自己? 多希望就是现在, 你可以面带微笑，去感受一下自己的呼吸, 身体与心灵交相辉映，或许会构成这世界上最美丽的风景。\n");
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

}
