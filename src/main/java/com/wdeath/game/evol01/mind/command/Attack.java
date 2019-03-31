package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Bot;

@IMind(id = 17)
public class Attack implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        int dir = mind.getCommand(mind.getUTK() + 1) % 8;
        mind.next();
        if(!mind.getBot().actionHealth(5))
            return;

        int[] dirs = mind.getBot().getDirLocal(dir);
        int x = mind.getBot().getX() + dirs[0];
        int y = mind.getBot().getY() + dirs[1];

        if(mind.getBot().getWorld().isFreeCellBot(x, y))
            return;

        Bot bot = mind.getBot().getWorld().getBot(x, y);
        bot.setHealth(bot.getHealth() - 10);
    }
}
