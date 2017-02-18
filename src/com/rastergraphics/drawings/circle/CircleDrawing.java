/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.drawings.circle;

import com.rastergraphics.drawings.Drawing;

/**
 *
 * @author asilkaratas
 */
public abstract class CircleDrawing extends Drawing
{
    protected int centerX;
    protected int centerY;
    
    public CircleDrawing(String name)
    {
        super(name);
    }
    
    protected void drawPoints(int x, int y)
    {
       drawPoint(centerX + x, centerY + y, true);
       drawPoint(centerX - x, centerY + y, true);
       drawPoint(centerX + x, centerY - y, true);
       drawPoint(centerX - x, centerY - y, true);
       
       drawPoint(centerX + y, centerY + x, false);
       drawPoint(centerX - y, centerY + x, false);
       drawPoint(centerX + y, centerY - x, false);
       drawPoint(centerX - y, centerY - x, false);
    }
    
    private void drawPoint(int x, int y, boolean vertical)
    {
        if(x >= 0 && x < bitmapGrid.getWidth() && y >= 0 && y < bitmapGrid.getHeight())
        {
          drawPixel(x, y, vertical);  
        }
    }
}
