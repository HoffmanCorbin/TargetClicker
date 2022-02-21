package com.target.clicker.UI.Menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class StatsPage implements Screen {
    private Stage stage;
    private Game enterMeaningfulNameHere;
    private Skin gameSkin;



    public StatsPage(final Game enterMeaningfulNameHere, final Skin gameSkin) throws IOException {
        this.gameSkin=gameSkin;
        this.enterMeaningfulNameHere=enterMeaningfulNameHere;
        stage = new Stage(new ScreenViewport());
        Label[] labels = read();
        Table outerTable = new Table();
        Table innerTable = new Table();

        ScrollPane scrollPane = new ScrollPane(innerTable,gameSkin,"default");
        outerTable.add(scrollPane);

        for(Label l:labels){
            outerTable.addActor(l);
        }

        stage.addActor(outerTable);

        final TextButton resetButton = new TextButton("Reset", gameSkin,"default");
        resetButton.setName("Reset");
        resetButton.setHeight(Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()-resetButton.getHeight()));
        resetButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                File file = new File("stats.json");
                file.delete();
                enterMeaningfulNameHere.setScreen(new MainMenu(enterMeaningfulNameHere,gameSkin));
                return true;
            }
        });
        stage.addActor(resetButton);

        final TextButton backButton = new TextButton("Back", gameSkin,"default");

        backButton.setHeight(Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()-backButton.getHeight()));
        backButton.setX(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth() -backButton.getWidth()));
        backButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                enterMeaningfulNameHere.setScreen(new MainMenu(enterMeaningfulNameHere,gameSkin));
                return true;
            }
        });
        stage.addActor(backButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    private Label[] read() throws IOException {
        File file = new File("stats.json");
        if(!file.exists()){
            file.createNewFile();
        }

        String text = new String(Files.readAllBytes(Paths.get("stats.json")));
        if(text.equals("")){
            Label statLabel = new Label( "You have no games played!",gameSkin,"default");
            statLabel.setY(Gdx.graphics.getHeight()-30);
            Label[] labels = new Label[1];
            labels[0]=statLabel;
            return labels;
        }

        JSONArray array = new JSONArray(text);
        Label[] labels = new Label[array.length()];

        for(int i = 0; i < array.length();i++){
            Label statLabel = new Label( array.getJSONObject(i).toString(),gameSkin,"default");
            statLabel.setY(Gdx.graphics.getHeight()-(i+1*30));
            labels[i] = statLabel;
        }
        return labels;
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