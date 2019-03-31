package com.wdeath.game.evol01;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowListenerApp implements WindowListener {

    public Screen screen;

    public WindowListenerApp(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        screen.threadUpdateBots.run = false;
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
