package com.wdeath.game.evol01;

import com.wdeath.game.GameCanvas;
import com.wdeath.game.GameScreen;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.mind.command.CheckFood;
import com.wdeath.game.evol01.mind.command.EatFood;
import com.wdeath.game.evol01.mind.command.EatOrganic;
import com.wdeath.game.evol01.mind.command.Phosynthesis;
import com.wdeath.game.evol01.world.*;

import java.awt.*;
import java.util.Random;

public class Screen implements GameScreen {

    public static int WIDTH_CELL = 5, HEIGHT_CELL = 5;

    public WorldDraw worldDraw;
    public World world;
    public ThreadUpdateBots threadUpdateBots;

    @Override
    public void open() {
        WIDTH_CELL = Config.CELL_WIDTH;
        HEIGHT_CELL = Config.CELL_HEIGHT;
        int w = MainApp.APP.getConfig().width / WIDTH_CELL;
        int h = MainApp.APP.getConfig().height / HEIGHT_CELL;
        System.out.println(w + " : " + h);
        world = new World(w, h);
        Random rnd = new Random(Config.SEED_RND_START_SPAWN);
        for(int i = 0; i < w * (h / 2); i++){
            Bot b = PulBot.getInstance();
            for(int mi = 0; mi < MindEngine.MAX_MIND; mi++){
                int m = 0;
                int r = rnd.nextInt(4);
                switch (r){
                    case 0: m = MindEngine.getIdCommand(Phosynthesis.class); break;
                    case 1: m = MindEngine.getIdCommand(EatOrganic.class); break;
                    case 2: m = MindEngine.getIdCommand(EatFood.class); break;
                    case 3: m = rnd.nextInt(MindEngine.MAX); break;
                }
                b.getMind().setCommand(mi, m);
            }
            int x = rnd.nextInt(w);
            int y = rnd.nextInt(h);
            b.setX(x);
            b.setY(y);
            world.addBot(b);
        }

        float f = 0.01f;
        for(int x = 0; x < world.width; x++){
            for(int y = 0; y < world.height; y++){
                if(rnd.nextFloat() > f)
                    continue;
                Wall wall = new Wall();
                wall.x = x;
                wall.y = y;
                world.addWall(wall);
            }
        }

        worldDraw = new WorldDraw(world);


        threadUpdateBots = new ThreadUpdateBots();
        threadUpdateBots.setWorld(world);
        threadUpdateBots.start();
    }

    @Override
    public void draw(GameCanvas canvas) {
        worldDraw.draw(canvas.getGraphics());
        drawInfo(canvas.getGraphics());
//        g.setColor(Color.BLACK);
//        g.drawString(timeUpdate, 10, 15);
//        g.drawString(tik, 10, 35);
    }

    public void drawInfo(Graphics2D g){
        if(!Config.DRAW_INFO_WORLD)
            return;
        g.setColor(new Color(50, 50, 50 , 255 / 2));
        g.fillRect(3, 3, 300, 20 * 12);
        String timeUpdate = String.valueOf("Time Update: " + threadUpdateBots.timeUpdate);
        String hz = String.valueOf("Num Update Per sec: " + (1 / threadUpdateBots.timeUpdate));
        String tik = String.valueOf("Tik: " + world.tik);
        String botLife = String.valueOf("MaxTikeBot Life: " + world.maxTikLifeBot);
        String typeDraw = String.valueOf("Type Draw: " + Config.TYPE_SHOW_BOT);
        String numBot = String.valueOf("Num Bot: " + world.bots.size());
        String numOrganic = String.valueOf("Num Organic: " + world.organics.size());
        String isDrawDir = String.valueOf("Draw Dir Bot: " + Config.DRAW_DIR_BOT);
        String isSleepThread = String.valueOf("SleepThread: " + Config.SLEEP_THREAD_RENDER);
        String isStop = String.valueOf("Stop render: " + threadUpdateBots.isStop());
        String isSyn = String.valueOf("SYN Draw and Render: " + Config.SYN_DRAW);
        String isDrawWorld = String.valueOf("Draw World: " + Config.DRAW_WORLD);


        g.setColor(Color.WHITE);
        int id = 0;
        g.drawString(timeUpdate, 5, 15 + 20 * id++);
        g.drawString(hz, 5, 15 + 20 * id++);
        g.drawString(tik, 5, 15 + 20 * id++);
        g.drawString(botLife, 5, 15 + 20 * id++);
        g.drawString(typeDraw, 5, 15 + 20 * id++);
        g.drawString(numBot, 5, 15 + 20 * id++);
        g.drawString(numOrganic, 5, 15 + 20 * id++);
        g.drawString(isDrawDir, 5, 15 + 20 * id++);
        g.drawString(isSleepThread, 5, 15 + 20 * id++);
        g.drawString(isStop, 5, 15 + 20 * id++);
        g.drawString(isSyn, 5, 15 + 20 * id++);
        g.drawString(isDrawWorld, 5, 15 + 20 * id++);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void close() {

    }
}
