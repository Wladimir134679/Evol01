package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Bot;

@IMind(id = 1)
public class Move implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        int dir = mind.getCommand(mind.getUTK() + 1) % 8;
        mind.next();
        if(!mind.getBot().actionHealth(5))
            return;
        int[] dirVector = mind.getBot().getDirLocal(dir);

        mind.stop();

        Bot b = mind.getBot();
        int x = b.getX() + dirVector[0];
        int y = b.getY() + dirVector[1];
        if(b.getWorld().isFreeCellAll(x, y)) {
            b.setPosition(x, y);
        }
    }

}
