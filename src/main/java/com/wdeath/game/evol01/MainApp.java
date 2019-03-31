package com.wdeath.game.evol01;

import com.wdeath.game.AppConfig;
import com.wdeath.game.ApplicationGame;
import com.wdeath.game.evol01.mind.MindEngine;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MainApp {

    public static Random rnd;
    public static ApplicationGame APP;
    public static Screen SCREEN;

    public static void main(String[] args) {
        System.out.println("Start Evol01");

        MindEngine.init();
        PulBot.init();

        rnd = new Random(2);
        AppConfig config = new AppConfig();
        config.width = Config.WINDOW_WIDTH;
        config.height = Config.WINDOW_HEIGHT;
        config.colorBackground = Color.BLACK;
        config.operationCloseFrame = JFrame.DISPOSE_ON_CLOSE;
        config.title = "Эволюция 0.1";
        SCREEN = new Screen();
        APP = new ApplicationGame(config, SCREEN);
        APP.getWindow().addWindowListener(new WindowListenerApp(SCREEN));
        APP.getCanvas().addKeyListener(new KeyListenerApp());
        APP.start();
    }
}
