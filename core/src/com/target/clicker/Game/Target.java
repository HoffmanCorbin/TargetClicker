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
    public double timeElapsed;
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

    public Target(Vector2 windowSize, int size, int speed){
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
        if(!destroyed) {

            shape.setColor(Color.WHITE);
            shape.begin(ShapeRenderer.ShapeType.Filled);

            move();


            timeElapsed = timeElapsed + Gdx.graphics.getDeltaTime();


            hitbox.setPosition(new Vector2(x,y));
            hitbox.setRadius(size);
            shape.circle(x, y, size);
            shape.end();

            counter++;
           if(counter%1000 ==0) {
               size++;
           }
        }
    }

    private void move(){
        if(x < windowSize.x - size/2 && x > 0+size) {


            x = (float) (x + direction.x + (direction.x*(1 + timeElapsed)));
        }
        else{
            direction.x = direction.x*-1;
            x = (float) (x + direction.x + (direction.x *(1 + timeElapsed)));
        }

        if(y < windowSize.y&& y -size/2> 0+size) {
            y = (float) (y + direction.y + (direction.y * (1 + timeElapsed)));
        }
        else{
            direction.y = direction.y *-1;
            y = (float) (y + direction.y + (direction.y * (1 + timeElapsed)));
        }
    }
    public void dispose(){
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

    public boolean hit(float x, float y){
        if(hitbox.contains(x,y))return true;
        return false;
        
    }



}