/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.drawings;

import com.rastergraphics.drawings.Drawing;
import com.rastergraphics.drawings.line.MidpointLine;
import com.rastergraphics.drawings.line.SymmetricLine;
import java.util.ArrayList;

/**
 *
 * @author asilkaratas
 */
public class LineFactory extends DrawingFactory
{
    public LineFactory()
    {
        super("line");
        
        drawings = new ArrayList<>();
        drawings.add(new MidpointLine());
        drawings.add(new SymmetricLine());
    }
    
}
