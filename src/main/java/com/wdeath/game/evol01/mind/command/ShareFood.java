package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Bot;

@IMind(id = 11)
public class ShareFood implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        int dir = mind.getCommand(mind.getUTK() + 1) % 8;
        mind.next();
        if(!mind.getBot().actionHealth(1))
            return;
        if(mind.getBot().getFood() <= 20)
            return;
        int[] dirs = mind.getBot().getDirLocal(dir);
        if(!mind.getBot().getWorld().isMyBot(mind.getBot(), mind.getBot().getX() + dirs[0], mind.getBot().getY() + dirs[1]))
            return;

        Bot my = mind.getBot().getWorld().getBot(mind.getBot().getX() + dirs[0], mind.getBot().getY() + dirs[1]);
        my.setFood(my.getFood() + 15);
        mind.getBot().setFood(mind.getBot().getFood() - 15);

    }
}
