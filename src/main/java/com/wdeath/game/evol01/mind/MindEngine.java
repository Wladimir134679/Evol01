package com.wdeath.game.evol01.mind;

import com.wdeath.game.evol01.MainApp;
import com.wdeath.game.evol01.world.Bot;
import com.wdeath.game.utills.ClassFinder;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MindEngine {

    public static final int MAX = 64, MAX_MIND = 128;
    public static HashMap<Integer, CommandMind> minds;

    public static void init(){
        minds = new HashMap<>();
        List<Class<?>> list = ClassFinder.find("com.wdeath.game.evol01.mind.command");
        for(Class<?> clazz : list){
            add(clazz);
        }
    }

    private static void add(Class<?> clazz){
        IMind iMind = clazz.getAnnotation(IMind.class);
        if(iMind == null) {
            System.out.println("ERROR! " + clazz.getName() + " not IMind");
            return;
        }
        if(minds.get(iMind.id()) != null){
            System.out.println("ERROR! " + clazz.getName() + ".id AND " + minds.get(iMind.id()).getClass().getName() + ".id == " + iMind.id());
            return;
        }
        Class<?>[] inters = clazz.getInterfaces();
        for(Class<?> inter : inters){
            if(inter.equals(CommandMind.class)){
                System.out.println("Add command " + clazz.getName() + ".id = " + iMind.id());
                CommandMind commandMind = newInstance(clazz);
                minds.put(iMind.id(), commandMind);
                return;
            }
        }
        System.out.println("ERROR! " + clazz.getName() + " not Command");
    }

    public static int getIdCommand(Class<?> clazz){
        IMind iMind = clazz.getAnnotation(IMind.class);
        if(iMind == null) {
            System.out.println("ERROR! " + clazz.getName() + " not IMind");
            return -1;
        }
        return iMind.id();
    }

    private static CommandMind newInstance(Class clazz){
        try {
            return (CommandMind) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int[] commands;
    private int utk;
    private boolean isStop;
    private Bot bot;

    public MindEngine(){
        commands = new int[MAX_MIND];
    }

    public void process(){
        isStop = false;
        int par = 0;
        for(int i = 0; i < 15 && !isStop; i++){
            utk = getUTKW(utk);

            par = getCommandUTK();
            CommandMind command = minds.get(par);
            if(command == null){
                utk += par;
                continue;
            }else{
                command.process(this);
            }

            utk++;
        }
        utk++;
    }

    public MindEngine cloneMind(MindEngine mind, float pr){
        for(int i = 0; i < commands.length; i++){
            mind.commands[i] = commands[i];
        }
        Random rnd = MainApp.rnd;
        float f = rnd.nextFloat();
        if(f <= pr){
            int mi = rnd.nextInt(MAX_MIND);
            int p = rnd.nextInt(MAX);
            mind.commands[mi] = p;
        }
        return mind;

    }

    public boolean isOverlap(MindEngine mind){
        int s = 0;
        for(int i = 0; i < commands.length; i++){
            if(commands[i] != mind.commands[i])
                s++;
            if(s >= 3)
                return false;
        }
        return true;
    }

    public void stop(){
        isStop = true;
    }

    public void next(){
        utk++;
    }

    public void step(int i){
        utk += i;
        utk = getUTKW(utk);
    }

    public void setCommand(int i, int m){
        i = getUTKW(i);
        commands[i] = m;
    }

    public int getCommandUTK(){
        return getCommand(utk);
    }

    public int getCommand(int u){
        int i = getUTKW(u);
        return commands[i];
    }

    public int getUTK(){
        return utk;
    }

    public int getUTKW(int u){
        if(u >= MAX_MIND)
            u = u - MAX_MIND;
        if(u < 0)
            u = MAX_MIND + u;
        return u;
    }

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }
}
