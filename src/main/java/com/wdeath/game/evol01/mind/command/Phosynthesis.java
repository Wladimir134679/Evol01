package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Bot;
import com.wdeath.game.evol01.world.Sun;
import com.wdeath.game.evol01.world.World;

@IMind(id = 3)
public class Phosynthesis implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        Bot bot = mind.getBot();
        World world = bot.getWorld();
        Sun sun = world.sun;
        int x = world.getXW(bot.getX());
        int y = world.getYW(bot.getY());

        int p = sun.getPhotosynthesis(x, y);
        mind.getBot().setFood(mind.getBot().getFood() + p);
        mind.getBot().toGreen();
    }
}
