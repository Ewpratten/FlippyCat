package flippycat.entities;

import java.awt.Color;

import PicoEngine.Entity;
import PicoEngine.Window;

import flippycat.Constants;

public class Player extends Entity {
    
    public Player(){
        // Temp untill updated by game loop
        this.y = 100;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void jump() {
        this.y -= 10;

    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void draw(Window win) {
        win.setColor(Color.black);
        win.fillRect(x, y, Constants.cat_width, Constants.cat_width);
    }

}