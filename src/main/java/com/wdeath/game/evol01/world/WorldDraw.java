package com.wdeath.game.evol01.world;

import com.wdeath.game.evol01.Config;
import com.wdeath.game.evol01.Screen;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.mind.command.EatBot;
import com.wdeath.game.evol01.mind.command.EatOrganic;
import com.wdeath.game.evol01.mind.command.Phosynthesis;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class WorldDraw {

    public World world;

    public WorldDraw(World world){
        this.world = world;
    }

    public void draw(Graphics2D g){
        if(!Config.DRAW_WORLD)
            return;
        drawSun(g);
        drawWalls(g);
        drawOrganics(g);
        if(Config.SYN_DRAW){
            synchronized (world.bots){
                drawBots(g);
            }
        }else {
            drawBots(g);
//            for (int x = 0; x < world.width; x++) {
//                for (int y = 0; y < world.height; y++) {
//                    Bot bot = world.getBot(x, y);
//                    if (bot != null) {
//                        drawBot(g, bot, x, y);
//                    }
//                }
//            }
        }
    }

    public void drawOrganics(Graphics2D g){
        Color color = new Color(150, 150, 200);
        ArrayList<Organic> orgs = new ArrayList<>(world.organics);
        for(Organic org : orgs){
            if(org == null)
                continue;
            int x = org.x * Screen.WIDTH_CELL;
            int y = org.y * Screen.HEIGHT_CELL;
            g.setColor(color);
            g.fillRect(x, y, Screen.WIDTH_CELL, Screen.HEIGHT_CELL);
        }
    }

    public void drawWalls(Graphics2D g){
        Color color = new Color(10, 10, 60);
        for(Wall wall : world.walls){
            int x = wall.x * Screen.WIDTH_CELL;
            int y = wall.y * Screen.HEIGHT_CELL;
            g.setColor(color);
            g.fillRect(x, y, Screen.WIDTH_CELL, Screen.HEIGHT_CELL);
        }
    }

    private void drawSun(Graphics2D g){
        int rC = Color.YELLOW.getRed();
        int gC = Color.YELLOW.getGreen();
        int bC = Color.YELLOW.getBlue();
        int rl = 0, gl = 0, bl = 0;

        float del = 0;
        Sun sun = world.sun;
        for(int x = sun.x - sun.lengthX; x < sun.x + sun.lengthX; x++){
            for(int y = sun.y - sun.lengthY; y < sun.y + sun.lengthY; y++){
                int xi = world.getXW(x);
                int yi = world.getYW(y);
                del = world.sun.getNormalDelta(xi, yi);
                rl = Math.round(rC * del);
                gl = Math.round(gC * del);
                bl = Math.round(bC * del);
                g.setColor(new Color(rl, gl, bl));
                g.fillRect(xi * Screen.WIDTH_CELL, yi * Screen.HEIGHT_CELL,
                        Screen.WIDTH_CELL, Screen.HEIGHT_CELL);
            }
        }
//        for(int y = 0; y < world.height; y++){
//            for(int x = 0; x < world.width; x++){
//                del = world.sun.getNormalDelta(world.getXW(x), world.getYW(y));
//                rl = Math.round(rC * del);
//                gl = Math.round(gC * del);
//                bl = Math.round(bC * del);
//                g.setColor(new Color(255-rl, 255-gl, 255-bl));
//                g.fillRect(x * Screen.WIDTH_CELL, y * Screen.HEIGHT_CELL,
//                        Screen.WIDTH_CELL, Screen.HEIGHT_CELL);
//            }
//        }
    }

    private void drawBots(Graphics2D g){
        ArrayList<Bot> bots = new ArrayList<>(world.bots);
        for(Iterator<Bot> it = bots.iterator(); it.hasNext(); ){
            Bot b = it.next();
            if(b == null)
                continue;
            drawBot(g, b, b.getX(), b.getY());
        }
    }

    private void drawBot(Graphics2D g, Bot bot, int x, int y){
        Color color = Color.BLUE;
        switch (Config.TYPE_SHOW_BOT){
            case NORM: color = getNormColor(bot); break;
            case HEALTH: color = getHealthColor(bot); break;
            case FOOD: color = getFoodColor(bot); break;
            case BOTS: color = getBotColor(bot); break;
        }
        g.setColor(color);
        g.fillRect(x * Screen.WIDTH_CELL, y * Screen.HEIGHT_CELL, Screen.WIDTH_CELL, Screen.HEIGHT_CELL);

        g.setColor(color.darker());
        g.drawRect(x * Screen.WIDTH_CELL, y * Screen.HEIGHT_CELL, Screen.WIDTH_CELL, Screen.HEIGHT_CELL);

        if(Config.DRAW_DIR_BOT) {
            g.setColor(Color.BLUE);
            int[] dirs = Bot.dirs.get(bot.getDir());
            g.drawLine(
                    x * Screen.WIDTH_CELL + Screen.WIDTH_CELL / 2,
                    y * Screen.HEIGHT_CELL + Screen.HEIGHT_CELL / 2,
                    x * Screen.WIDTH_CELL + Screen.WIDTH_CELL / 2 + (dirs[0] * (Screen.WIDTH_CELL / 2)),
                    y * Screen.HEIGHT_CELL + Screen.HEIGHT_CELL / 2 + (dirs[1] * (Screen.HEIGHT_CELL / 2)));
        }
    }



    private Color getNormColor(Bot bot){
        int[] commands = getCommandEat(bot);
        int num = commands[0] + commands[1] + commands[2];
        if(num == 0)
            num = 1;
        int red = (int)Math.round(255 * (commands[0] / (float)num));
        int green = (int)Math.round(255 * (commands[1] / (float)num));
        int blue = (int)Math.round(255 * (commands[2] / (float)num));
        return new Color(red, green, blue);
    }

    private int[] getCommandEat(Bot bot){
        int idBot = MindEngine.getIdCommand(EatBot.class);
        int idPhoto = MindEngine.getIdCommand(Phosynthesis.class);
        int idOrg = MindEngine.getIdCommand(EatOrganic.class);
        int numB = 0, numP = 0, numO = 0;
        int id = 0;
        for(int i = 0; i < MindEngine.MAX; i++){
            id = bot.getMind().getCommand(i);
            if(id == idBot)
                numB++;
            if(id == idPhoto)
                numP++;
            if(id == idOrg)
                numO++;
        }
        return new int[]{numB, numP, numO};
    }

    public Color getBotColor(Bot bot){
        bot.updateColor();
        return new Color(bot.rC, bot.gC, bot.bC);
    }

    public Color getHealthColor(Bot bot){
        int p = bot.getHealth();
        int c = (int)Math.round(255 * (p / (float)Bot.MAX_HEALTH));
        if(c > 255)
            c = 255;
        if(c < 0)
            c = 0;
        return new Color(255-c, c, 0);
    }

    public Color getFoodColor(Bot bot){
        int p = bot.getFood();
        int c = (int)Math.round(255 * (p / (float)Bot.MAX_FOOD));
        if(c > 255)
            c = 255;
        if(c < 0)
            c = 0;
        return new Color(255-c, c, 0);
    }
}
