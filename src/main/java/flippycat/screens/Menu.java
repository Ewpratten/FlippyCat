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
    Button exit = new Button(0, 0, Constants.squared_button_width, Constants.squared_button_width);
    Button start = new Button(0, 0, Constants.rect_button_width, Constants.rect_button_height, "start.png");
    Button tutorial = new Button(0, 0, Constants.rect_button_width, Constants.rect_button_height, "help.png");

    public void setup(Window win) {
        // Set the pallet
        win.setColor(Color.black);
        win.setBackgroundColor(Color.white);

        // Clear the window
        win.clear();

        // Move all buttons to correct location in grid
        exit.setLocation(win.getGrid().getX(20) - Constants.squared_button_width, 0);
        start.setLocation(win.getGrid().getX(7), win.getGrid().getY(50));
        tutorial.setLocation(win.getGrid().getX(7), win.getGrid().getY(40));
    }

    public void feed(Window win) {
        Point loc = win.getMousePosition();
        int clickType = win.getMouseClick();

        boolean isExit     = (exit.wasPressed(loc, clickType));
        boolean isStart    = (start.wasPressed(loc, clickType));
        boolean isTutorial = (tutorial.wasPressed(loc, clickType));


        if (isStart) {
            sm.setScreen("Game", win);
        } else if (isTutorial) {
            sm.setScreen("Tutorial", win);
        } else if (isExit) {
            System.exit(0);
        }
    }
    
    public void draw(Window win) {
        // One-time draw
        synchronized (win) {
            win.clear();

            exit.debug(win);
            start.draw(win);
            tutorial.draw(win);
        }
        win.sleep(2);
    }

}