package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Bot;

@IMind(id = 5)
public class CheckFood implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        int p = Bot.MAX_FOOD / MindEngine.MAX;
        int arg = mind.getCommand(mind.getUTK() + 1);
        int s = arg * p;
        if(!mind.getBot().actionHealth(1))
            return;
        int food = mind.getBot().getFood();
        if(food >= s){
            mind.step(mind.getCommand(mind.getUTK()));
        }
    }
}
