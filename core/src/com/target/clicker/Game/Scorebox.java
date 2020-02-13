package com.target.clicker.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Scorebox {


    private  SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer scoreBox;



    public Scorebox(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        scoreBox = new ShapeRenderer();


    }

    public void render(int score, Double remainingTime){
        scoreBox.begin(ShapeRenderer.ShapeType.Filled);
        scoreBox.setColor(Color.BLACK);
        scoreBox.rect(0, Gdx.graphics.getHeight()-50,100,50);
        scoreBox.end();

        scoreBox.begin(ShapeRenderer.ShapeType.Line);
        scoreBox.setColor(Color.WHITE);
        scoreBox.rect(0,Gdx.graphics.getHeight()-50,100,50);
        scoreBox.end();

        batch.begin();

        font.draw(batch, "Score: " + score, 10, Gdx.graphics.getHeight()-10);
        font.draw(batch, "Time: " + String.format("%.2f", remainingTime), 10, Gdx.graphics.getHeight()-30);
        batch.end();
    }


}
