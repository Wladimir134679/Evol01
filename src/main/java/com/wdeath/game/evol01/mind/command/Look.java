package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;

@IMind(id = 2)
public class Look implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        int dir = mind.getCommand(mind.getUTK() + 1) % 8;
        mind.next();
        if(!mind.getBot().actionHealth(1))
            return;
        int[] dirsV = mind.getBot().getDirLocal(dir);
        int x = mind.getBot().getWorld().getXW(mind.getBot().getX() + dirsV[0]);
        int y = mind.getBot().getWorld().getYW(mind.getBot().getY() + dirsV[1]);

        if(mind.getBot().getWorld().isFreeCellAll(x, y))
            mind.step(mind.getCommand(mind.getUTK()));

        if(mind.getBot().getWorld().isMyBot(mind.getBot(), x, y))
            mind.step(mind.getCommand(mind.getUTK() + 1));

        if(mind.getBot().getWorld().isWall(x, y))
            mind.step(mind.getCommand(mind.getUTK() + 2));

        if(mind.getBot().getWorld().isOrganic(x, y))
            mind.step(mind.getCommand(mind.getUTK() + 3));
    }
}
