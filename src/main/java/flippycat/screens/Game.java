package flippycat.screens;

import java.awt.Color;

import PicoEngine.Screen;
import PicoEngine.Window;

import flippycat.entities.Player;

public class Game implements Screen {
    public boolean isJump;
    public Player cat;    

    public void setup(Window win) {
        
        // Set the pallet
        win.setColor(Color.black);
        win.setBackgroundColor(Color.green);

        // Clear the window
        win.clear();
    }

    public void feed(Window win) {
        isJump = win.getKeyCode() == 32;
        if (isJump) {
            cat.jump();
        }
    }
    
    public void draw(Window win) {
        synchronized (win) {
            win.clear();
        }
        
    }

}