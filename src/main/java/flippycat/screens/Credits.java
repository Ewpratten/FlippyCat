package flippycat.screens;

import java.awt.Color;
import java.awt.Font;

import PicoEngine.Screen;
import PicoEngine.ScreenManager;
import PicoEngine.Window;

public class Credits implements Screen {
    ScreenManager sm = ScreenManager.getInstance();

    // We use a timeout to deal with an I/O issue with hsa
    int timeout_counter = 200;

    public void setup(Window win) {
        // Set the pallet
        win.setColor(Color.black);
        win.setBackgroundColor(Color.white);

        // Clear the window
        win.clear();
    }

    public void feed(Window win) {

    }
    
    public void draw(Window win) {
        // One-time draw
        synchronized (win) {
            win.clear();

            // Make sure, again, that the color is correct
            win.setColor(Color.black);

            // Draw the text
            win.drawString("Developers:", win.getGrid().getX(1), win.getGrid().getY(70));
            win.drawString("Evan Pratten", win.getGrid().getX(1), win.getGrid().getY(73));
            win.drawString("Carter Tomlenovich", win.getGrid().getX(1), win.getGrid().getY(74.5));
        }
        win.sleep(2);

        if (timeout_counter <= 0) {
            sm.setScreen("Menu", win);
        } else {
            timeout_counter -= 1;
        }
        
    }

}