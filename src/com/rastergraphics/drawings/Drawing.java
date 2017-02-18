/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.drawings;

import com.rastergraphics.model.BitmapGrid;
import com.rastergraphics.model.GridPoint;
import com.rastergraphics.pencils.Pencil;

/**
 *
 * @author asilkaratas
 */
public abstract class Drawing
{
    private String name;
    protected BitmapGrid bitmapGrid;
    protected Pencil pencil;
    
    public Drawing(String name)
    {
        this.name = name;
    }
    
    public abstract void draw(int x1, int y1, int x2, int y2);
    
    public void draw(GridPoint startPoint, GridPoint endPoint)
    {
        draw(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
    }
    
    public void setBitmapGrid(BitmapGrid bitmapGrid)
    {
        this.bitmapGrid = bitmapGrid;
    }
    
    public void setPencil(Pencil pencil)
    {
        this.pencil = pencil;
    }
    
    public void drawPixel(GridPoint point, boolean vertical)
    {
        drawPixel(point.getX(), point.getY(), vertical);
    }
    
    public void drawPixel(int x, int y, boolean vertical)
    {
        int pencilSize = pencil.getSize();
        int halfSize = (pencilSize - 1) / 2;
        
        int xPos = x;
        int yPos = y;
        
        if(vertical)
        {
            for(int i = 0; i < pencilSize; ++i)
            {
                yPos = y + i - halfSize;
                
                if(xPos >= 0 && xPos < bitmapGrid.getWidth() && yPos >= 0 && yPos < bitmapGrid.getHeight())
                {
                    bitmapGrid.setPixelColor(xPos, yPos, pencil.getColor());
                }
            }
        }
        else
        {
            for(int i = 0; i < pencilSize; ++i)
            {
                xPos = x + i - halfSize;
                if(xPos >= 0 && xPos < bitmapGrid.getWidth() && yPos >= 0 && yPos < bitmapGrid.getHeight())
                {
                    bitmapGrid.setPixelColor(xPos, yPos, pencil.getColor());
                }
            }
        }
        
        /*
        int[] pencilMatrix = pencil.getMatrix();
        int pencilColor = pencil.getColor();
        int index = 0;
        int pencilValue = 0;
        for(int i = 0; i < pencilSize; ++i)
        {
            for(int j = 0; j < pencilSize; ++j)
            {
                index = i * pencilSize + j;
                pencilValue = pencilMatrix[index];
                
                if(pencilValue == 1)
                {
                    xPos = x + j - halfSize;
                    yPos = y + i - halfSize;
                    
                    if(xPos >= 0 && xPos < bitmapGrid.getWidth() && yPos >= 0 && yPos < bitmapGrid.getHeight())
                    {
                        bitmapGrid.setPixelColor(xPos, yPos, pencil.getColor());
                    }
                }
            }
        }
                */
        
    }
    
    public String toString()
    {
        return name;
    }
}
