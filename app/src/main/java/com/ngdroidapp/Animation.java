package com.ngdroidapp;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Bitmap;

/**
 * Created by Muhammed T. Karakul on 16.06.2018
 * You can use this class for create awesome animations :) Good luck!
 */

public class Animation {

    private String sourceFilePath;

    private int firstFrame;
    private int lastFrame;

    private int sourceX;
    private int sourceY;
    private int sourceWidth;
    private int sourceHeight;

    private int destinationX;
    private int destinationY;
    private int destinationWidth;
    private int destinationHeight;



     /* Constructor Methods */

    /**
     * Constructor method with no parameter.
     */
    Animation() {
        sourceFilePath = "";
        firstFrame = 0;
        lastFrame = 0;
        sourceX = 0;
        sourceY = 0;
        sourceWidth = 0;
        sourceHeight = 0;

        destinationX = 0;
        destinationY = 0;
        destinationWidth = 0;
        destinationHeight = 0;
    }

    /**
     * Constructor method with parameters.
     * @param firstFrame The animation's first frame.
     * @param lastFrame The animation's last frame.
     * @param sourceFilePath The graphics's source file path.
     * @param sourceX The source file chosen field x coordinate.
     * @param sourceY The source file chosen field y coordinate.
     * @param sourceWidth The source file chosen field width.
     * @param sourceHeight The source file chosen field height.
     */
    Animation(
            String sourceFilePath,
            int firstFrame,
            int lastFrame,
            int sourceX,
            int sourceY,
            int sourceWidth,
            int sourceHeight,
            int destinationX,
            int destinationY,
            int destinationWidth,
            int destinationHeight
    ) {
        this.sourceFilePath = sourceFilePath;
        this.firstFrame = firstFrame;
        this.lastFrame = lastFrame;
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.sourceWidth = sourceWidth;
        this.sourceHeight = sourceHeight;

        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.destinationWidth = destinationWidth;
        this.destinationHeight = destinationHeight;
    }

    /* Getter Methods */
    public String getSourceFilePath() { return this.sourceFilePath; }
    public int getFirstFrame() { return this.firstFrame; }
    public int getLastFrame() { return this.lastFrame; }
    public int getSourceX() { return this.sourceX; }
    public int getSourceY() { return this.sourceY; }
    public int getSourceWidht() { return this.sourceWidth; }
    public int getSourceHeight() { return this.sourceHeight; }
    public int getDestinationX() { return this.destinationX; }
    public int getDestinationY() { return this.destinationY; }
    public int getDestinationWidth() { return this.destinationWidth; }
    public int getDestinationHeight() { return this.destinationHeight; }

    /* Setter Methods */
    public void setSourceFilePath(String sourceFilePath) { this.sourceFilePath = sourceFilePath; }
    public void setFirstFrame(int firstFrame) { this.firstFrame = firstFrame; }
    public void setLastFrame(int lastFrame) { this.lastFrame = lastFrame; }
    public void setSourceX(int sourceX) { this.sourceX = sourceX; }
    public void setSourceY(int sourceY) { this.sourceY = sourceY; }
    public void setSourceWidht(int sourceWidth) { this.sourceWidth = sourceWidth; }
    public void setDestinationX(int destinationX) { this.destinationX = destinationX; }
    public void setDestinationY(int destinationY) { this.destinationY = destinationY; }
    public void setDestinationWidth(int destinationWidth) { this.destinationWidth = destinationWidth; }
    public void setDestinationHeight(int destinationHeight) { this.destinationHeight = destinationHeight; }

    /* Animation Methods */
    public void play() {

    }

}
