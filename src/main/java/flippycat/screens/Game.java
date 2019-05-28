package flippycat.screens;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import PicoEngine.Screen;
import PicoEngine.Window;
import PicoEngine.ScreenManager;
import PicoEngine.Paralaxer;

import flippycat.entities.Player;
import flippycat.entities.Pipe;
import flippycat.Constants;

public class Game implements Screen {
    ScreenManager sm = ScreenManager.getInstance();

    Player cat = new Player();
    Pipe pipe;

    // RNG for pipe height
    Random rand = new Random();

    // Paralaxer for background art
    Paralaxer background = new Paralaxer(Constants.bg_scroll_speed, "Background-1.png", "Background-2.png",
            "Background-3.png");

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
            
            // Get the next centre pos of the pipes while taking into account the buffer zones
            int gapPos = rand.nextInt(win.getHeight() - (Constants.pipe_rand_buffer * 2)) + Constants.pipe_rand_buffer;


            pipe = new Pipe(gapPos - Constants.pipe_gap_size,
                            gapPos + Constants.pipe_gap_size, 
                            Constants.pipe_width, win.getWidth());
            Constants.success_count += 1;

        } else {
            // Move the pipe across the screen
            pipe.moveLeft(4); // 4 is about the max speed to go from a bottom to a top pipe1

            // Check if the cat died
            if (pipe.isColliding(new Point(cat.x, cat.y), Constants.cat_width)) {

                // Switch to death screen, then reset the player's metrics
                sm.setScreen("Death", win);
                cat = new Player();
            }
        }
        
        // Tell the Paralaxer that the background should be moving
        background.scroll();
    }
    
    public void draw(Window win) {
        synchronized (win) {
            win.clear();

            // Draw the background art
            background.draw(win);

            // Draw the current pipe
            pipe.draw(win);

            // Draw a temp ground
            win.setColor(Color.green);
            win.rect(0, win.getGrid().getY(77), win.getWidth(), win.getGrid().getY(3), true);

            // Draw the "Cat"
            // This should be the final thing to be drawn.
            win.setColor(Color.black);
            win.fillRect(cat.x, cat.y, Constants.cat_width, Constants.cat_width);

            // Experimental crosshair on the cat
            // win.setColor(Color.white);
            // win.drawLine(0, cat.y + (int) (Constants.cat_width / 2), win.getWidth(), cat.y + (int) (Constants.cat_width / 2));
            // win.drawLine(cat.x + (int) (Constants.cat_width / 2), 0, cat.x + (int) (Constants.cat_width / 2), win.getHeight());
            
        }
        win.sleep(2);
    }

}