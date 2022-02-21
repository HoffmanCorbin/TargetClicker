package com.target.clicker.UI.Menu;

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


    protected ProgramSettings(final Game game, final Skin gameSkin){
        prefs = new TimedPreferences(1,50,20,1);
        prefs = prefs.readFile();

        stage = new Stage(new ScreenViewport());
        Label title = new Label("Settings", gameSkin,"title-black");
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*9/10f);
        title.setWidth(Gdx.graphics.getWidth());
        stage.addActor(title);

        final Label resolution = new Label("Resolution: ", gameSkin, "default");
        resolution.setAlignment(Align.center);
        resolution.setY(title.getY()- (Gdx.graphics.getHeight()/10f));
        resolution.setWidth(Gdx.graphics.getWidth()/4f);
        resolution.setFontScale(1.2f);
        stage.addActor(resolution);

        final SelectBox resSel = new SelectBox( gameSkin,"default");
        resSel.setItems("1920x1080", "1280x720", "800x600", "600x480", Gdx.graphics.getWidth() + "x" +Gdx.graphics.getHeight());
        resSel.setSelectedIndex(resSel.getItems().size-1);
        resSel.setAlignment(Align.center);
        resSel.setY(title.getY()- (Gdx.graphics.getHeight()*(1/10f)));
        resSel.setX(resolution.getX()+resolution.getWidth()+50f);
        resSel.setWidth(Gdx.graphics.getWidth()/4f);
        stage.addActor(resSel);

        final CheckBox fullscreenSel = new CheckBox("Fullscreen", gameSkin, "default");
        fullscreenSel.setY(title.getY()- (Gdx.graphics.getHeight()*(3/10f)));
        fullscreenSel.setX(resolution.getX()+resolution.getWidth()+50f);
        fullscreenSel.setWidth(Gdx.graphics.getWidth()/4f);
        // Sets checked if the game is already fullscreen
        if(Gdx.graphics.isFullscreen())fullscreenSel.setChecked(true);
        stage.addActor(fullscreenSel);



        TextButton playButton = new TextButton("Back",gameSkin);
        playButton.setWidth(Gdx.graphics.getWidth()/2f);
        playButton.setPosition(Gdx.graphics.getWidth()/2f-playButton.getWidth()/2f,
                Gdx.graphics.getHeight()/4f-playButton.getHeight()*1/10f);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                String temp = (String)resSel.getSelected();
                String temps[] = temp.split("x", 2);


                Gdx.graphics.setWindowedMode(Integer.parseInt(temps[0]),Integer.parseInt(temps[1]));
                if(fullscreenSel.isChecked())Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
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
            System.out.println("ERROR " + e);
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