/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.model;

import com.rastergraphics.drawings.Drawing;
import com.rastergraphics.pencils.Pencil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author asilkaratas
 */
public class AppModel
{
    public static final String CSS = "assets/css/style.css";
    
    private ObjectProperty<BitmapGrid> bitmapGrid;
    private ObjectProperty<BitmapGrid> multiSamplingBitmapGrid;
    private ObjectProperty<Drawing> drawing;
    private ObjectProperty<Pencil> pencil;
    
    private static AppModel instance;
    public static AppModel getInstance()
    {
        if(instance == null)
        {
            instance = new AppModel();
        }

        return instance;
    }
    
    private AppModel()
    {
        BitmapGrid grid = new BitmapGrid(50, 50, 10);
        BitmapGrid multiSamplingGrid = new BitmapGrid(100, 100, 5);
        
        bitmapGrid = new SimpleObjectProperty<>();
        bitmapGrid.setValue(grid);
        
        multiSamplingBitmapGrid = new SimpleObjectProperty<>();
        multiSamplingBitmapGrid.setValue(multiSamplingGrid);
        
        drawing = new SimpleObjectProperty<>();
        pencil = new SimpleObjectProperty<>();
        //drawing.set(new NaiveLine());
    }
    
    public ObjectProperty<BitmapGrid> getBitmapGrid()
    {
        return bitmapGrid;
    }
    
    public ObjectProperty<BitmapGrid> getMultiSamplingBitmapGrid()
    {
        return multiSamplingBitmapGrid;
    }
    
    public ObjectProperty<Drawing> getDrawing()
    {
        return drawing;
    }
    
    public ObjectProperty<Pencil> getPencil()
    {
        return pencil;
    }
    
    
    public void clear()
    {
       
    }
    
    
}
