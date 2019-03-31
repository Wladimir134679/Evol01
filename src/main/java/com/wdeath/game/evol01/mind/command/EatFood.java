package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Bot;

@IMind(id = 4)
public class EatFood implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        Bot b = mind.getBot();
        if(b.getFood() <= 0)
            return;
        b.setHealth(b.getHealth() + 5);
        b.setFood(b.getFood() - 1);
    }
}
