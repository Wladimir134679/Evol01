package com.wdeath.game.evol01.mind.command;

import com.wdeath.game.evol01.mind.CommandMind;
import com.wdeath.game.evol01.mind.IMind;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.world.Organic;

@IMind(id = 16)
public class EatOrganic implements CommandMind {

    @Override
    public void process(MindEngine mind) {
        int dir = mind.getCommand(mind.getUTK() + 1) % 8;
        mind.next();

        int[] dirs = mind.getBot().getDirLocal(dir);
        int x = mind.getBot().getX() + dirs[0];
        int y = mind.getBot().getY() + dirs[1];
        if(!mind.getBot().getWorld().isOrganic(x, y))
            return;
        Organic org = mind.getBot().getWorld().getOrganic(x, y);
        mind.getBot().setFood(mind.getBot().getFood() + org.food);
        org.world.removeOrganic(org);
        mind.getBot().toBlue();
    }
}
