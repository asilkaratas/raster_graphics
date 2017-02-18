/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.view;

import com.rastergraphics.model.BitmapGrid;
import com.rastergraphics.controller.DrawingController;
import com.rastergraphics.model.BitmapGridUpdateEvent;
import com.rastergraphics.model.GridPoint;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 *
 * @author asilkaratas
 */
public class RootPane extends StackPane
{
    public RootPane()
    {
        ImageViewerView imageViewerView = new ImageViewerView();
        EditorView editorView = new EditorView();
        
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(imageViewerView, editorView);
        splitPane.setDividerPositions(.5, .5);
        
        getChildren().add(splitPane);
    }
    
    
}
