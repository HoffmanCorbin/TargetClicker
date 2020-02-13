package com.target.clicker.Menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.target.clicker.Game.Timed;

import java.awt.*;


public class GameSettings implements Screen {
    private Stage stage;
    private TimedPreferences prefs;


    //Todo: target decay rate
    //Todo: pull default values for textfields from storage

    public GameSettings(final Game enterMeaningfulNameHere, final Skin gameSkin){
        prefs = new TimedPreferences(1,50,20,1);
        prefs = prefs.readFile();





        stage = new Stage(new ScreenViewport());
        Label title = new Label("Timed", gameSkin,"title-black");
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*9/10);
        title.setWidth(Gdx.graphics.getWidth());
        stage.addActor(title);




        final MenuLabel targetSize = new MenuLabel("Target Size: ", gameSkin, 10,3,1);
        stage.addActor(targetSize);

        final MenuTextBox targetSizeSel= new MenuTextBox("40",gameSkin,10,3,2);
        stage.addActor(targetSizeSel);

        final MenuLabel spawnSpeed = new MenuLabel("Spawn Speed: ", gameSkin, 10,4,1);
        stage.addActor(spawnSpeed);

        final MenuTextBox spawnSpeedSel= new MenuTextBox("4",gameSkin,10,4,2);
        stage.addActor(spawnSpeedSel);


        final MenuLabel time = new MenuLabel("Time ", gameSkin, 10,5,1);
        stage.addActor(time);

        final MenuTextBox timeSel= new MenuTextBox("30",gameSkin,10,5,2);
        stage.addActor(timeSel);

        final MenuLabel quickness = new MenuLabel("Target speed ", gameSkin, 10,6,1);
        stage.addActor(quickness);

        final MenuTextBox quicknessSel= new MenuTextBox("1",gameSkin,10,6,2);
        stage.addActor(quicknessSel);

        spawnSpeedSel.setText(String.valueOf(prefs.spawnSpeed));
        targetSizeSel.setText(String.valueOf(prefs.targetSize));
        timeSel.setText(String.valueOf(prefs.time));
        quicknessSel.setText(String.valueOf(prefs.quickness));

        TextButton playButton = new TextButton("Play!",gameSkin);
        playButton.setWidth(Gdx.graphics.getWidth()/2);
        playButton.setPosition(Gdx.graphics.getWidth()/2-playButton.getWidth()/2,
                Gdx.graphics.getHeight()*1/4-playButton.getHeight()*1/10);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if((isValid(spawnSpeedSel.getText()))&& (isValid(targetSizeSel.getText()))&&(isValid(timeSel.getText()))
                        &&(isValid(quicknessSel.getText()))) {


                    prefs = new TimedPreferences(Integer.parseInt(spawnSpeedSel.getText()),
                            Integer.parseInt(targetSizeSel.getText()), Integer.parseInt(timeSel.getText()),
                            Integer.parseInt(quicknessSel.getText()));
                    prefs.storeEvents();

                    enterMeaningfulNameHere.setScreen(new Timed(Integer.parseInt(spawnSpeedSel.getText()),
                            Integer.parseInt(targetSizeSel.getText()), Integer.parseInt(timeSel.getText()),
                            Integer.parseInt(quicknessSel.getText()), enterMeaningfulNameHere, gameSkin));
                }
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(playButton);




    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    private boolean isValid(String text){
        try{
            Double.parseDouble(text);
        }
        catch (Exception e){
            System.out.println("ERROR,ERROR");
            return false;
        }
        return true;
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