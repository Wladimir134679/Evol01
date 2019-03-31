package com.wdeath.game.evol01.editor.edit;

import com.wdeath.game.evol01.Config;
import com.wdeath.game.evol01.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class EditorStageControll implements Initializable {

    @FXML
    public TextField sizeWindow, sizeCell, seedRandom, sleepThreadUpdate;
    @FXML
    public CheckBox synDraw, isSleepThreadUpdate, drawDirBot, drawWorld, drawInfoWorld, opti;
    @FXML
    public RadioButton tdcNorm, tdcHealth, tdcFood, tdcBot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sizeWindow.setText("800X600");
        sizeCell.setText("8X8");
        seedRandom.setText("1");
        sleepThreadUpdate.setText("100");
        isSleepThreadUpdate.setSelected(false);
        synDraw.setSelected(false);
        drawDirBot.setSelected(false);
        drawWorld.setSelected(true);
        drawInfoWorld.setSelected(false);
        opti.setSelected(false);
        tdcBot.setSelected(true);
    }

    @FXML
    public void start(MouseEvent event){
        if(!updateConfig())
            return;
        MainApp.main(null);
    }

    @FXML
    public void pauseAndRun(MouseEvent event){
        MainApp.SCREEN.threadUpdateBots.setStop(!MainApp.SCREEN.threadUpdateBots.isStop());
    }

    @FXML
    public void updateConfig(MouseEvent event){
        updateConfig();
    }

    public boolean updateConfig(){
        String sizeW = sizeWindow.getText();
        try{
            String[] s = sizeW.split("X");
            int w = Integer.parseInt(s[0]);
            int h = Integer.parseInt(s[1]);
            Config.WINDOW_WIDTH = w;
            Config.WINDOW_HEIGHT = h;
        }catch (Exception e){
            return false;
        }

        String sizeC = sizeCell.getText();
        try{
            String[] s = sizeC.split("X");
            int w = Integer.parseInt(s[0]);
            int h = Integer.parseInt(s[1]);
            Config.CELL_WIDTH = w;
            Config.CELL_HEIGHT = h;
        }catch (Exception e){
            return false;
        }

        if(tdcBot.isSelected())
            Config.TYPE_SHOW_BOT = Config.TypeShowBot.BOTS;
        if(tdcFood.isSelected())
            Config.TYPE_SHOW_BOT = Config.TypeShowBot.FOOD;
        if(tdcHealth.isSelected())
            Config.TYPE_SHOW_BOT = Config.TypeShowBot.HEALTH;
        if(tdcNorm.isSelected())
            Config.TYPE_SHOW_BOT = Config.TypeShowBot.NORM;

        Config.optimization = opti.isSelected();
        Config.SYN_DRAW = synDraw.isSelected();
        Config.SLEEP_THREAD_RENDER = isSleepThreadUpdate.isSelected();
        Config.SEED_RND_START_SPAWN = Integer.parseInt(seedRandom.getText());
        Config.TIME_SLEEP_THREAD_RENDER = Integer.parseInt(sleepThreadUpdate.getText());
        Config.DRAW_DIR_BOT = drawDirBot.isSelected();
        Config.DRAW_WORLD = drawWorld.isSelected();
        Config.DRAW_INFO_WORLD = drawInfoWorld.isSelected();

        return true;
    }
}
