package com.target.clicker.UI.Menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.target.clicker.Core.GameStats;
import com.target.clicker.UI.Menu.MainMenu;
import com.target.clicker.UI.Menu.MenuLabel;
import org.json.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class PostGameResults implements Screen {
    private Stage stage;
    private GameStats stats;

    public PostGameResults(final Game game, final Skin gameSkin, GameStats stats, String mode) {
        stage = new Stage(new ScreenViewport());

        Label title = new Label("Mode: " + mode, gameSkin,"title-black");
        title.setY(Gdx.graphics.getHeight()-50);
        title.setX(Gdx.graphics.getWidth()-200);

        stage.addActor(title);

        final MenuLabel scoreLbl = new MenuLabel("Score: ", gameSkin, 10,1,1);
        stage.addActor(scoreLbl);

        final MenuLabel score = new MenuLabel(String.valueOf(stats.getScore()) , gameSkin,10,1,2);
        stage.addActor(score);

        final MenuLabel adjScoreLbl = new MenuLabel("Hit %: ", gameSkin,10,2,1);
        stage.addActor(adjScoreLbl);

        final MenuLabel adjScore = new MenuLabel(String.valueOf(1.0*stats.getHits()/(stats.getHits()+stats.getMisses())*100), gameSkin,10,2,2);
        stage.addActor(adjScore);

        final MenuLabel targetsHitLbl = new MenuLabel("Targets Hit: ", gameSkin, 10, 3,1);
        stage.addActor(targetsHitLbl);

        final MenuLabel targetsHit = new MenuLabel(String.valueOf(stats.getHits()),gameSkin,10,3,2);
        stage.addActor(targetsHit);

        final MenuLabel targetsMissedlbl = new MenuLabel("Missed Clicks: ", gameSkin,10,4,1);
        stage.addActor(targetsMissedlbl);

        final MenuLabel targetsMissed = new MenuLabel(String.valueOf(stats.getMisses()),gameSkin,10,4,2);
        stage.addActor(targetsMissed);

        final MenuLabel timelbl = new MenuLabel("Time: ", gameSkin, 10,5,1);
        stage.addActor(timelbl);

        final MenuLabel time = new MenuLabel(String.valueOf(stats.getTime()),gameSkin,10,5,2);
        stage.addActor(time);

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
        this.stats = stats;

        try {
            fileWrite();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fileWrite() throws IOException {
        JSONArray array;
        if(Files.exists(Paths.get("stats.json"))) {
            String text = new String(Files.readAllBytes(Paths.get("stats.json")));
            if(text.equals(""))array = new JSONArray();
            else array = new JSONArray(text);

        }
        else{
            array = new JSONArray();
        }

        Writer writer = new FileWriter("stats.json");
        JSONWriter jsonWrite = new JSONWriter(writer);
        JSONObject jsonStats = new JSONObject();
        jsonStats.put("Mode", stats.getMode());
        jsonStats.put("Hits", stats.getHits());
        jsonStats.put("Misses",stats.getMisses());
        jsonStats.put("Hit Percent", 1.0 * stats.getHits()/(stats.getHits() + stats.getMisses()));
        jsonStats.put("Score", stats.getScore());

        array.put(jsonStats);


        writer.write(array.toString());
        writer.close();

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