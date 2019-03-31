package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Bot;
import com.wdeath.game.evol01.world.Sun;
import com.wdeath.game.evol01.world.World;

@IMind(id = 14)
public class CheckLengthSun implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        Bot bot = mind.getBot();
        World world = bot.getWorld();
        Sun sun = world.sun;
        int x = world.getXW(bot.getX());
        int y = world.getYW(bot.getY());

        int p = Math.round(MindEngine.MAX * sun.getNormalDelta(x, y));
        mind.step(mind.getCommand(mind.getUTK() + p));
    }
}
