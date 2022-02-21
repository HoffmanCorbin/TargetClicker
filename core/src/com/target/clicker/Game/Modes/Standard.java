package com.target.clicker.Game.Modes;


import com.target.clicker.Core.Settings;

public class Standard extends ClassicGameMode {

    /**
     * @param set      - The settings object containing the settings necessary to run the game
     * @param gameMode
     */
    public Standard(Settings set, String gameMode) {
        super(set, gameMode);
    }

    public Standard(Settings set) {
        super(set, "Standard");
    }

    @Override
    public void show() {
        super.show();
        spawnCalcThread();
    }

    // creates a thread to calculate time asynchronously rather than waiting in the main update loop
    private void spawnCalcThread(){
        Thread thread = new Thread(){
            public void run(){
                setMakeTarget(true);
                try {
                    Thread.sleep(5000/(getSettings().getSpeed()));
                    spawnCalcThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    @Override
    public int getScore(){
        return (getHits()*10)-(getHits()*5);
    }
}