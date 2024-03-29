package com.target.clicker.UI.Menu;

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
import com.target.clicker.Game.Modes.FlickAccuracy;
import com.target.clicker.Game.Modes.Standard;
import com.target.clicker.Core.Settings;

import java.io.IOException;


public class MainMenu implements Screen {
    private final Stage stage;
    private final Skin gameSkin;
    private final Game enterMeaningfulNameHere;

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


        //play button
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

        //options button
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

        //exit button
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

        //stats button
        TextButton statsButton = new TextButton("Stats",gameSkin);
        statsButton.setWidth(60);
        statsButton.setPosition(Gdx.graphics.getWidth()-statsButton.getWidth(),0);
        statsButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                try {
                    enterMeaningfulNameHere.setScreen(new StatsPage(enterMeaningfulNameHere,gameSkin));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }

        });
        stage.addActor(statsButton);


    }

    private void newDialogueMenu(){
        Dialog dialog = new Dialog("", gameSkin, "c2") {
            public void result(Object obj) {
               if(obj.equals("Standard")){
                  // enterMeaningfulNameHere.setScreen(new GameSettings(enterMeaningfulNameHere,gameSkin));
                   newStandardMenu();
               }
               if(obj.equals("Flick")){
                   newFlickMenu();
               }
            }
        };
        dialog.text("Select Game Mode");

        dialog.button("Standard", "Standard");
        dialog.button("Flick", "Flick");
        dialog.button("Cancel", false);
        dialog.key(Input.Keys.ENTER, true);
        dialog.pack();
        dialog.setScale(1.1f,1.1f);
        dialog.setPosition(Gdx.graphics.getWidth()/2-dialog.getWidth()/2f, Gdx.graphics.getHeight()/2f);
        stage.addActor(dialog);
    }

    private void newStandardMenu(){
        Dialog dialog = new Dialog("Select a difficulty", gameSkin, "c2") {
            public void result(Object obj) {
                if(obj.equals("Easy")){
                    Settings easy = new Settings(enterMeaningfulNameHere,gameSkin);
                    easy.setQuickness(2);
                    easy.setSize(50);
                    easy.setSpeed(2);
                    easy.setRemainingTime(30);
                    easy.setMode("Easy");
                    enterMeaningfulNameHere.setScreen(new Standard(easy));
                }
                if(obj.equals("Medium")){
                    Settings med = new Settings(enterMeaningfulNameHere,gameSkin);
                    med.setQuickness(3);
                    med.setSize(50);
                    med.setSpeed(3);
                    med.setRemainingTime(30);
                    med.setMode("Medium");
                    enterMeaningfulNameHere.setScreen(new Standard(med));

                }
                if(obj.equals("Hard")){
                    Settings hard = new Settings(enterMeaningfulNameHere,gameSkin);
                    hard.setQuickness(4);
                    hard.setSize(40);
                    hard.setSpeed(4);
                    hard.setRemainingTime(30);
                    hard.setMode("Hard");
                    enterMeaningfulNameHere.setScreen(new Standard(hard));
                }
                if(obj.equals("Custom")){
                    enterMeaningfulNameHere.setScreen(new GameSettings(enterMeaningfulNameHere,gameSkin));
                }
            }
        };
        dialog.text("Select Game Mode");

        dialog.button("Easy", "Easy");
        dialog.button("Medium", "Medium");
        dialog.button("Hard", "Hard");
        dialog.button("Custom", "Custom");
        dialog.button("Cancel",false);
        dialog.key(Input.Keys.ENTER, true);
        dialog.pack();
        dialog.setScale(1.1f,1.1f);
        dialog.setPosition(Gdx.graphics.getWidth()/2-dialog.getWidth()/2, Gdx.graphics.getHeight()/2);
        stage.addActor(dialog);
    }

    private void newFlickMenu(){
        Dialog dialog = new Dialog("Select a difficulty", gameSkin, "c2") {
            public void result(Object obj) {
                if(obj.equals("Easy")){
                    enterMeaningfulNameHere.setScreen(new FlickAccuracy(enterMeaningfulNameHere,gameSkin,1));
                }
                if(obj.equals("Medium")){
                    enterMeaningfulNameHere.setScreen(new FlickAccuracy(enterMeaningfulNameHere,gameSkin,3));
                }
                if(obj.equals("Hard")){
                    enterMeaningfulNameHere.setScreen(new FlickAccuracy(enterMeaningfulNameHere,gameSkin,5));
                }
            }
        };
        dialog.text("Select Game Mode");

        dialog.button("Easy", "Easy");
        dialog.button("Medium", "Medium");
        dialog.button("Hard", "Hard");
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