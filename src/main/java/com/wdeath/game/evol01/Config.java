package com.wdeath.game.evol01;

public class Config {

    public static enum TypeShowBot{
        NORM,
        HEALTH,
        FOOD,
        BOTS
    }

    public static int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600;
    public static int CELL_WIDTH = 2, CELL_HEIGHT = 2;

    public static boolean SYN_DRAW = false;
    public static boolean SLEEP_THREAD_RENDER = false;
    public static long TIME_SLEEP_THREAD_RENDER = 1000/30;
    public static long SEED_RND_START_SPAWN = 6;
    public static boolean DRAW_DIR_BOT = false;
    public static boolean DRAW_WORLD = true;
    public static boolean DRAW_INFO_WORLD = true;


    public static int MOVE_SUN_X = 0;
    public static int MOVE_SUN_Y = 0;

    public static boolean optimization = false;

    public static TypeShowBot TYPE_SHOW_BOT = TypeShowBot.NORM;
}
