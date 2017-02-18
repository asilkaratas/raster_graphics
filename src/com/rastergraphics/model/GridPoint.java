/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.model;

/**
 *
 * @author asilkaratas
 */
public class GridPoint
{
    private int x;
    private int y;
    
    public GridPoint(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public GridPoint()
    {
        x = 0;
        y = 0;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public int getY()
    {
        return y;
    }
    
    public GridPoint getDoubled()
    {
        return new GridPoint(x*2, y*2);
    }
}
