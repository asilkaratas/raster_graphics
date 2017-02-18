/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.model;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

/**
 *
 * @author asilkaratas
 */
public class BitmapGrid 
{
    private int width;
    private int height;
    private int pixelSize;
    private int borderSize;
    private int imageWidth;
    private int imageHeight;
    private int pixelHeight;
    private int pixelCount;
    
    private int[] pixels;
    
    public BitmapGrid(int width, int height, int pixelSize)
    {
        this.width = width;
        this.height = height;
        this.pixelSize = pixelSize;
        this.borderSize = 1;
        this.pixelHeight = pixelSize + borderSize;
        this.imageWidth = width * pixelHeight + borderSize;
        this.imageHeight = height * pixelHeight + borderSize;
        this.pixelCount = imageWidth * imageHeight;
        this.pixels = new int[pixelCount];
        
        clear();
    }
    
    
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    
    public int getPixelSize()
    {
        return pixelSize;
    }
    
    public void clear()
    {
        drawBg();
        drawGrid();
        update();
    }
    
    private void drawBg()
    {
        int white = (255<<16)+(255<<8)+255;
        for(int i = 0; i < pixelCount; ++i)
        {
            pixels[i] = white;
        }
    }
    
    private void drawGrid()
    {
        int index = 0;
        boolean isBorder = true;
        int w = 0;
        int h = 0;
        while(index < pixelCount)
        {
            pixels[index] = 0;
            if(isBorder)
            {
                index ++;
                if(w != imageWidth - 1)
                {
                    w++;
                }
                else
                {
                    isBorder= false;
                    w = 0;
                    h ++;
                }
            }
            else
            {
                if(w != width)
                {
                    index += pixelHeight;
                    w++;
                }
                else
                {
                    index ++;
                    isBorder= false;
                    w=0;
                    h++;
                    
                    if(h == pixelHeight)
                    {
                        h = 0;
                        isBorder = true;
                    }
                }
            }
        }
    }
    
    public int[] getPixels()
    {
        return pixels;
    }
    
    public int getImageWidth()
    {
        return imageWidth;
    }
    
    public int getImageHeight()
    {
        return imageHeight;
    }
    
    public GridPoint getPoint(int x, int y)
    {
        int col = x/pixelHeight;
        int row = y/pixelHeight;
        
        //System.out.println("getPixelIndex:x:" + x  + " " + imageWidth + " y:" + y + " " + imageHeight);
        
        if(x >= imageWidth-1)
            col --;
        if(y >= imageHeight-1)
            row --;
        
        GridPoint point = new GridPoint(col, row);
        return point;
    }
    
    public int getPixelIndex(int x, int y)
    {
        int col = x/pixelHeight;
        int row = y/pixelHeight;
        
        //System.out.println("getPixelIndex:x:" + x  + " " + imageWidth + " y:" + y + " " + imageHeight);
        
        if(x >= imageWidth-1)
            col --;
        if(y >= imageHeight-1)
            row --;
        
        //System.out.println("row:" + row  + " " + col + " x:" + x + " " + y);
        int index = row * width + col;
        return index;
    }
    
    public void setPixelColor(int x, int y)
    {
        setPixelColor(x, y, 2555);
    }
    
    public int getPixelColor(int x, int y)
    {
        int imageX = x * pixelHeight;
        int imageY = y * pixelHeight;
        
        //System.out.println("setPixelColor:" + row  + " " + col + " x:" + x + " " + y);
        
        int startX = imageX + 1;
        int startY = imageY + 1;
        
        int xPos = startX;
        int yPos = startY;
        
        int pixelIndex = yPos * imageWidth + xPos;
        
        int color = 0;
        
        if(pixelIndex > 0 && pixelIndex < pixelCount)
            color = pixels[pixelIndex];
        
        return color;
    }
    
    public void setPixelColor(int x, int y, int color)
    {
        int imageX = x * pixelHeight;
        int imageY = y * pixelHeight;
        
        //System.out.println("setPixelColor:" + row  + " " + col + " x:" + x + " " + y);
        
        int startX = imageX + 1;
        int startY = imageY + 1;
        
        int xPos = startX;
        int yPos = startY;
        for(int i = 0; i < pixelSize; ++i)
        {
            xPos = startX; 
            for(int j = 0; j < pixelSize; ++j)
            {
                int pixelIndex = yPos * imageWidth + xPos;
                if(pixelIndex > 0 && pixelIndex < pixelCount)
                    pixels[pixelIndex] = color;
                xPos++;
            }
            yPos ++;
        }
    }
    
    public void setPixelColorByIndex(int index, int color)
    {
        int y = index/width;
        int x = index%width;
        
        setPixelColor(x, y, color);
    }
    
    public void setPixelColor(GridPoint point)
    {
        setPixelColor(point.getX(), point.getY(), 2555);
    }
    
    public void setPixelColor(GridPoint point, int color)
    {
        setPixelColor(point.getX(), point.getY(), color);
    }
    
    public GridPoint getPoint(int index)
    {
        int x = index % width;
        int y = index / width;
        
        GridPoint point = new GridPoint(x, y);
        
        return point;
    }
    
    public int getMultiSampledColor(int x, int y)
    {
        int xPos = x;
        int yPos = y;
        
        int color = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        
        //System.out.println("sampling:" + x + " " + y);
        for(int i = 0; i < 2; i++)
        {
            xPos = x;
            for(int j = 0; j < 2; j++)
            {
                color = getPixelColor(xPos, yPos);
                red += (color&0xff0000)>>16;
                green += (color&0xff00)>>8;
                blue += color&0xff;

                //System.out.println("Color:" + color);

                xPos ++;
            }

            yPos ++;
        }
        
        red /= 4;
        green /= 4;
        blue /= 4;
        
        color = (red<<16)+(green<<8)+blue;
        return color;
    }
    
    private EventHandler<BitmapGridUpdateEvent> updateEventHandler = null;
    public void setUpdateEventHandler(EventHandler<BitmapGridUpdateEvent> updateEventHandler)
    {
        this.updateEventHandler = updateEventHandler;
    }
    
    public void update()
    {
        if(updateEventHandler != null)
        {
            updateEventHandler.handle(new BitmapGridUpdateEvent());
        }
    }
    
    
}
