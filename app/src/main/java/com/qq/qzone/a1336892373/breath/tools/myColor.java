package com.qq.qzone.a1336892373.breath.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.qq.qzone.a1336892373.breath.R;

public class myColor {

    public final static int CHEN = 0;
    public final static int BLUE = 1;
    public final static int FEN = 2;
    public final static int ZI = 3;
    public final static int YELLOW = 4;
    public final static int GREEN = 5;

    public int SHEN = R.color.welcome_shen;
    public int QIAN= R.color.welcome_qian;
    public int LIANG = R.color.welcome_liang;
    public int tupian = R.drawable.dayuan_green;

    public myColor(Context context){
        SharedPreferences pre = context.getSharedPreferences("mydata", Activity.MODE_PRIVATE);
        int yanseflag = GREEN;
        yanseflag = pre.getInt("yanse", GREEN);
        switch ( yanseflag ){
            case CHEN :
                SHEN = R.color.welcome_chen_shen;
                QIAN = R.color.welcome_chen_qian;
                LIANG = R.color.welcome_chen_liang;
                tupian = R.drawable.dayuan_chen;
                break;
            case BLUE :
                SHEN = R.color.welcome_blue_shen;
                QIAN = R.color.welcome_blue_qian;
                LIANG = R.color.welcome_blue_liang;
                tupian = R.drawable.dayuan_blue;
                break;
            case FEN :
                SHEN = R.color.welcome_fen_shen;
                QIAN = R.color.welcome_fen_qian;
                LIANG = R.color.welcome_fen_liang;
                tupian = R.drawable.dayuan_fen;
                break;
            case ZI :
                SHEN = R.color.welcome_zi_shen;
                QIAN = R.color.welcome_zi_qian;
                LIANG = R.color.welcome_zi_liang;
                tupian = R.drawable.dayuan_zi;
                break;
            case YELLOW:
                SHEN = R.color.welcome_yellow_shen;
                QIAN = R.color.welcome_yellow_qian;
                LIANG = R.color.welcome_yellow_liang;
                tupian = R.drawable.dayuan_yellow;
                break;
            case GREEN :
                SHEN = R.color.welcome_shen;
                QIAN = R.color.welcome_qian;
                LIANG = R.color.welcome_liang;
                tupian = R.drawable.dayuan_green;
                break;
        }
    }

    public int getSHEN() {
        return SHEN;
    }

    public int getQIAN() {
        return QIAN;
    }

    public int getLIANG() {
        return LIANG;
    }

    public int getTUPIAN() {
        return tupian;
    }

}
