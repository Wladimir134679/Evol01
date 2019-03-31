package com.wdeath.game.evol01.world;

import com.wdeath.game.evol01.Config;

public class Sun {

    public int x, y;
    public int lengthX, lengthY;
    private float del = 0;
    public World world;
    public int tik = 0;

    public void update(){

        x+= Config.MOVE_SUN_X;
        x = world.getXW(x);

        y+= Config.MOVE_SUN_Y;
        y = world.getYW(y);
    }

    public int getPhotosynthesis(int x, int y){
        return Math.round(10 * getNormalDelta(x, y));
    }

    public float getNormalDelta(int x, int y){
        del = getDelta(x, y);
        if(del > 1)
            del = 1;
        if(del < 0)
            del = 0;
        del = 1 - del;
        return del;
    }

    public float getDelta(int x, int y){
        float xD = getLengthX(x, world.getXW(this.x)) / (float)this.lengthX;
        float yD = getLengthY(y, world.getYW(this.y)) / (float)this.lengthY;
        if(Config.optimization)
            return xD + yD;
        else
            return (float)Math.sqrt(xD * xD + yD * yD);
    }

    private int getLengthX(int p1, int p2){
        int l1 = p1 + (world.width - p2);
        int l2 = Math.abs(p1 - p2);
        int l3 = p2 + (world.width - p1);
        return Math.min(l1, Math.min(l2, l3));
    }

    private int getLengthY(int p1, int p2){
        int l1 = p1 + (world.height - p2);
        int l2 = Math.abs(p1 - p2);
        int l3 = p2 + (world.height - p1);
        return Math.min(l1, Math.min(l2, l3));
    }
}
