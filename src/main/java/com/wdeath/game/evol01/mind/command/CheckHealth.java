package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Bot;

@IMind(id = 6)
public class CheckHealth implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        int p = Bot.MAX_HEALTH / MindEngine.MAX;
        int arg = mind.getCommand(mind.getUTK() + 1);
        mind.next();
        if(!mind.getBot().actionHealth(1))
            return;
        int s = arg * p;
        int health = mind.getBot().getHealth();
        if(health >= s){
            mind.step(mind.getCommand(mind.getUTK()));
        }
    }
}
