package com.target.clicker.Menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class ProgramSettings implements Screen {
    private Stage stage;
    private TimedPreferences prefs;


    //Todo: target decay rate
    //Todo: pull default values for textfields from storage

    public ProgramSettings(final Game game, final Skin gameSkin){
        prefs = new TimedPreferences(1,50,20,1);
        prefs = prefs.readFile();





        stage = new Stage(new ScreenViewport());
        Label title = new Label("Settings", gameSkin,"title-black");
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*9/10);
        title.setWidth(Gdx.graphics.getWidth());
        stage.addActor(title);



        final Label resolution = new Label("Resolution: ", gameSkin, "default");
        resolution.setAlignment(Align.center);
        resolution.setY(title.getY()- (Gdx.graphics.getHeight()*1/10));
        resolution.setWidth(Gdx.graphics.getWidth()/4);
        resolution.setFontScale(1.2f);
        stage.addActor(resolution);

        final SelectBox resSel = new SelectBox( gameSkin,"default");
        resSel.setItems("1800x1000", "1280x720", "800x600", "600x480", Gdx.graphics.getWidth() + "x" +Gdx.graphics.getHeight());
        resSel.setSelectedIndex(resSel.getItems().size-1);
        resSel.setAlignment(Align.center);
        resSel.setY(title.getY()- (Gdx.graphics.getHeight()*1/10));
        resSel.setX(resolution.getX()+resolution.getWidth()+50);
        resSel.setWidth(Gdx.graphics.getWidth()/4);
        stage.addActor(resSel);



        TextButton playButton = new TextButton("Back",gameSkin);
        playButton.setWidth(Gdx.graphics.getWidth()/2);
        playButton.setPosition(Gdx.graphics.getWidth()/2-playButton.getWidth()/2,
                Gdx.graphics.getHeight()*1/4-playButton.getHeight()*1/10);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                String temp = (String)resSel.getSelected();
                String temps[] = temp.split("x", 2);

                Gdx.graphics.setWindowedMode(Integer.parseInt(temps[0]),Integer.parseInt(temps[1]));
               game.setScreen(new MainMenu(game,gameSkin));
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