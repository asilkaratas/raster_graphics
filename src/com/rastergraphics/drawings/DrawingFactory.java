/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.drawings;

import com.rastergraphics.drawings.LineFactory;
import java.util.ArrayList;

/**
 *
 * @author asilkaratas
 */
public class DrawingFactory
{
    private static ArrayList<DrawingFactory> factories;
    public static ArrayList<DrawingFactory> getFactories()
    {
        if(factories == null)
        {
            factories = new ArrayList<>();
            factories.add(new LineFactory());
            factories.add(new CircleFactory());
        }
        return factories;
    }
    
    protected String name;
    protected ArrayList<Object> drawings;
    
    protected DrawingFactory(String name)
    {
        this.name = name;
        drawings = new ArrayList<>();
    }
    
    public String getName()
    {
        return name;
    }
    
    public ArrayList<Object> getDrawings()
    {
        return drawings;
    }
    
    @Override
    public String toString()
    {
        return name;
    }
}
