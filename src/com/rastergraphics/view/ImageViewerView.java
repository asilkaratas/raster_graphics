/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.view;

import com.rastergraphics.controller.DrawingController;
import com.rastergraphics.model.AppModel;
import com.rastergraphics.model.BitmapGrid;
import com.rastergraphics.model.BitmapGridUpdateEvent;
import com.rastergraphics.model.GridPoint;
import ij.process.ColorProcessor;
import java.awt.image.BufferedImage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author asilkaratas
 */
public class ImageViewerView extends StackPane
{
    private ImageView imageView;
    private BitmapGrid bitmapGrid;
    private DrawingController drawingController;
    
    public ImageViewerView()
    {
        drawingController = new DrawingController();
        
        GridEditorView gridEditorView = new GridEditorView();
        
        
        imageView = new ImageView();
        imageView.addEventFilter(MouseEvent.MOUSE_PRESSED, new MousePressedListener());
        
        StackPane imageViewPane = new StackPane();
        imageViewPane.getChildren().add(imageView);
        
        VBox imageViewerBox = new VBox();
        imageViewerBox.getChildren().addAll(gridEditorView, imageViewPane);
        
        BorderedTitledPane imageViewerPane = new BorderedTitledPane("grid", imageViewerBox);
        getChildren().add(imageViewerPane);
        
        AppModel.getInstance().getBitmapGrid().addListener(new BitmapGridChangeHandler());
        setBitmapGrid(AppModel.getInstance().getBitmapGrid().getValue());
    }
    
    private void setBitmapGrid(BitmapGrid bitmapGrid)
    {
        this.bitmapGrid = bitmapGrid;
        bitmapGrid.setUpdateEventHandler(new BitmapGridUpdateEventHandler());
        
        BitmapGrid multisampling = new BitmapGrid(bitmapGrid.getWidth()*2, bitmapGrid.getHeight()*2, bitmapGrid.getPixelSize()/2);
        AppModel.getInstance().getMultiSamplingBitmapGrid().setValue(multisampling);
        
        updateImage();
    }
    
    private void updateImage()
    {
        ColorProcessor colorProcessor = new ColorProcessor(bitmapGrid.getImageWidth(), bitmapGrid.getImageHeight(), bitmapGrid.getPixels());
        BufferedImage bufferedImage = colorProcessor.getBufferedImage();
        WritableImage fxImage = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
        SwingFXUtils.toFXImage(bufferedImage, fxImage);
        
        imageView.setImage(fxImage);
    }
    
    private class MousePressedListener implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event)
        {
            GridPoint point = bitmapGrid.getPoint((int)event.getX(), (int)event.getY());
            drawingController.setPoint(point);
        }
    }
    
    private class BitmapGridChangeHandler implements ChangeListener<BitmapGrid>   
    {
        @Override
        public void changed(ObservableValue<? extends BitmapGrid> observable, BitmapGrid oldValue, BitmapGrid newValue)
        {
            if(bitmapGrid != null)
            {
                bitmapGrid.setUpdateEventHandler(null);
               
            }
            
            bitmapGrid = newValue;
            
            if(bitmapGrid != null)
            {
                
                setBitmapGrid(bitmapGrid);
            }
            
        }
    }
    
    private class BitmapGridUpdateEventHandler implements EventHandler<BitmapGridUpdateEvent>
    {
        @Override
        public void handle(BitmapGridUpdateEvent event)
        {
            updateImage();
        }
    }
}
