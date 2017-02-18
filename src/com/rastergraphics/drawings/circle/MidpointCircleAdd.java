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
public class MidpointCircleAdd extends CircleDrawing
{
    public MidpointCircleAdd()
    {
        super("midpoint add");
    }
    
    public void draw(int x1, int y1, int x2, int y2)
    {
        this.centerX = x1;
        this.centerY = y1;
        
        float dx = x2 - x1;
        float dy = y2 - y1;
        int R = (int)Math.sqrt(dx * dx + dy * dy);
        
        int dE = 3;
        int dSE = 5 - 2 * R;
        int d = 1 - R;
        int x = 0;
        int y = R;
        
        drawPoints(x, y);
        
        while(y > x)
        {
            if(d < 0)
            {
               d += dE;
               dE += 2;
               dSE += 2;
            }
            else
            {
                d += dSE;
                dE += 2;
                dSE += 4;
                --y;
            }
            ++x;
            drawPoints(x, y);
        }
        
        bitmapGrid.update();
    }
}
