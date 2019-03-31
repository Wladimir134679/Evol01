package com.wdeath.game.evol01.world;

import com.wdeath.game.evol01.PulBot;

import java.util.ArrayList;

public class World {

    public ArrayList<Bot> bots;
    public int width, height;
    public Bot[][] botsMap;

    public ArrayList<Wall> walls;
    public Wall[][] wallsMap;

    public ArrayList<Organic> organics;
    public Organic[][] organicsMap;
    private int inter = 0;

    public Sun sun;

    public long tik, tikUpdateEnd;
    public long maxTikLifeBot;

    public World(int w, int h){
        this.width = w;
        this.height = h;
        this.botsMap = new Bot[width][height];
        this.wallsMap = new Wall[width][height];
        this.organicsMap = new Organic[width][height];
        walls = new ArrayList<>();
        bots = new ArrayList<>();
        organics = new ArrayList<>();
        sun = new Sun();
        sun.x = width /2;
        sun.y = height/2;
        sun.lengthX = height/2;
        sun.lengthY = height/2;
        sun.world = this;
        tik = 0;
        tikUpdateEnd = 0;
        maxTikLifeBot = 0;
    }

    public int getIndexBot(Bot b){
        return bots.indexOf(b);
    }

    public boolean addBot(Bot b){
        return addBot(b, bots.size());
    }

    public boolean addBot(Bot b, int i){
        int x = getXW(b.getX());
        int y = getYW(b.getY());
        if (botsMap[x][y] != null)
            return false;
        b.setWorld(this);
        bots.add(b);
        botsMap[x][y] = b;
        return true;
    }

    public Bot getBot(int x, int y){
        x = getXW(x);
        y = getYW(y);
        return botsMap[x][y];
    }

    public void removeBot(Bot b){
        int x = getXW(b.getX());
        int y = getYW(b.getY());
//        int i = bots.indexOf(b);
//        if(i <= inter)
//            inter--;
        PulBot.remove(b);
        bots.remove(b);
        botsMap[x][y] = null;
    }

    public boolean isFreeR(int x, int y){
        boolean m;

        m = isFreeCellAll(x + 1, y);
        if(!m)
            m = isFreeCellAll(x - 1, y);
        if(!m)
            m = isFreeCellAll(x, y + 1);
        if(!m)
            m = isFreeCellAll(x, y - 1);

        if(!m)
            m = isFreeCellAll(x + 1, y + 1);
        if(!m)
            m = isFreeCellAll(x + 1, y - 1);
        if(!m)
            m = isFreeCellAll(x - 1, y + 1);
        if(!m)
            m = isFreeCellAll(x - 1, y - 1);

        return m;
    }

    public boolean isMyBot(Bot b1, int x, int y){
        x = getXW(x);
        y = getYW(y);
        Bot b2 = botsMap[x][y];
        if(b2 == null)
            return false;
        return isMyBot(b1, b2);
    }

    public boolean isMyBot(Bot b1, Bot b2){
        return b1.isMy(b2);
    }

    public boolean isFreeCellBot(int x, int y){
        x = getXW(x);
        y = getYW(y);
        if (botsMap[x][y] != null)
            return false;
        return true;
    }

    public boolean isFreeCellAll(int x, int y){
        x = getXW(x);
        y = getYW(y);
        if (botsMap[x][y] != null)
            return false;
        if (wallsMap[x][y] != null)
            return false;
        if (organicsMap[x][y] != null)
            return false;
        return true;
    }

    public void update(){
        tik++;
        sun.update();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                botsMap[x][y] = null;
                wallsMap[x][y] = null;
                organicsMap[x][y] = null;
            }
        }

        for(Organic organic : organics){
            int x = getXW(organic.x);
            int y = getYW(organic.y);
            organicsMap[x][y] = organic;
        }

        for(Wall wall : walls){
            int x = getXW(wall.x);
            int y = getYW(wall.y);
            wallsMap[x][y] = wall;
        }

        if(tik - tikUpdateEnd > 50){
            tikUpdateEnd = tik;
            maxTikLifeBot = 0;
            for(Bot bot : bots){
                maxTikLifeBot = Math.max(bot.getTikLife(), maxTikLifeBot);
            }
        }

        for(Bot bot : bots){
            int x = getXW(bot.getX());
            int y = getYW(bot.getY());
            botsMap[x][y] = bot;
        }

        for(int i = 0; i < organics.size(); i++){
            Organic org = organics.get(i);
            org.update();
        }

        for(inter = 0; inter < bots.size(); inter++){
            Bot bot = bots.get(inter);
            bot.update();
        }
    }

    //================ORGANIC==========

    public boolean isOrganic(int x, int y){
        x = getXW(x);
        y = getYW(y);
        return organicsMap[x][y] != null;
    }

    public Organic getOrganic(int x, int y){
        x = getXW(x);
        y = getYW(y);
        return organicsMap[x][y];
    }

    public boolean addOrganic(Organic org){
        int x = getXW(org.x);
        int y = getYW(org.y);
        if(organicsMap[x][y] != null)
            return false;
        org.world = this;
        organics.add(org);
        organicsMap[x][y] = org;
        return true;
    }

    public void removeOrganic(Organic org){
        organics.remove(org);
        int x = getXW(org.x);
        int y = getYW(org.y);
        if(organicsMap[x][y] != null)
            organicsMap[x][y] = null;
    }


    ////==============================



    public boolean isWall(int x, int y){
        x = getXW(x);
        y = getYW(y);
        return wallsMap[x][y] != null;
    }

    public boolean addWall(Wall wall){
        int x = getXW(wall.x);
        int y = getYW(wall.y);
        if(wallsMap[x][y] != null)
            return false;
        walls.add(wall);
        wallsMap[x][y] = wall;
        return true;
    }

    public void removeWall(Wall wall){
        walls.remove(wall);
        int x = getXW(wall.x);
        int y = getYW(wall.y);
        if(wallsMap[x][y] != null)
            wallsMap[x][y] = null;
    }

    public int getXW(int x){
        if(x >= width)
            x = x - width;
        if(x < 0)
            x = width + x;
        return x;
    }

    public int getYW(int y){
        if(y >= height)
            y = y - height;
        if(y < 0)
            y = height + y;
        return y;
    }
}
