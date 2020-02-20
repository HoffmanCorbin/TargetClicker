package com.target.clicker;

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
import com.target.clicker.Menu.MainMenu;
import com.target.clicker.Menu.MenuLabel;


public class PostGameResults implements Screen {
    private Stage stage;


    public PostGameResults(final Game game, final Skin gameSkin, GameStats stats, String mode){



        stage = new Stage(new ScreenViewport());


        Label title = new Label("Mode: " + mode, gameSkin,"title-black");
        title.setY(Gdx.graphics.getHeight()-50);
        title.setX(Gdx.graphics.getWidth()-200);

        stage.addActor(title);


        final MenuLabel scoreLbl = new MenuLabel("Score: ", gameSkin, 10,1,1);
        stage.addActor(scoreLbl);

        final MenuLabel score = new MenuLabel(String.valueOf(stats.getScore()) , gameSkin,10,1,2);
        stage.addActor(score);


        final MenuLabel adjScoreLbl = new MenuLabel("Adjusted Score: ", gameSkin,10,2,1);
        stage.addActor(adjScoreLbl);

        final MenuLabel adjScore = new MenuLabel(String.valueOf(stats.getAdjustedScore()), gameSkin,10,2,2);
        stage.addActor(adjScore);





        TextButton playButton = new TextButton("Back",gameSkin);
        playButton.setWidth(Gdx.graphics.getWidth()/2f);
        playButton.setPosition(Gdx.graphics.getWidth()/2f-playButton.getWidth()/2,
                Gdx.graphics.getHeight()/4f-playButton.getHeight()*1/10f);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenu(game, gameSkin));

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
            System.out.println("ERROR: " + e);
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