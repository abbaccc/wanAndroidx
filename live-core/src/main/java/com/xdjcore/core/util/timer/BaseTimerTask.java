package com.xdjcore.core.util.timer;

import java.util.TimerTask;

/**
 * Created by xdj on 2018/4/19.
 */

public class BaseTimerTask extends TimerTask {
    private ITimerListener mTimerListener;

    public BaseTimerTask(ITimerListener mTimerListener) {
        this.mTimerListener = mTimerListener;
    }

    @Override
    public void run() {
        if (mTimerListener != null) {
            mTimerListener.onTimer();
        }
    }


}
