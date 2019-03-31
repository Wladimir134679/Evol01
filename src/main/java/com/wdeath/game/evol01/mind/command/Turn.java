package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;

@IMind(id = 12)
public class Turn implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        int dir = mind.getCommand(mind.getUTK() + 1) % 8;
        mind.next();
        if(!mind.getBot().actionHealth(1))
            return;

        mind.getBot().setDir(mind.getBot().getDir() + dir);
    }
}
