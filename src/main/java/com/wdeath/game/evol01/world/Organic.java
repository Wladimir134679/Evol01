package com.wdeath.game.evol01.world;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;

public class Organic{

    public int x, y;
    public int food;
    public int tikHealth;
    public World world;

    private int tikCurrent = 0;

    public void update(){
        tikCurrent++;
        if(tikCurrent < tikHealth)
            return;
        world.removeOrganic(this);
    }

    public void move(){
//        int ny = y;
//        if(ny >= world.height/2)
//            ny++;
//        if(ny < world.height/2)
//            ny--;
//        if(ny < 0)
//            ny = 0;
//        if(ny >= world.height)
//            ny = world.height - 1;
//        ny = world.getYW(ny);
//        if(world.isFreeCellAll(x, ny)){
//            y = ny;
//        }
    }
}
