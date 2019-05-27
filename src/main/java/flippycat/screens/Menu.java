package flippycat.screens;

import java.awt.Color;
import java.awt.Font;

import PicoEngine.Screen;
import PicoEngine.ScreenManager;
import PicoEngine.Window;
import PicoEngine.interaction.Button;

public class Menu implements Screen {
    ScreenManager sm = ScreenManager.getInstance();

    Button settings = new Button(0, 0, 32, 32);

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

            settings.debug(win);
        }
        win.sleep(2);
    }

}