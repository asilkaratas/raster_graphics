/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.pencils;

import com.rastergraphics.drawings.*;
import com.rastergraphics.drawings.LineFactory;
import com.rastergraphics.model.AppModel;
import java.util.ArrayList;

/**
 *
 * @author asilkaratas
 */
public class PencilFactory
{
    private ArrayList<Pencil> pencils;
    
    private static PencilFactory instance;
    public static PencilFactory getInstance()
    {
        if(instance == null)
        {
            instance = new PencilFactory();
        }

        return instance;
    }
    
    public ArrayList<Pencil> getPencils()
    {
        return pencils;
    }
    
    private PencilFactory()
    {
        pencils = new ArrayList<>();
        pencils.add(createPencil1());
        pencils.add(createPencil3());
        pencils.add(createPencil5());
        pencils.add(createPencil7());
    }
    
    private Pencil createPencil1()
    {
        int[] matrix = {1};
        
        Pencil pencil = new Pencil("1", matrix);
        return pencil;
    }
    
    private Pencil createPencil3()
    {
        int[] matrix = {
            0, 1, 0,
            1, 1, 1,
            0, 1, 0
        };
        
        Pencil pencil = new Pencil("3", matrix);
        return pencil;
    }
    
    private Pencil createPencil5()
    {
        int[] matrix = {
            0, 0, 1, 0, 0,
            0, 1, 1, 1, 0,
            1, 1, 1, 1, 1,
            0, 1, 1, 1, 0,
            0, 0, 1, 0, 0
        };
        
        Pencil pencil = new Pencil("5", matrix);
        return pencil;
    }
    
    private Pencil createPencil7()
    {
        int[] matrix = {
            0, 0, 1, 1, 1, 0, 0,
            0, 1, 1, 1, 1, 1, 0,
            1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1,
            0, 1, 1, 1, 1, 1, 0,
            0, 0, 1, 1, 1, 0, 0
        };
        
        Pencil pencil = new Pencil("7", matrix);
        return pencil;
    }
    
    public Pencil getDoubledPencil(Pencil pencil)
    {   
        int index = pencils.indexOf(pencil);
        if(index < pencils.size() - 1)
        {
            index ++;
        }
        
        Pencil doubledPencil = pencils.get(index);
        return doubledPencil;
    }
            
}
