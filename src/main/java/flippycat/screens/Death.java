package flippycat.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import PicoEngine.Screen;
import PicoEngine.ScreenManager;
import PicoEngine.Window;
import PicoEngine.interaction.Button;

import flippycat.Constants;

public class Death implements Screen {
    ScreenManager sm = ScreenManager.getInstance();

    Button start = new Button(0, 0, Constants.rect_button_width, Constants.rect_button_height, "start.png");
    Button ragequit = new Button(0, 0, Constants.rect_button_width, Constants.rect_button_height, "ragequit.png");

    public void setup(Window win) {
        // Move all buttons to correct location in grid
        start.setLocation(win.getGrid().getX(7), win.getGrid().getY(57));
        ragequit.setLocation(win.getGrid().getX(7), win.getGrid().getY(46));

        // Set the font to use
        win.setFont(Constants.main_font);
    }

    public void feed(Window win) {
        Point loc = win.getMousePosition();
        int clickType = win.getMouseClick();

        boolean isStart = (start.wasPressed(loc, clickType));
        boolean isQuit = (ragequit.wasPressed(loc, clickType));

        // Check if the player wants to restart, then reset the counter, and switch to the Game scene
        if (isStart) {
            sm.setScreen("Game", win);
            Constants.success_count = 0;
        } else if (isQuit) {
            System.exit(0);
        }
    }
    
    public void draw(Window win) {
        // One-time draw
        synchronized (win) {
            // Not clearing. Just drawing a box

            win.setColor(Color.white);
            win.setBackgroundColor(Color.white);
            win.clear();

            // Make sure, again, that the color is correct
            win.setColor(Color.black);

            // Draw the text
            win.drawString("You died!", win.getGrid().getX(6), win.getGrid().getY(10));
            win.drawString("Next time, do better ", win.getGrid().getX(2), win.getGrid().getY(28));
            win.drawString("than " + Constants.success_count + " points.", win.getGrid().getX(5), win.getGrid().getY(32));
            
            // Debug draw the button
            start.draw(win);
            ragequit.draw(win);
        }
        win.sleep(2);
        
    }

}