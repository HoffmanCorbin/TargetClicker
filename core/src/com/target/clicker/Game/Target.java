package com.target.clicker.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
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
    private int size;
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
    Target(Vector2 windowSize, int size, int speed){
        this.windowSize = windowSize;

        shape = new ShapeRenderer();
        hitbox = new Circle();
        hp =1;
        this.size = size;

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
    void dispose(){
        shape = null;
        hitbox = null;
    }

    private Vector2 determineLocation(){

        Vector2 temp = new Vector2();
        if(x > windowSize.x){
            temp.x = -speed;
        }
        else{
            temp.x= speed;
        }
        if(y > windowSize.y){
            temp.y = -speed;
        }
        else{
            temp.y = speed;
        }


        return temp;

    }

    boolean hit(float x, float y){
        if(hitbox.contains(x,y))return true;
        return false;
        
    }



}