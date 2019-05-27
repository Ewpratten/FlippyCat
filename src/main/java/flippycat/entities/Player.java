package flippycat.entities;

import PicoEngine.Entity;

public class Player extends Entity{
    String name = "";
    int x, y;

    public void setName(String name) {
        this.name = name;
    }

    public void jump() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

}