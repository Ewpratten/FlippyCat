package flippycat.entities;

import java.awt.Color;
import java.awt.Point;

import PicoEngine.Window;
import PicoEngine.templates.GappedWall;

public class Pipe implements GappedWall {
    private int x, width;
    private int min, max;

    public Pipe(int min, int max, int width, int startX) {
        this.x = startX;
        this.width = width;
        this.min = min;
        this.max = max;
    }

    public boolean isColliding(Point loc, int colliderWidth) {
        boolean inGap = (loc.getY() > min && loc.getY() + colliderWidth < max);
        boolean touchingSide = (loc.getX() + colliderWidth >= x && loc.getX() <= x + width);

        // Colliding if touching but not in gap
        if (touchingSide && inGap) {
            return false;
        }

        return touchingSide;
    }

    public void setGap(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public void draw(Window win) {
        // Temp rects. Replace with images
        win.rect(x, 0, width, min, true);
        win.rect(x, max, width, win.getHeight(), true);
    }

    public boolean shoudRedraw() {
        return x + width <= 0;
    }

    public void moveLeft(int px) {
        this.x -= px;
    }

}