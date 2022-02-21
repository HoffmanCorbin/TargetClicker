package com.target.clicker.Core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Settings{
    private int speed;
    private int size;
    private int remainingTime;
    private int quickness;
    private Game game;
    private String mode;

    private final Skin gameSkin;

    public Settings(Game game, Skin gameskin){
        this.game = game;
        this.gameSkin = gameskin;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getQuickness() {
        return quickness;
    }

    public Skin getGameSkin() {
        return gameSkin;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setQuickness(int quickness) {
        this.quickness = quickness;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}