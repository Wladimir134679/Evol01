package com.wdeath.game.evol01;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerApp implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_1)
            Config.TYPE_SHOW_BOT = Config.TypeShowBot.NORM;
        if(e.getKeyCode() == KeyEvent.VK_2)
            Config.TYPE_SHOW_BOT = Config.TypeShowBot.HEALTH;
        if(e.getKeyCode() == KeyEvent.VK_3)
            Config.TYPE_SHOW_BOT = Config.TypeShowBot.FOOD;
        if(e.getKeyCode() == KeyEvent.VK_4)
            Config.TYPE_SHOW_BOT = Config.TypeShowBot.BOTS;

        if(e.getKeyCode() == KeyEvent.VK_SHIFT)
            Config.DRAW_INFO_WORLD = !Config.DRAW_INFO_WORLD;
        if(e.getKeyCode() == KeyEvent.VK_D)
            Config.DRAW_DIR_BOT = !Config.DRAW_DIR_BOT;
        if(e.getKeyCode() == KeyEvent.VK_W)
            Config.SYN_DRAW = !Config.SYN_DRAW;
        if(e.getKeyCode() == KeyEvent.VK_Q)
            MainApp.SCREEN.threadUpdateBots.setStop(!MainApp.SCREEN.threadUpdateBots.isStop());
        if(e.getKeyCode() == KeyEvent.VK_P)
            Config.SLEEP_THREAD_RENDER = !Config.SLEEP_THREAD_RENDER;

        if(e.getKeyCode() == KeyEvent.VK_E)
            Config.DRAW_WORLD = !Config.DRAW_WORLD;
    }
}
