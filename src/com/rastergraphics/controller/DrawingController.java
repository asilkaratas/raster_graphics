/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.controller;

import com.rastergraphics.drawings.line.SymmetricLine;
import com.rastergraphics.drawings.Drawing;
import com.rastergraphics.model.AppModel;
import com.rastergraphics.model.BitmapGrid;
import com.rastergraphics.model.GridPoint;
import com.rastergraphics.pencils.Pencil;
import com.rastergraphics.pencils.PencilFactory;

/**
 *
 * @author asilkaratas
 */
public class DrawingController
{
    private GridPoint startPoint;
    private GridPoint endPoint;
    
    private Drawing drawing;
    
    public DrawingController()
    {
       drawing = new SymmetricLine();
       reset(); 
    }
    
    public void reset()
    {
        startPoint = null;
        endPoint = null;
    }
    
    public void setPoint(GridPoint point)
    {
        if(point == null) return;
        
        BitmapGrid bitmapGrid = getBitmapGrid();
        
        if(startPoint == null)
        {
            startPoint = point;
            
        }
        else
        {
            endPoint = point;
        
            draw();
            reset();
        }
    }
    
    private void draw()
    {
        BitmapGrid multisampleBitmapGrid = getMultisampledBitmapGrid();
        BitmapGrid bitmapGrid = getBitmapGrid();
        Drawing drawing = getDrawing();
        Pencil pencil = getPencil();
        
        Pencil doubledPencil = PencilFactory.getInstance().getDoubledPencil(pencil);
        
        drawing.setBitmapGrid(multisampleBitmapGrid);
        drawing.setPencil(doubledPencil);
        
        if(drawing != null && pencil != null)
        {
            //drawing.draw(startPoint, endPoint);
            
            multisampleBitmapGrid.clear();
            
            GridPoint doubledStartPoint = startPoint.getDoubled();
            GridPoint doubledEndPoint = endPoint.getDoubled();
            
            drawing.draw(doubledStartPoint, doubledEndPoint);
            
            
            int w = multisampleBitmapGrid.getWidth();
            int h = multisampleBitmapGrid.getHeight();
            
            int color = 0;
            for(int y = 0; y < h; y+=2)
            {
                for(int x = 0; x < w; x+=2)
                {
                    color = multisampleBitmapGrid.getMultiSampledColor(x, y);
                    bitmapGrid.setPixelColor(x/2, y/2, color);
                }
            }
            
            bitmapGrid.update();
                   
        }
    }
    
    private Pencil getPencil()
    {
        return AppModel.getInstance().getPencil().getValue();
    }
    
    private Drawing getDrawing()
    {
        return AppModel.getInstance().getDrawing().getValue();
    }
    
    private BitmapGrid getMultisampledBitmapGrid()
    {
        return AppModel.getInstance().getMultiSamplingBitmapGrid().getValue();
    }
    
    private BitmapGrid getBitmapGrid()
    {
        return AppModel.getInstance().getBitmapGrid().getValue();
    }
}
