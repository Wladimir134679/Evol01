package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Bot;

@IMind(id = 15)
public class Infect implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        int dir = mind.getCommand(mind.getUTK() + 1) % 8;
        int mi = mind.getCommand(mind.getUTK() + 2);
        mind.step(2);

        if(!mind.getBot().actionHealth(10))
            return;

        int[] dirs = mind.getBot().getDirLocal(dir);
        int x = mind.getBot().getX() + dirs[0];
        int y = mind.getBot().getY() + dirs[1];
        if(mind.getBot().getWorld().isFreeCellBot(x, y))
            return;
        if(mind.getBot().getWorld().isMyBot(mind.getBot(), x, y))
            return;

        Bot b = mind.getBot().getWorld().getBot(x, y);
        b.getMind().setCommand(mi, mind.getCommand(mi));
    }
}
