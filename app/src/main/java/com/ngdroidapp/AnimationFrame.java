package com.ngdroidapp;

public class AnimationFrame {

    private String sourceFilePath;
    private int sourceX;
    private int sourceY;
    private int sourceWidth;
    private int sourceHeight;

    AnimationFrame() {
        sourceFilePath = "";
        sourceX = 0;
        sourceY = 0;
        sourceWidth = 0;
        sourceHeight = 0;
    }

    AnimationFrame(
            String sourceFilePath,
            int sourceX,
            int sourceY,
            int sourceWidth,
            int sourceHeight
    ) {
        this.sourceFilePath = sourceFilePath;
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.sourceWidth = sourceWidth;
        this.sourceHeight = sourceHeight;
    }

    /* Getter Methods */
    public String getSourceFilePath() { return sourceFilePath; }
    public int getSourceX() { return sourceX; }
    public int getSourceY() { return sourceY; }
    public int getSourceWidth() { return sourceWidth; }
    public int getSourceHeight() { return sourceHeight; }

    /* Setter Methods */
    public void setSourceFilePath(String sourceFilePath) { this.sourceFilePath = sourceFilePath; }
    public void setSourceX(int sourceX) { this.sourceX = sourceX; }
    public void setSourceY(int sourceY) { this.sourceY = sourceY; }
    public void setSourceWidth(int sourceWidth) { this.sourceWidth = sourceWidth; }
    public void setSourceHeight(int sourceHeight) { this.sourceHeight = sourceHeight; }
}
