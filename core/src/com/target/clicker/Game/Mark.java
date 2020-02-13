package com.target.clicker.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.target.clicker.GameStats;

/***
 * This Class works with the marking to be added to the background after a miss
 */


public class Mark {
    private float x;
    private float y;
    private int size;
    private int counter = 0;
    private boolean decrease=false;
    private ShapeRenderer shape;


    public Mark(float x, float y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
        shape = new ShapeRenderer();

    }

    public void render(){
        shape.setColor(new Color(240f/255, 161f/255, 156f/255,0.1f/255));
        shape.begin(ShapeRenderer.ShapeType.Filled);

        shape.circle(x, y,counter );
        shape.end();


        incrementCounter();

    }

    private void incrementCounter(){
        if(counter>size){
            decrease= true;
        }
        if(decrease){
            counter--;
        }
        else{
            counter++;
        }
    }

}
