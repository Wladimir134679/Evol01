package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.PulBot;
import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Bot;

@IMind(id = 7)
public class Fission implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        Bot bot = mind.getBot();
        int dir = mind.getCommand(mind.getUTK() + 1) % 8;
        mind.next();
        int[] dirsV = bot.getDirLocal(dir);

        int x = bot.getWorld().getXW(bot.getX() + dirsV[0]);
        int y = bot.getWorld().getYW(bot.getY() + dirsV[1]);
        if(!bot.getWorld().isFreeCellAll(x, y))
            return;

        bot.setFood(bot.getFood() - 50);
        bot.setHealth(bot.getHealth() - 10);
        if(bot.getFood() <= 0 || bot.getHealth() <= 0) {
            mind.stop();
            return;
        }


        Bot b = PulBot.getInstance();
        b.setFood(5);
        b.setHealth(10);
        mind.cloneMind(b.getMind(), 0.25f);
        b.setX(x);
        b.setY(y);
        bot.getWorld().addBot(b, bot.getWorld().getIndexBot(bot) + 1);
    }
}
