package com.qq.qzone.a1336892373.breath;

import com.qq.qzone.a1336892373.breath.tools.huitan;
import com.qq.qzone.a1336892373.breath.tools.myColor;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;

public class dayuanFragment extends Fragment {

    private AnimatorSet animatorSet = new AnimatorSet();
    private ImageView dayuan;
    public Timer timer = new Timer();
    private Handler myhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1 :
                    animatorSet.start();
                    break;
                case 2 :
                    kaichang();
                    animation();
                    huxi();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dayuan, container, false);

        dayuan = (ImageView) view.findViewById(R.id.dayuan);
        myColor color = new myColor(getActivity());
        dayuan.setImageResource(color.getTUPIAN());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try { Thread.sleep(400); } catch (Exception e) { }
                Message message = new Message();    message.what = 2;   myhandler.sendMessage(message);
            }
        }).start();

        return view;
    }

    private void animation(){
        ObjectAnimator suoX = ObjectAnimator.ofFloat(dayuan, "scaleX", 1f, 0.4f).setDuration(3500);
        ObjectAnimator suoY = ObjectAnimator.ofFloat(dayuan, "scaleY", 1f, 0.4f).setDuration(3500);
        ObjectAnimator fangX = ObjectAnimator.ofFloat(dayuan, "scaleX", 0.4f, 1f).setDuration(7500);
        ObjectAnimator fangY = ObjectAnimator.ofFloat(dayuan, "scaleY", 0.4f, 1f).setDuration(7500);
        suoX.setInterpolator(new DecelerateInterpolator());     suoY.setInterpolator(new DecelerateInterpolator());
        fangX.setInterpolator(new DecelerateInterpolator());    fangY.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(suoX).with(suoY);
        animatorSet.play(fangX).with(fangY).after(5000);
    }

    private void huxi() {
        TimerTask task = new TimerTask(){
            public void run() {
                Message message1 = new Message();    message1.what = 1;   myhandler.sendMessage(message1);
            }
        };
        timer.schedule(task, 3000, 15000);
    }

    private void kaichang(){
        dayuan.setVisibility(View.VISIBLE);
        ObjectAnimator fangx = ObjectAnimator.ofFloat(dayuan, "scaleX", 0.05f, 1f).setDuration(1500);
        ObjectAnimator fangy = ObjectAnimator.ofFloat(dayuan, "scaleY", 0.05f, 1f).setDuration(1500);
        fangx.setInterpolator(new huitan());    fangy.setInterpolator(new huitan());
        fangx.start();  fangy.start();
    }

}
