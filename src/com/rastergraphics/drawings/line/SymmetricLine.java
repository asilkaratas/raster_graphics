package com.rastergraphics.drawings.line;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.rastergraphics.drawings.Drawing;

/**
 *
 * @author asilkaratas
 */
public class SymmetricLine extends Drawing
{
    public SymmetricLine()
    {
        super("symmetric");
    }
    
    @Override
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
        
        int xf = x1;
        int yf = y1;
        int xb = x2;
        int yb = y2;
        
        int yStep = (y1 < y2) ? 1 : -1;
        
        if(x1 > x2)
        {
            xf = x2;
            yf = y2;
            xb = x1;
            yb = y1;
            yStep = -yStep;
        }
        
        drawPixel(xf, yf, true);
        drawPixel(xb, yb, true);
        
        while(xf < xb)
        {
            ++xf; --xb;
            if(d < 0)
            {
                d += dE;
            }
            else
            {
                d += dNE;
                yf += yStep;
                yb -= yStep;
            }
            
            drawPixel(xf, yf, true);
            drawPixel(xb, yb, true);
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
        
        int xf = x1;
        int yf = y1;
        int xb = x2;
        int yb = y2;
        
        int xStep = (x1 < x2) ? 1 : -1;
        
        if(y1 > y2)
        {
            xf = x2;
            yf = y2;
            xb = x1;
            yb = y1;
            xStep = -xStep;
        }
        
        drawPixel(xf, yf, false);
        drawPixel(xb, yb, false);
        
        //System.out.println("drawLineY:yf:" + yf + " " + yb);
        
        while(yf < yb)
        {
            ++yf; --yb;
            if(d < 0)
            {
                d += dE;
            }
            else
            {
                d += dNE;
                xf += xStep;
                xb -= xStep;
            }
            
            drawPixel(xf, yf, false);
            drawPixel(xb, yb, false);
        }
        
        bitmapGrid.update();
    }
}
