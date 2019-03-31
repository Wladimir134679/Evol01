package com.wdeath.game.evol01.world;

import com.wdeath.game.evol01.Config;

public class ThreadUpdateBots {

    private Thread thread;
    private boolean stop;
    private World world;
    public float timeUpdate;
    private float timeStart, timeCurrent;

    public boolean run = true;

    public ThreadUpdateBots(){
        thread = new Thread(this::run);
        thread.setPriority(Thread.MAX_PRIORITY);
        stop = false;
    }

    public void setWorld(World w){
        this.world = w;
    }

    public void start(){
        thread.start();
    }

    private void run(){
        timeCurrent = 0;
        timeStart = 0;
        timeUpdate = 0;
        while(run){
            timeStart = System.nanoTime();
            world.update();
            timeCurrent = System.nanoTime();
            timeUpdate = (timeCurrent - timeStart) / 1000000000f;

            if(Config.SLEEP_THREAD_RENDER)
                sleep(Config.TIME_SLEEP_THREAD_RENDER);
            while(stop){
                sleep(1000/60);
            }
        }
    }

    public void setStop(boolean b){
        this.stop = b;
    }

    public boolean isStop(){
        return stop;
    }

    private void sleep(long s){
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
