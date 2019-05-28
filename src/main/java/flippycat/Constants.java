package flippycat;

import java.awt.Font;

/**
 * Constants and magic numbers are defined here with comments
 */
public class Constants {
    // This one is for fun
    public static final boolean should_auto_solve = false;

    // sizing
    public static final int squared_button_width = 64; // in px
    public static final int cat_width = 32; // in px
    public static final int rect_button_height = 96;
    public static final int rect_button_width = 192;

    public static final int pipe_width = 80;
    public static final int pipe_gap_size = 100;
    public static final int pipe_rand_buffer = 64;

    public static final double bg_scroll_speed = 0.6;

    // This one is not constant, but still global
    public static int success_count = 0;

    public static Font main_font = new Font("Monospaced", Font.PLAIN, 40);

    public class Grid {
        public static final int width = 20;
        public static final int height = 80;
    }
}