package com.wdeath.game.evol01;

import com.wdeath.game.evol01.world.Bot;

import java.util.ArrayList;

public class PulBot {

    public static ArrayList<Bot> bots;

    public static void init(){
        bots = new ArrayList<>();
    }

    public static Bot getInstance(){
        Bot b = null;
        if(bots.size() <= 0){
            b = new Bot();
        }else{
            b = bots.remove(0);
            b.clear();
        }
        return b;
    }

    public static void remove(Bot bot){
        bots.add(bot);
    }
}

