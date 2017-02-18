/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.drawings;

import com.rastergraphics.drawings.circle.MidpointCircle;
import com.rastergraphics.drawings.circle.MidpointCircleAdd;
import com.rastergraphics.drawings.circle.MidpointCircleInternet;
import java.util.ArrayList;

/**
 *
 * @author asilkaratas
 */
public class CircleFactory extends DrawingFactory
{
    public CircleFactory()
    {
        super("circle");
        
        drawings = new ArrayList<>();
        drawings.add(new MidpointCircle());
        drawings.add(new MidpointCircleAdd());
        drawings.add(new MidpointCircleInternet());
    }
    
}
