package com.target.clicker;

public class GameStats {


    int misses;
    int hits;
    int time;
    int score;
    int speed;
    int spawntime;
    int size;

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

    public int getScore() {
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

    public double getAdjustedScore() {
        return (score*((1 + .1*spawntime)+ (1 +  0.05*speed )))/(size/60);
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
