package com.target.clicker;

public class GameStats {


    private int misses;
    private int hits;
    private int time;
    private int score;
    private int speed;
    private int spawntime;
    private int size;

    public GameStats()
    {
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getMisses() {
        return misses;
    }

    public void setMisses(int misses) {
        this.misses = misses;
    }

    int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    double getAdjustedScore() {
        return (score+(0.1*((.5*(spawntime-1))+ (0.1*speed)+(.05 * ( 60-size)))));
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSpawntime(int spawntime) {
        this.spawntime = spawntime;
    }

    public int getSpawntime() {
        return spawntime;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
