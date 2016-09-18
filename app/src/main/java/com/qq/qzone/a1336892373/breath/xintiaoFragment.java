package com.qq.qzone.a1336892373.breath;

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
import com.qq.qzone.a1336892373.breath.tools.myColor;
import java.util.Timer;
import java.util.TimerTask;

public class xintiaoFragment extends Fragment {

    private ImageView dayuan;
    public Timer timer = new Timer();
    AnimatorSet set = new AnimatorSet();
    private Handler myhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            set.start();
            super.handleMessage(msg);
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dayuan, container, false);

        dayuan = (ImageView) view.findViewById(R.id.dayuan);
        myColor color = new myColor(getActivity());
        dayuan.setImageResource(color.getTUPIAN());

        init();
        dayuan.setVisibility(View.VISIBLE);
        zhuanbian();

        return view;
    }


    private void zhuanbian() {

        TimerTask task = new TimerTask(){
            public void run() {
                Message message = new Message();
                message.what = 0;
                myhandler.sendMessage(message);
            }
        };
        timer.schedule(task, 700, 1500);
    }

    private void init(){
        ObjectAnimator suoX = ObjectAnimator.ofFloat(dayuan, "scaleX", 1f, 0.75f).setDuration(85);
        ObjectAnimator suoY = ObjectAnimator.ofFloat(dayuan, "scaleY", 1f, 0.75f).setDuration(85);
        ObjectAnimator fangX = ObjectAnimator.ofFloat(dayuan, "scaleX", 0.8f, 1f).setDuration(230);
        ObjectAnimator fangY = ObjectAnimator.ofFloat(dayuan, "scaleY", 0.8f, 1f).setDuration(230);
        suoX.setInterpolator(new DecelerateInterpolator());
        suoY.setInterpolator(new DecelerateInterpolator());
        fangX.setInterpolator(new DecelerateInterpolator());
        fangX.setInterpolator(new DecelerateInterpolator());
        set.play(suoX).with(suoY).before(fangX).with(fangY);
    }

}
