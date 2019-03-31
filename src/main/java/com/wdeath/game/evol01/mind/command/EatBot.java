package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Bot;

@IMind(id = 8)
public class EatBot implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        int dir = mind.getCommand(mind.getUTK() + 1) % 8;
        mind.next();
        int[] dirsV = mind.getBot().getDirLocal(dir);

        int x = mind.getBot().getX();
        int y = mind.getBot().getY();
        Bot b = mind.getBot();
        if(b.getWorld().isFreeCellBot(x + dirsV[0], y + dirsV[1]))
            return;
//        if(b.getWorld().isMyBot(mind.getBot(), x + dirsV[0], y + dirsV[1]))
//            return;

        if(!mind.getBot().actionHealth(5))
            return;

        Bot eatBot = mind.getBot().getWorld().getBot(x + dirsV[0], y + dirsV[1]);
        Bot bot = mind.getBot();
        mind.getBot().getWorld().removeBot(eatBot);
        bot.setFood(bot.getFood() + eatBot.getFood());
        mind.getBot().toRed();
    }
}
