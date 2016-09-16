package com.qq.qzone.a1336892373.breath.tools;

import android.view.animation.Interpolator;

public class huitan implements Interpolator {
    private final float mTension;

    public huitan() {
        mTension = 2.0f;
    }

    public huitan(float tension) {
        mTension = tension;
    }

    public float getInterpolation(float t) {
        // _o(t) = t * t * ((tension + 1) * t + tension)
        // o(t) = _o(t - 1) + 1
        t -= 1.0f;
        return t * t * ((mTension + 1) * t + mTension) + 1.0f;
    }

}