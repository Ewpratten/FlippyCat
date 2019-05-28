package flippycat.entities;

import java.awt.Color;
import java.awt.image.BufferedImage;

import PicoEngine.Entity;
import PicoEngine.Window;
import PicoEngine.ImageUtils;

import flippycat.Constants;

public class Player extends Entity {

    BufferedImage sprite;
    
    public Player(){
        // Temp untill updated by game loop
        this.y = 100;

        sprite = ImageUtils.getInstance().loadImageFromResources("Cat.png");
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
        win.drawImage(sprite, x - 2, y - 2, Constants.cat_width + 4, Constants.cat_width + 4);
    }

}