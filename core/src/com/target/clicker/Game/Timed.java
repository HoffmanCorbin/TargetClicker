package com.target.clicker.Game;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.target.clicker.GameStats;
import com.target.clicker.Menu.MainMenu;
import com.target.clicker.PostGameResults;
import com.target.clicker.Settings;


import java.util.ArrayList;

public class Timed implements Screen {
    private Stage stage;
    private int score =0;
    private double time = 0;
    private double remainingTime;
    private int misses = 0;
    private boolean makeTarget=false;
    private Vector3 mouseLoc;
    private ArrayList<Target> targets;
    private Target test;

    private ArrayList<Mark> marks;

    private int decayRate;
    private int speed;
    private int quickness;
    private int size;
    private Game game;
    private Skin gameSkin;

    private Settings set;

    private Scorebox box ;



    public Timed(int speed, int size, int remainingTime, int quickness, Game game, Skin gameSkin){
        this.speed = speed;
        this.size = size;
        this.quickness = quickness;
        this.remainingTime = remainingTime;

        this.game = game;
        this.gameSkin = gameSkin;

        box = new Scorebox();
        set = new com.target.clicker.Settings(game,gameSkin);
        set.setMode("Advanced");

        decayRate = 3;
    }

    public Timed(Settings set){
        this.speed = set.getSpeed();
        this.size = set.getSize();
        this.quickness = set.getQuickness();
        this.remainingTime = set.getRemainingTime();

        this.game = set.getGame();
        this.gameSkin = set.getGameSkin();

        box = new Scorebox();

        this.set = set;

        decayRate = 3;



    }



    @Override
    public void show() {
        //Onscreen text

        stage = new Stage();
        Gdx.input.setInputProcessor(new GameInput());
        mouseLoc = new Vector3();

        targets = new ArrayList<Target>();
        marks = new ArrayList<Mark>();


        testThread();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(163/255f, 137/255f, 137/255f, 1);

        //calculates usable mouselocation
        mouseLoc.set(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY()), 0);

        box.render(score, remainingTime);

        if(makeTarget){
            targets.add(new Target(new Vector2(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()), size, quickness));
            makeTarget=false;
        }

        for(Mark m : marks){
            m.render();
        }

        for(Target t: targets){
                t.render();
        }

      time = time +  Gdx.graphics.getDeltaTime();
        remainingTime = remainingTime-Gdx.graphics.getDeltaTime();

        if(remainingTime <0){

            this.dispose();
            GameStats stats = new GameStats();
            stats.setHits(score+(misses*2));
            stats.setMisses(misses);
            stats.setScore(score);
            stats.setTime((int)time);
            stats.setSize(size);
            stats.setSpeed(quickness);
            stats.setSpawntime(speed);
            stats.setMode(set.getMode());
            game.setScreen(new PostGameResults(game,gameSkin, stats, "Timed"));

        }

    }

    private void testThread(){
        Thread thread = new Thread(){
            public void run(){
                makeTarget=true;
                try {
                    Thread.sleep(5000/(speed));
                    testThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    private void shoot(){


        for(int i= 0; i< targets.size();i++){
            if(targets.get(i).hit(mouseLoc.x,mouseLoc.y)){
                targets.remove(i);
                score++;
                return;
            }

        }
        marks.add(new Mark(mouseLoc.x, mouseLoc.y,size));
        score = score - 2;
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
                game.setScreen(new MainMenu(game,gameSkin));
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
