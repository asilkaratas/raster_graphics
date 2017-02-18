/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.drawings.line;

import com.rastergraphics.drawings.Drawing;
/**
 *
 * @author asilkaratas
 */
public class MidpointLine extends Drawing
{
    public MidpointLine()
    {
        super("midpoint");
    }
    
    public void draw(int x1, int y1, int x2, int y2)
    {   
        float m = ((float)(y2 - y1)/(x2 - x1));
        if(Math.abs(m) < 1)
        {
            drawLineX(x1, y1, x2, y2);
        }
        else
        {
            drawLineY(x1, y1, x2, y2);
        }
    }
    
    private void drawLineX(int x1, int y1, int x2, int y2)
    {
        int dy = Math.abs(y2 - y1);
        int dx = Math.abs(x2 - x1);
        int d = 2 * dy - dx;
        int dE = 2 * dy;
        int dNE = 2 * (dy - dx);
        int x = x1;
        int y = y1;
        
        int yStep = (y1 < y2) ? 1 : -1;
        if(x1 > x2)
        {
            x = x2;
            y = y2;
            x2 = x1;
            
            yStep = -yStep;
        }
        else
        {
            x = x1;
            y = y1;
        }
        
        drawPixel(x, y, true);
        
        System.out.println("drawLineX d:" + d);
        
        while(x < x2)
        {
            ++x;
            if(d < 0)
            {
                d += dE;
            }
            else
            {
                d += dNE;
                y += yStep;
            }

            drawPixel(x, y, true);
        }  
        
        bitmapGrid.update();
    }
    
    private void drawLineY(int x1, int y1, int x2, int y2)
    {
        int dy = Math.abs(y2 - y1);
        int dx = Math.abs(x2 - x1);
        int d = 2 * dx - dy;
        int dE = 2 * dx;
        int dNE = 2 * (dx - dy);
        int x = x1;
        int y = y1;
        
        int xStep = (x1 < x2) ? 1 : -1;
        if(y1 > y2)
        {
            y = y2;
            x = x2;
            y2 = y1;
            
            xStep = -xStep;
        }
        else
        {
            y = y1;
            x = x1;
        }
        
        drawPixel(x, y, false);
        
        //System.out.println("drawLineY d:" + d);
        
        while(y < y2)
        {
            ++y;
            if(d < 0)
            {
                d += dE;
            }
            else
            {
                d += dNE;
                x += xStep;
            }

            drawPixel(x, y, false);
        }  
        
        bitmapGrid.update();
    }
    
}
