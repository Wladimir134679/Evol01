package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Bot;

@IMind(id = 9)
public class CheckFoodBot implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        int p = Bot.MAX_FOOD / MindEngine.MAX;
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

        int s = arg * p;
        int health = bot.getFood();
        if(health >= s){
            mind.step(mind.getCommand(mind.getUTK()));
        }
    }
}
