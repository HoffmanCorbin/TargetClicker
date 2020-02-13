package com.target.clicker.Game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.target.clicker.Menu.MainMenu;

  /*TODO: Add a borderless circle to the middle. Spawn targets only if mouse is in that circle.
    Targets despawn quickly.*/

public class FlickAccuracy implements Screen {

    private Circle hitbox;
    private ShapeRenderer shape;
    private Target target;
    private boolean spawnTarget = false;
    private int counter = 0;
    private Game game;
    private Skin skin;
    private int targetCount = 0;
    private  int difficulty;
    private Scorebox scorebox;
    private double timeRemaining;
    private int score;


    public FlickAccuracy(Game game, Skin skin, int difficulty){

        timeRemaining = 30;


        Gdx.input.setInputProcessor(new GameInput());

        shape = new ShapeRenderer();
        hitbox = new Circle();
        scorebox = new Scorebox();
        this.game = game;
        this.skin = skin;
        this.difficulty = difficulty;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(163/255f, 137/255f, 137/255f, 1);

        shape.setColor(Color.WHITE);
        shape.begin(ShapeRenderer.ShapeType.Line);



        hitbox.setPosition(new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2));
        hitbox.setRadius(Gdx.graphics.getHeight()/14);
        shape.circle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getHeight()/14);
        shape.end();

        if(hitbox.contains(Gdx.input.getX(),Gdx.input.getY())){
            Gdx.gl.glClearColor(143/255f, 167/255f, 137/255f, 1);
            counter++;
        }
        if(hitbox.contains(Gdx.input.getX(),Gdx.input.getY())&&target==(null)&&counter > 100){
            Gdx.gl.glClearColor(143/255f, 167/255f, 137/255f, 1);
            spawnTarget = true;
            counter = 0;
        }

        if(spawnTarget){
            target = new Target(new Vector2(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()),25-difficulty,0);
            spawnTarget= false;
        }

        if(target!=null) {
            target.render();
            targetCount++;
        }

        if(targetCount > 200/difficulty){
            Gdx.gl.glClearColor(255/255f, 137/255f, 137/255f, 1);
            target = null;
            targetCount = 0;
        }
        timeRemaining = timeRemaining-Gdx.graphics.getDeltaTime();

        scorebox.render(score,timeRemaining);

    }

    public void shoot(){

        // Sound sound = Gdx.audio.newSound(Gdx.files.internal("data/mysound.mp3"));
        // if(test.hit(mouseLoc.x,mouseLoc.y))System.out.println("Boop");


        if(target!=null) {
            if (target.hit(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
                target = null;
                score++;
                targetCount = 0;
                return;
            } else {
                score = score - 3;
            }
        }

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
        target = null;
        game.setScreen(new MainMenu(game, skin));


    }


    private class GameInput implements InputProcessor {
        @Override
        public boolean keyDown(int keycode) {

            if(keycode== Input.Keys.ESCAPE){
                dispose();

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
