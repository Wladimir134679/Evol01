package com.wdeath.game.evol01.world;

import com.wdeath.game.evol01.MainApp;
import com.wdeath.game.evol01.mind.MindEngine;
import com.wdeath.game.evol01.mind.command.EatBot;
import com.wdeath.game.evol01.mind.command.Phosynthesis;

import java.awt.*;
import java.util.HashMap;

public class Bot {

    public static int MAX_HEALTH = 500, MAX_FOOD = 500;

    public static final int UP = 0, DOWN = 4, LEFT = 6, RIGHT = 2, UR = 1, UL = 7, DR = 3, DL = 5;

    public static HashMap<Integer, int[]> dirs;

    static{
        System.out.println("Init DIR Bot");
        dirs = new HashMap<>();
        dirs.put(UP, new int[]{0, 1});
        dirs.put(DOWN, new int[]{0, -1});
        dirs.put(LEFT, new int[]{-1, 0});
        dirs.put(RIGHT, new int[]{1 , 0});
        dirs.put(UR, new int[]{1, 1});
        dirs.put(UL, new int[]{-1, 1});
        dirs.put(DR, new int[]{1, -1});
        dirs.put(DL, new int[]{-1, -1});
    }

    private World world;
    private int x, y;
    private int health;
    private int food;
    private MindEngine mind;
    private int dir = 0;
    public long tikLife = 0;

    public int rC = 0, gC = 0, bC = 0;

    public Bot(){
        mind = new MindEngine();
        mind.setBot(this);
        health = 10;
        food = 0;
        rC = 255/3;
        gC = 255/3;
        bC = 255/3;
    }

    public void clear(){
        mind.setBot(this);
        health = 10;
        food = 0;
        rC = 255/3;
        gC = 255/3;
        bC = 255/3;
    }

    public void update(){
        mind.process();
        tikLife++;
        health--;
        death();
        mut();
    }

    public void toRed(){
        if(rC + 2 > 255 || gC - 1 < 0)
            return;
        rC+=2;
        gC--;
        bC--;
        updateColor();
    }

    public void toGreen(){
        if(gC + 2 > 255 || rC - 1 < 0)
            return;
        rC--;
        gC+=2;
        bC--;
        updateColor();
    }

    public void toBlue(){
        if(bC + 2 > 255 || gC - 1 < 0)
            return;
        rC--;
        gC--;
        bC+=2;
        updateColor();
    }

    public void updateColor(){
        if(rC > 255)
            rC = 255;
        if(rC < 0)
            rC = 0;

        if(gC > 255)
            gC = 255;
        if(gC < 0)
            gC = 0;

        if(bC > 255)
            bC = 255;
        if(bC < 0)
            bC = 0;
    }

    private void mut(){
        if(health < MAX_HEALTH && food < MAX_FOOD)
            return;
        health = (int)Math.floor(health / 2f);
        food = (int)Math.floor(food / 2f);
        mind.cloneMind(mind, 0.25f);
    }

    private void death(){
        if(health > 0)
            return;
        Organic organic = new Organic();
        organic.x = x;
        organic.y = y;
        organic.food = 5;
        organic.tikHealth = MainApp.rnd.nextInt(400) + 50;
        world.addOrganic(organic);

        world.removeBot(this);
    }

    public long getTikLife(){
        return tikLife;
    }

    public boolean actionHealth(int i){
        if(health - i >= 0) {
            health -= i;
            return true;
        }
        return false;
    }

    public void setPosition(int x, int y){
        this.x = world.getXW(this.x);
        this.y = world.getYW(this.y);
        if(world.getBot(this.x, this.y).equals(this))
            world.botsMap[this.x][this.y] = null;
        this.x = world.getXW(x);
        this.y = world.getYW(y);
        world.botsMap[this.x][this.y] = this;
    }

    public boolean isMy(Bot bot){
        int s = 5;
        if(bot.rC - s < rC || bot.rC + s > rC)
            return false;
        if(bot.gC - s < gC || bot.gC + s > gC)
            return false;
        if(bot.bC - s < bC || bot.bC + s > bC)
            return false;
        return mind.isOverlap(bot.getMind());
    }

    public int[] getDirLocal(int dirL){
        int dirR = dirL + dir;
        if(dirR >= 8)
            dirR = dirR - 8;
        return dirs.get(dirR);
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        if(dir >= 8)
            dir = dir - 8;
        if(dir < 0)
            dir = 8 + dir;
        this.dir = dir;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public MindEngine getMind() {
        return mind;
    }

    public void setMind(MindEngine mind) {
        this.mind = mind;
    }
}
