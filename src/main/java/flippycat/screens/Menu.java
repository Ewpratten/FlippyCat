package flippycat.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import PicoEngine.Screen;
import PicoEngine.ScreenManager;
import PicoEngine.Window;
import PicoEngine.interaction.Button;

import flippycat.Constants;

public class Menu implements Screen {
    ScreenManager sm = ScreenManager.getInstance();

    // This button must sit in the bottom left of the screen.
    // But when this code is run, we do not know where that actually is. 
    // Creating a new button during setup is a waste of CPU time, so, 
    // we create a button in the wrong location now, then move it in setup()
    Button settings = new Button(0, 0, Constants.squared_button_width, Constants.squared_button_width);
    Button exit = new Button(0, 0, Constants.squared_button_width, Constants.squared_button_width);
    Button start = new Button(0, 0, Constants.rect_button_width, Constants.rect_button_height);

    public void setup(Window win) {
        // Set the pallet
        win.setColor(Color.black);
        win.setBackgroundColor(Color.white);

        // Clear the window
        win.clear();

        // Move all buttons to correct location in grid
        exit.setLocation(win.getGrid().getX(20) - Constants.squared_button_width, 0);
        start.setLocation(win.getGrid().getX(8), win.getGrid().getY(45));
    }

    public void feed(Window win) {
        Point loc = win.getMousePosition();
        int clickType = win.getMouseClick();

        boolean isExit     = (exit.wasPressed(loc, clickType));
        boolean isSettings = (settings.wasPressed(loc, clickType));
        boolean isStart    = (start.wasPressed(loc, clickType));


        if (isStart) {
            sm.setScreen("Game", win);
        } else if (isSettings) {
            // Do nothing. We do not have a settings screen
        } else if (isExit) {
            System.exit(0);
        }
    }
    
    public void draw(Window win) {
        // One-time draw
        synchronized (win) {
            win.clear();

            settings.debug(win);
            exit.debug(win);
            start.debug(win);
        }
        win.sleep(2);
    }

}