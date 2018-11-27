package com.mindf.utils.runnabletask;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public interface RunnableTask extends Runnable {

    Map<String, Boolean> conditionsDone = new HashMap<>();
    int[] progress = {0};
    boolean[] isRunning = {false};
    //void run();
    void progress(); // user has to set the progress after condition is done
    void end();

    default void start(Activity activity, int refreshRateMilliseconds) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isRunning[0]) {
                    isRunning[0] = true;
                    activity.runOnUiThread(new Thread(RunnableTask.this));
                } else if (progress[0] >= 100){
                    conditionsDone.clear();
                    progress[0] = 0;
                    isRunning[0] = false;
                    stopTimer(timer);
                    activity.runOnUiThread(() -> end());
                } else {
                    progress();
                }
            }
        }, 0, refreshRateMilliseconds);
    }

    default void start(Activity activity, int refreshRateMilliseconds, int minWaitSeconds) {
        final boolean[] isRunning = {false};
        final int[] elapsedTime = {0};
        final Timer timer = new Timer();
        final int multiplier = 1000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isRunning[0]) {
                    isRunning[0] = true;
                    activity.runOnUiThread(new Thread(RunnableTask.this));
                } else if (progress[0] >= 100 && elapsedTime[0] > multiplier * minWaitSeconds) {
                    conditionsDone.clear();
                    progress[0] = 0;
                    stopTimer(timer);
                    end();
                } else {
                    progress();
                }
                elapsedTime[0] += 100;
            }
        }, 0, refreshRateMilliseconds);
    }

    default void progressAfter(boolean condition, String name, int progressAmount) {
        if (condition) {
            if (conditionsDone.get(name) == null) {
                conditionsDone.put(name, true);
                increaseProgress(progressAmount);
                ViewTools.logv("Progress made: " + progressAmount + "!");
            }
        }
    }

    default void stopTimer(Timer timer) {
        timer.cancel();
        timer.purge();
    }

    default void increaseProgress(int value) {
        progress[0] += value;
    }

    default boolean isRunning() {
        return isRunning[0];
    }

    default void setProgress(int progress) {
        RunnableTask.progress[0] = progress;
    }

    default int getProgress() {
        return progress[0];
    }

    default void stopRunning() {
        isRunning[0] = false;
    }
}

