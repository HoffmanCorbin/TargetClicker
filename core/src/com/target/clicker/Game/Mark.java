package com.target.clicker.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.target.clicker.GameStats;

/***
 * This class works with the marking to be added to the background after a miss
 */


public class Mark {
    private float x;
    private float y;
    private int size;
    private int counter = 255;
    private boolean decrease=false;
    private ShapeRenderer shape;


    Mark(float x, float y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
        shape = new ShapeRenderer();

    }

    public void render(){
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        shape.setColor(new Color(240f/255, 161f/255, 156f/255,(float)counter/255));

        shape.begin(ShapeRenderer.ShapeType.Filled);

        shape.circle(x, y,size );
        shape.end();


       counter--;

    }



}
