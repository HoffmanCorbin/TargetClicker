package com.target.clicker.Game.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/***
 * This class works with the marking to be added to the background after a miss
 */


public class Mark {
    private float x;
    private float y;
    private float size;
    private int counter = 255;
    private boolean decrease=false;
    private ShapeRenderer shape;


    public Mark(float x, float y, float size){
        this.x = x;
        this.y = y;
        this.size = size *(Gdx.graphics.getHeight()/1080f);
        shape = new ShapeRenderer();
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("buzz.wav"));
        sound.play();

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
