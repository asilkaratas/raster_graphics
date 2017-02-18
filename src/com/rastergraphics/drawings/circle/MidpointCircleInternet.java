/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.drawings.circle;

/**
 *
 * @author asilkaratas
 */
public class MidpointCircleInternet extends CircleDrawing
{
    public MidpointCircleInternet()
    {
        super("midpoint internet");
    }
    
    public void draw(int x1, int y1, int x2, int y2)
    {
        this.centerX = x1;
        this.centerY = y1;
        
        float dx = x2 - x1;
        float dy = y2 - y1;
        int R = (int)Math.sqrt(dx * dx + dy * dy);
        
        int d = 1 - R;
        int x = 0;
        int y = R;
        
        drawPoints(x, y);
        
        while(x < y)
        {
            ++x;
            if(d < 0)
            {
                d += 2 * x + 1;
            }
            else
            {
                --y;
                d += 2 * (x-y) + 1;
            }
            drawPoints(x, y);
        }
        
        bitmapGrid.update();
    }
}
