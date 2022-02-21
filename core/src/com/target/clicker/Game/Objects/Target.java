package com.target.clicker.Game.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.GdxNativesLoader;

import java.util.Random;


public class Target{
    private Circle hitbox;
    private ShapeRenderer shape;
    private int hp;
    private float x;
    private float y;
    private double timeElapsed;
    private Random random;
    private int counter =0;

    private int speed;


    private Vector2 direction;


    private Vector2 windowSize;
    private float size;
    private Boolean destroyed = false;
    static{
        GdxNativesLoader.load();
    }

    /**
     * Default constructor
     * @param windowSize - The size of the actual game window
     * @param size - The size of the target
     * @param speed - The speed of the target
     */
    public Target(Vector2 windowSize, float size, int speed){
        this.windowSize = windowSize;

        shape = new ShapeRenderer();
        hitbox = new Circle();
        hp =1;
        this.size = size*(Gdx.graphics.getHeight()/1080f);

        random = new Random();
        x= random.nextInt((int)((windowSize.x - (size*3))))+(size*1.5f);
        y = random.nextInt((int)((windowSize.y - (size*3))))+(size*1.5f);

        this.speed = speed;

        direction = determineLocation();

    }

    public void render(){
        // Renders if not destroyed
        if(!destroyed) {
            shape.setColor(Color.WHITE);
            shape.begin(ShapeRenderer.ShapeType.Filled);
            move();
            timeElapsed = timeElapsed + Gdx.graphics.getDeltaTime();
            hitbox.setPosition(new Vector2(x,y));
            hitbox.setRadius(size);
            shape.circle(x, y, size);
            shape.end();
        }
    }

    private void move(){
        if(x < windowSize.x - size/2f && x > size) {
            x =  (x + direction.x + (direction.x*((float)-timeElapsed/100f)));
        }
        else{
            direction.x = direction.x*-1;
            x = (x + direction.x + (direction.x *((float)-timeElapsed/100f)));
        }

        if(y < windowSize.y&& y -size/2f> size) {
            y =  (y + direction.y + (direction.y*((float)-timeElapsed/100f)));
        }
        else{
            direction.y = direction.y *-1;
            y =  (y + direction.y + (direction.y *((float)-timeElapsed/100f)));
        }
    }
    public void dispose(){
        shape = null;
        hitbox = null;
    }

    private Vector2 determineLocation(){
        double x = Math.random()-0.5;
        double y = Math.random()-0.5;

        return new Vector2((float) ((float)x/Math.abs(x))*speed,((float) ((float)y/Math.abs(y)))*speed);
    }

    //TODO: add sound attribution at

    public boolean hit(float x, float y){
        if(hitbox.contains(x,y)) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("pop.wav"));
            sound.play(1.0f);
            return true;
        }
        return false;
    }
}