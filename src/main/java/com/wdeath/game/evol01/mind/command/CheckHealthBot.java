package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Bot;

@IMind(id = 10)
public class CheckHealthBot implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        int arg = mind.getCommand(mind.getUTK() + 1);
        mind.next();
        int dir = mind.getCommand(mind.getUTK() + 1) % 8;
        mind.next();
        if(!mind.getBot().actionHealth(5))
            return;
        int[] dirs = mind.getBot().getDirLocal(dir);
        if(!mind.getBot().getWorld().isMyBot(mind.getBot(), mind.getBot().getX() + dirs[0], mind.getBot().getY() + dirs[1]))
            return;
        Bot bot = mind.getBot().getWorld().getBot(mind.getBot().getX() + dirs[0], mind.getBot().getY() + dirs[1]);

        int p = Bot.MAX_HEALTH / MindEngine.MAX;
        int s = arg * p;
        int health = bot.getHealth();
        if(health >= s){
            mind.step(mind.getCommand(mind.getUTK()));
        }
    }
}