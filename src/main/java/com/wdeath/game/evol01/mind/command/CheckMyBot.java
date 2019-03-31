package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;

@IMind(id = 13)
public class CheckMyBot implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        int dir = mind.getCommand(mind.getUTK() + 1) % 8;
        mind.next();
        if(!mind.getBot().actionHealth(1))
            return;
        int[] dirs = mind.getBot().getDirLocal(dir);
        int x = mind.getBot().getX() + dirs[0];
        int y = mind.getBot().getY() + dirs[1];
        if (mind.getBot().getWorld().isMyBot(mind.getBot(), x, y)) {
            mind.step(mind.getCommand(mind.getUTK() + 1));
        }
    }
}
