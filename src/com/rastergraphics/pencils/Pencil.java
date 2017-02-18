/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.pencils;

/**
 *
 * @author asilkaratas
 */
public class Pencil
{
    private String name;
    private int[] matrix;
    private int color;
    private int size;
    private int halfSize;
    
    public static int BLUE = (0<<16)+(0<<8)+255;
    public static int RED = (255<<16)+(0<<8)+0;
    public static int YELLOW = (0<<16)+(255<<8)+0;
    
    public Pencil(String name, int[] matrix, int color)
    {
        this.name = name;
        this.matrix = matrix;
        this.color = color;
        
        calculatePencilSize();
    }
    
    public Pencil(String name, int[] matrix)
    {
        this.name = name;
        this.matrix = matrix;
        this.color = BLUE;
        
        calculatePencilSize();
    }
    
    private void calculatePencilSize()
    {
        size = (int)Math.sqrt(matrix.length);
        halfSize = (size -1)/2;
    }
    
    public int getHalfsize()
    {
        return halfSize;
    }
    
    public int getColor()
    {
        return color;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public int[] getMatrix()
    {
        return matrix;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
