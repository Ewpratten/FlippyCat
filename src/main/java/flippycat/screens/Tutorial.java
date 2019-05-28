package flippycat.screens;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import PicoEngine.Screen;
import PicoEngine.Window;
import PicoEngine.ScreenManager;
import PicoEngine.Paralaxer;
import PicoEngine.interaction.Button;

import flippycat.entities.Player;
import flippycat.entities.Pipe;
import flippycat.Constants;

public class Tutorial implements Screen {
    ScreenManager sm = ScreenManager.getInstance();

    Player cat = new Player();
    Pipe pipe;

    int hint_stage = 0;
    Button next = new Button(0, 0, Constants.rect_button_width, Constants.rect_button_height);

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
                Constants.pipe_width, (int)(win.getWidth() * 0.7));
        
        // Set the cat's x to be locked to a grid location
        cat.x = win.getGrid().getX(5);

        // Set the font
        win.setFont(Constants.main_font);

        // Move the button now that we know the grid sizing
        next.setLocation(win.getGrid().getX(2), win.getGrid().getY(62));
    }

    public void feed(Window win) {
        // Check if the "next" button was clicked
        Point loc = win.getMousePosition();
        int clickType = win.getMouseClick();

        boolean isNext = (next.wasPressed(loc, clickType));

        // if so, increment the stage
        if (isNext){
            hint_stage += 1;
        }
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
            cat.draw(win);

            // Draw the current hint window
            drawHint(win);

        }
        win.sleep(2);
    }
    
    private void drawHint(Window win) {
        // For each hint stage, do appropriate stuff

        switch (hint_stage) {
        case 0:
            // Hide everything that is not the cat with some white rectangles
            win.setColor(Color.white);
            win.rect(0, 0, win.getGrid().getX(4), win.getHeight(), true);
            win.rect(win.getGrid().getX(7), 0, win.getWidth(), win.getHeight(), true);
            win.rect(0, 0, win.getWidth(), 80, true);
            win.rect(0, 150, win.getWidth(), win.getHeight(), true);

            // Draw some text to explain what they are looking at
            win.setColor(Color.black);
            win.drawString("This is you", win.getGrid().getX(2), win.getGrid().getY(30));
            win.drawString("You are a flying cat", win.getGrid().getX(2), win.getGrid().getY(34));
            win.drawString("Don't ask why", win.getGrid().getX(2), win.getGrid().getY(38));

            // Draw the "next" button
            next.debug(win);
            break;
        case 1:
            // Hide everything that is not the pipe with some white rectangles
            win.setColor(Color.white);
            win.rect(0, 0, win.getGrid().getX(13), win.getHeight(), true);
            win.rect(win.getGrid().getX(18), 0, win.getWidth(), win.getHeight(), true);

            // Draw some text to explain what they are looking at
            win.setColor(Color.black);
            win.drawString("This is a pipe", win.getGrid().getX(1.2), win.getGrid().getY(20));

            // Draw the "next" button
            next.debug(win);
            break;
        }
    }

}