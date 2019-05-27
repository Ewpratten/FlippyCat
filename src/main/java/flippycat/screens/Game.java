package flippycat.screens;

import java.awt.Color;

import PicoEngine.Screen;
import PicoEngine.Window;
import PicoEngine.ScreenManager;

import flippycat.entities.Player;
import flippycat.Constants;

public class Game implements Screen {
    ScreenManager sm = ScreenManager.getInstance();

    public boolean isJump;
    public Player cat = new Player();    

    public void setup(Window win) {
        
        // Set the pallet
        win.setColor(Color.black);
        win.setBackgroundColor(Color.white);

        // Clear the window
        win.clear();
    }

    public void feed(Window win) {
        isJump = win.getKeyCode() == 32;

        // Check if the player should jump
        if (isJump) {
            cat.jump();
        }

        // Constant gravity is a good idea right?
        cat.y += 10;

        // Make sure the player does not hit the ground. This will kill them
        if (cat.y <= 0 || cat.y >= win.getHeight()) {
            sm.setScreen("Death", win);
            cat = new Player();
        }
    }
    
    public void draw(Window win) {
        synchronized (win) {
            win.clear();

            // Draw the "Cat"
            // This should be the final thing to be drawn.
            win.setColor(Color.black);
            win.fillRect(win.getGrid().getX(5), cat.getY(), Constants.cat_width, Constants.cat_width);

        }
        win.sleep(2);
    }

}