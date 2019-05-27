package flippycat;

/**
 * Constants and magic numbers are defined here with comments
 */
public class Constants {
    public static final int squared_button_width = 64; // in px
    public static final int cat_width = 32; // in px
    public static final int rect_button_height = 64;
    public static final int rect_button_width = 128;

    public static final int pipe_width = 64;
    public static final int pipe_gap_size = 100;
    public static final int pipe_rand_buffer = 64;

    // This one is not constant, but still global
    public static int success_count = 0;

    public class Grid {
        public static final int width = 20;
        public static final int height = 80;
    }
}