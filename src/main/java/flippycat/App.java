/**
 * The App class is just a wrapper around a GameController. This allows a slightly cleaner (and non-static) interface for the programmers.
 */
package flippycat;

import PicoEngine.Window;

public class App {
    static final int width = 600;
    static final int height = 800;

    // Create the window
    static Window win = new Window(width, height, 20, 80, "FlippyCat");
    static GameController controller = new GameController();

    public static void main(String[] args) {
        new App();
    }

    App() {

        // Inject win into controller
        controller.win = win;

        // Set up game
        controller.setup();

        // Game loop
        while (true) {
            controller.loop();
        }
        
    }
}
