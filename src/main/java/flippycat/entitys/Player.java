package flippycat;

import PicoEngine.Entity;

public class Player extends Entity{
    String name = "Player";
    int x, y;

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