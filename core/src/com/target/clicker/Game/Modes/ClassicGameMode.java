package com.target.clicker.Game.Modes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.target.clicker.Game.Objects.Mark;
import com.target.clicker.Game.Objects.Scorebox;
import com.target.clicker.Game.Objects.Target;
import com.target.clicker.Core.GameStats;
import com.target.clicker.UI.Menu.MainMenu;
import com.target.clicker.UI.Menu.PostGameResults;
import com.target.clicker.Core.Settings;


import java.util.ArrayList;

public class ClassicGameMode implements Screen {
    private Stage stage;
    private int hits = 0;
    private double time = 0;
    private double remainingTime;
    private int misses = 0;
    private boolean makeTarget=false;
    private Vector3 mouseLoc;
    private ArrayList<Target> targets;

    private ArrayList<Mark> marks;

    private final Settings settings;
    private final Scorebox box ;
    private final String gameMode;

    /**
     *
     * @param set - The settings object containing the settings necessary to run the game
     */
    public ClassicGameMode(Settings set, String gameMode){
        this.settings = set;
        this.gameMode = gameMode;
        box = new Scorebox();
        this.remainingTime = settings.getRemainingTime();
    }

    @Override
    public void show() {
        //Onscreen text
        stage = new Stage();
        Gdx.input.setInputProcessor(new GameInput());
        mouseLoc = new Vector3();
        targets = new ArrayList<Target>();
        marks = new ArrayList<Mark>();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(163/255f, 137/255f, 137/255f, 1);

        // calculates usable mouselocation
        mouseLoc.set(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY()), 0);
        box.render(getScore(), remainingTime);

        // creates a new target based on the size of the screen and settings
        if(makeTarget){
            targets.add(new Target(new Vector2(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()),
                    settings.getSize(), settings.getQuickness()));
            makeTarget=false;
        }

        // renders marks for missed shots
        for(Mark m : marks){
            m.render();
        }

        // renders targets
        for(Target t: targets){
            t.render();
        }

        // remaining time
        time = time +  Gdx.graphics.getDeltaTime();
        remainingTime = remainingTime - Gdx.graphics.getDeltaTime();

        // closes the game and creates a postgame screen at 0 seconds
        if(remainingTime < 0){
            this.dispose();
            GameStats stats = new GameStats();
            stats.setHits(hits);
            stats.setMisses(misses);
            stats.setScore(getScore());
            stats.setTime((int)time);
            stats.setSize(settings.getSize());
            stats.setSpeed(settings.getQuickness());
            stats.setSpawntime(settings.getSpeed());
            stats.setMode(settings.getMode());
            settings.getGame().setScreen(new PostGameResults(settings.getGame(), settings.getGameSkin(), stats, gameMode));
        }
    }

    private void shoot(){
        for(int i= 0; i< targets.size();i++){
            if(targets.get(i).hit(mouseLoc.x,mouseLoc.y)){
                targets.remove(i);
                hits++;
                return;
            }
        }
        marks.add(new Mark(mouseLoc.x, mouseLoc.y, settings.getSize()));
        misses++;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public Settings getSettings(){
        return settings;
    }

    public boolean isMakeTarget() {
        return makeTarget;
    }

    public void setMakeTarget(boolean makeTarget) {
        this.makeTarget = makeTarget;
    }

    // The formula to get the score of a game
    public int getScore(){
        return 0;
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    @Override
    public void dispose() {
        for(Target t: targets){
            t.dispose();
        }
        marks = null;
        targets = null;
        System.gc();
    }
    private class GameInput implements InputProcessor {
        @Override
        public boolean keyDown(int keycode) {
            if(keycode== Input.Keys.ESCAPE){
                dispose();
                settings.getGame().setScreen(new MainMenu(settings.getGame(), settings.getGameSkin()));
            }
            return false;
        }
        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            shoot();
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
