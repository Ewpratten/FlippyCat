package flippycat.entities;

import PicoEngine.Entity;

public class Player extends Entity {
    
    public Player(){
        // Temp untill updated by game loop
        this.y = 100;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void jump() {
        this.y -= 15;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void setY(int y){
        this.y = y;
    }

}