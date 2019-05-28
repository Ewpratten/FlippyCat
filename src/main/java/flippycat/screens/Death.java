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

    Button start = new Button(0, 0, Constants.rect_button_width, Constants.rect_button_height);
    Button ragequit = new Button(0, 0, Constants.rect_button_width, Constants.rect_button_height);

    public void setup(Window win) {
        // Move all buttons to correct location in grid
        start.setLocation(win.getGrid().getX(5.5), win.getGrid().getY(57));
        ragequit.setLocation(win.getGrid().getX(5.5), win.getGrid().getY(38));
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
            // win.rect(win.getGrid().getX(5), win.getGrid().getY(15), win.getGrid().getX(10), win.getGrid().getY(60), true);

            // Make sure, again, that the color is correct
            win.setColor(Color.black);

            // Draw the text
            win.drawString("You died!", win.getGrid().getX(9), win.getGrid().getY(20));
            win.drawString("Next time, do better than " + Constants.success_count + " tries.", win.getGrid().getX(7),
                    win.getGrid().getY(28));
            
            // Debug draw the button
            start.debug(win);
            ragequit.debug(win);
        }
        win.sleep(2);
        
    }

}