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

    public void setup(Window win) {
        // Set the pallet
        win.setColor(Color.black);
        win.setBackgroundColor(Color.white);

        // Clear the window
        win.clear();

        // Move all buttons to correct location in grid
        start.setLocation(win.getGrid().getX(8), win.getGrid().getY(45));
    }

    public void feed(Window win) {
        Point loc = win.getMousePosition();
        int clickType = win.getMouseClick();

        boolean isStart    = (start.wasPressed(loc, clickType));

        // Check if the player wants to restart, then reset the counter, and switch to the Game scene
        if (isStart) {
            sm.setScreen("Game", win);
            Constants.success_count = 0;
        }
    }
    
    public void draw(Window win) {
        // One-time draw
        synchronized (win) {
            win.clear();

            // Make sure, again, that the color is correct
            win.setColor(Color.black);

            // Draw the text
            win.drawString("You died!", win.getGrid().getX(1), win.getGrid().getY(40));
            win.drawString("Next time, do better than " + Constants.success_count + " tries.", win.getGrid().getX(1),
                    win.getGrid().getY(43));
            
            // Debug draw the button
            start.debug(win);
        }
        win.sleep(2);
        
    }

}