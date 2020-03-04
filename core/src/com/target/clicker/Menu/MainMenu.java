package com.target.clicker.Menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.target.clicker.Game.FlickAccuracy;
import com.target.clicker.Game.Timed;
import com.target.clicker.Settings;


public class MainMenu implements Screen {
    private Stage stage;
    private Skin gameSkin;
    private Game enterMeaningfulNameHere;

    public MainMenu(final Game enterMeaningfulNameHere, final Skin gameSkin){

        System.gc();

        this.gameSkin = gameSkin;
        this.enterMeaningfulNameHere = enterMeaningfulNameHere;

        stage = new Stage(new ScreenViewport());
        Label title = new Label("Target Clicker", gameSkin,"title-black");
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*2/3f);
        title.setWidth(Gdx.graphics.getWidth());
        stage.addActor(title);

        TextButton playButton = new TextButton("Play!",gameSkin);
        playButton.setWidth(Gdx.graphics.getWidth()/2f);
        playButton.setPosition(Gdx.graphics.getWidth()/2f-playButton.getWidth()/2f,Gdx.graphics.getHeight()/2f-playButton.getHeight()/2f);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                newDialogueMenu();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(playButton);

        TextButton optionsButton = new TextButton("Options",gameSkin);
        optionsButton.setWidth(Gdx.graphics.getWidth()/2f);
        optionsButton.setPosition(Gdx.graphics.getWidth()/2f-optionsButton.getWidth()/2f,Gdx.graphics.getHeight()/3f-optionsButton.getHeight()/2f);
        optionsButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                 enterMeaningfulNameHere.setScreen(new ProgramSettings(enterMeaningfulNameHere,gameSkin));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(optionsButton);

        TextButton exitButton = new TextButton("Exit",gameSkin);
        exitButton.setWidth(Gdx.graphics.getWidth()/2f);
        exitButton.setPosition(Gdx.graphics.getWidth()/2f-optionsButton.getWidth()/2f,Gdx.graphics.getHeight()/6f-optionsButton.getHeight()/2f);
        exitButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(exitButton);


    }

    private void newDialogueMenu(){
        Dialog dialog = new Dialog("", gameSkin, "c2") {
            public void result(Object obj) {
               if(obj.equals("Timed")){
                  // enterMeaningfulNameHere.setScreen(new GameSettings(enterMeaningfulNameHere,gameSkin));
                   newTimedMenu();
               }
               if(obj.equals("Flick")){
                   enterMeaningfulNameHere.setScreen(new FlickAccuracy(enterMeaningfulNameHere,gameSkin,3));
               }
            }
        };
        dialog.text("Select Game Mode");

        dialog.button("Timed", "Timed");
        dialog.button("Flick", "Flick");
        dialog.button("Cancel", false);
        dialog.key(Input.Keys.ENTER, true);
        dialog.pack();
        dialog.setScale(1.1f,1.1f);
        dialog.setPosition(Gdx.graphics.getWidth()/2-dialog.getWidth()/2f, Gdx.graphics.getHeight()/2f);
        stage.addActor(dialog);
    }

    private void newTimedMenu(){
        Dialog dialog = new Dialog("", gameSkin, "c2") {
            public void result(Object obj) {
                if(obj.equals("Easy")){
                    Settings easy = new Settings(enterMeaningfulNameHere,gameSkin);
                    easy.setQuickness(2);
                    easy.setSize(50);
                    easy.setSpeed(2);
                    easy.setRemainingTime(30);
                    enterMeaningfulNameHere.setScreen(new Timed(easy));

                }
                if(obj.equals("Medium")){
                    Settings med = new Settings(enterMeaningfulNameHere,gameSkin);
                    med.setQuickness(3);
                    med.setSize(50);
                    med.setSpeed(3);
                    med.setRemainingTime(30);
                    enterMeaningfulNameHere.setScreen(new Timed(med));

                }
                if(obj.equals("Hard")){
                    Settings hard = new Settings(enterMeaningfulNameHere,gameSkin);
                    hard.setQuickness(4);
                    hard.setSize(40);
                    hard.setSpeed(4);
                    hard.setRemainingTime(30);
                    enterMeaningfulNameHere.setScreen(new Timed(hard));
                }
                if(obj.equals("Advanced")){
                    enterMeaningfulNameHere.setScreen(new GameSettings(enterMeaningfulNameHere,gameSkin));
                }
            }
        };
        dialog.text("Select Game Mode");

        dialog.button("Easy", "Easy");
        dialog.button("Medium", "Medium");
        dialog.button("Hard", "Hard");
        dialog.button("Advanced", "Advanced");
        dialog.button("Cancel",false);
        dialog.key(Input.Keys.ENTER, true);
        dialog.pack();
        dialog.setScale(1.1f,1.1f);
        dialog.setPosition(Gdx.graphics.getWidth()/2-dialog.getWidth()/2, Gdx.graphics.getHeight()/2);
        stage.addActor(dialog);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(222/255f, 255/255f, 230/255f,1);
        stage.act();
        stage.draw();

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

    }
}