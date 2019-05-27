package flippycat.screens;

import java.awt.Color;
import java.awt.Point;

import PicoEngine.Screen;
import PicoEngine.Window;
import PicoEngine.ScreenManager;

import flippycat.entities.Player;
import flippycat.entities.Pipe;
import flippycat.Constants;

public class Game implements Screen {
    ScreenManager sm = ScreenManager.getInstance();

    public Player cat = new Player();
    public Pipe pipe;

    public void setup(Window win) {
        
        // Set the pallet
        win.setColor(Color.black);
        win.setBackgroundColor(Color.white);

        // Clear the window
        win.clear();

        // Create the pipe in the centre (make the first one predictable)
        pipe = new Pipe(win.getHeight() / 2 - Constants.pipe_gap_size, win.getHeight() / 2 + Constants.pipe_gap_size,
                Constants.pipe_width, win.getWidth());
        
        // Set the cat's x to be locked to a grid location
        cat.x = win.getGrid().getX(5);
    }

    public void feed(Window win) {
        boolean doJump = win.getKeyCode() == 32;

        // Check if the player should jump
        if (doJump) {
            cat.jump();
        }

        // Constant gravity is a good idea right?
        cat.y += 5;

        // Make sure the player does not hit the ground. This will kill them
        if (cat.y <= 0 || cat.y >= win.getHeight()) {

            // Switch to death screen, then reset the player's metrics
            sm.setScreen("Death", win);
            cat = new Player();
        }

        // Update the current pipe
        if (pipe.shoudRedraw()) {
            // The cat made it past a pipe. Reset the pipe, and increment the success counter
            pipe = new Pipe(win.getHeight() / 2 - Constants.pipe_gap_size,
                    win.getHeight() / 2 + Constants.pipe_gap_size, Constants.pipe_width, win.getWidth());
            Constants.success_count += 1;

        } else {
            // Move the pipe across the screen
            pipe.moveLeft(1);

            // Check if the cat died
            if (pipe.isColliding(new Point(cat.x, cat.y), Constants.cat_width)) {
                
                // Switch to death screen, then reset the player's metrics
                sm.setScreen("Death", win);
                cat = new Player();
            }
        }
    }
    
    public void draw(Window win) {
        synchronized (win) {
            win.clear();

            // Draw the "Cat"
            // This should be the final thing to be drawn.
            win.setColor(Color.black);
            win.fillRect(cat.x, cat.y, Constants.cat_width, Constants.cat_width);

            // Draw the current pipe
            pipe.draw(win);

        }
        win.sleep(2);
    }

}