package flippycat;

import java.awt.Color;

import PicoEngine.ScreenManager;
import PicoEngine.Window;
import flippycat.screens.Credits;
import flippycat.screens.Menu;
import flippycat.screens.Game;
import flippycat.screens.Death;

public class GameController {
    public Window win;
    ScreenManager sm = ScreenManager.getInstance();

    public void setup() {
        win.autoConfig(Color.black);

        // Register screens
        sm.register("Credits", new Credits());
        sm.register("Menu", new Menu());
        sm.register("Game", new Game());
        sm.register("Death", new Death());

        // Set default screen
        sm.setScreen("Credits", win);
    }
    
    public void loop() {
        synchronized (win) {
            sm.feed(win);
            sm.draw(win);
        }
        win.sleep(2);
    }
}