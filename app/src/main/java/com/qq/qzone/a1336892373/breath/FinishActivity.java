package com.qq.qzone.a1336892373.breath;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qq.qzone.a1336892373.breath.tools.myColor;

import java.util.Timer;
import java.util.TimerTask;

public class FinishActivity extends Activity {

    AnimatorSet set = new AnimatorSet();
    public Timer timer = new Timer();
    private Button yanseButton;
    private TextView xiatext;
    private TextView dayuantext;
    private ImageView dayuan;
    private Handler myhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1 :
                    cishu();
                    kaichang();
                    break;
                case 2 :
                    wenzi();
                    break;
                case 3 :
                    anniu();
                    break;
                case 4 :
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
        setContentView(R.layout.activity_finish);


        dayuan = (ImageView) findViewById(R.id.finish_dayuan);
        dayuantext = (TextView) findViewById(R.id.finish_dayuantext);
        xiatext = (TextView) findViewById(R.id.finish_xiatext);
        yanseButton = (Button) findViewById(R.id.finish_button);

        yanseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(FinishActivity.this, chooseActivity.class));
            }
        });

        huanse();
        init();
        huxi();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try { Thread.sleep(500); }  catch (Exception e) { }
                Message message1 = new Message(); message1.what = 1;    myhandler.sendMessage(message1);
                try { Thread.sleep(1300); }  catch (Exception e) { }
                Message message2 = new Message(); message2.what = 2;    myhandler.sendMessage(message2);
                try { Thread.sleep(1300); }  catch (Exception e) { }
                Message message3 = new Message(); message3.what = 3;    myhandler.sendMessage(message3);
            }
        }).start();

    }

    private void kaichang(){
        dayuan.setVisibility(View.VISIBLE);
        ObjectAnimator fangx = ObjectAnimator.ofFloat(dayuan, "scaleX", 0.05f, 1f).setDuration(2000);
        ObjectAnimator fangy = ObjectAnimator.ofFloat(dayuan, "scaleY", 0.05f, 1f).setDuration(2000);
        fangx.setInterpolator(new DecelerateInterpolator());    fangy.setInterpolator(new DecelerateInterpolator());
        fangx.start();  fangy.start();
    }

    private void cishu(){
        SharedPreferences pre= getSharedPreferences("mydata", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        int cishu = pre.getInt("cishu", 1);
        dayuantext.setText(cishu+"");
        editor.putInt("cishu", cishu+1);
        editor.commit();
    }

    private void wenzi(){
        ObjectAnimator xiatextalp = ObjectAnimator.ofFloat(xiatext, "alpha", 0f, 1f).setDuration(2000);
        xiatext.setVisibility(View.VISIBLE);
        xiatextalp.start();
    }

    private void anniu(){
        ObjectAnimator buttonX = ObjectAnimator.ofFloat(yanseButton, "scaleX", 0.05f, 1f).setDuration(800);
        ObjectAnimator buttonY = ObjectAnimator.ofFloat(yanseButton, "scaleY", 0.05f, 1f).setDuration(800);
        buttonX.setInterpolator(new DecelerateInterpolator());      buttonY.setInterpolator(new DecelerateInterpolator());
        yanseButton.setVisibility(View.VISIBLE);
        buttonX.start();  buttonY.start();
    }

    private void huanse(){
        myColor color = new myColor(FinishActivity.this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.finish_layout);
        layout.setBackgroundColor(getResources().getColor(color.getQIAN()));
        dayuan.setImageResource(color.getTUPIAN());
        GradientDrawable myGrad = (GradientDrawable) yanseButton.getBackground();
        myGrad.setColor(getResources().getColor(color.getLIANG()));
    }

    @Override
    protected void onResume() {
        huanse();
        super.onResume();
    }

    private void huxi() {

        TimerTask task = new TimerTask(){
            public void run() {
                Message message = new Message();    message.what = 4;   myhandler.sendMessage(message);
            }
        };
        timer.schedule(task, 3600, 4000);
    }

    private void init(){
        ObjectAnimator suoX = ObjectAnimator.ofFloat(dayuan, "scaleX", 1f, 0.92f).setDuration(1000);
        ObjectAnimator suoY = ObjectAnimator.ofFloat(dayuan, "scaleY", 1f, 0.92f).setDuration(1000);
        ObjectAnimator fangX = ObjectAnimator.ofFloat(dayuan, "scaleX", 0.92f, 1f).setDuration(1000);
        ObjectAnimator fangY = ObjectAnimator.ofFloat(dayuan, "scaleY", 0.92f, 1f).setDuration(1000);
        set.play(suoX).with(suoY);
        set.play(fangX).with(fangY).after(1500);
    }

}
