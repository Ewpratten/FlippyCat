package flippycat;

import java.awt.Color;

import PicoEngine.ScreenManager;
import PicoEngine.Window;
import flippycat.screens.Credits;
import flippycat.screens.Menu;
import flippycat.screens.Game;

public class GameController {
    public Window win;
    ScreenManager sm = ScreenManager.getInstance();

    public void setup() {
        win.autoConfig(Color.black);

        // Register screens
        sm.register("Credits", new Credits());
        sm.register("Menu", new Menu());
        sm.register("Game", new Game());

        // Set default screen
        sm.setScreen("Credits", win);
    }
    
    public void loop() {
        synchronized (win) {
            win.clear();
            sm.feed(win);
            sm.draw(win);
        }
        win.sleep(2);
    }
}