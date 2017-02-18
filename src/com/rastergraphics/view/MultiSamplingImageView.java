/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.view;

import com.rastergraphics.model.AppModel;
import com.rastergraphics.model.BitmapGrid;
import com.rastergraphics.model.BitmapGridUpdateEvent;
import ij.process.ColorProcessor;
import java.awt.image.BufferedImage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;

/**
 *
 * @author asilkaratas
 */
public class MultiSamplingImageView extends Stage
{
    private ImageView imageView;
    private BitmapGrid bitmapGrid;
    
    public static boolean IsOpened = false;
    
    public MultiSamplingImageView()
    {
        super(StageStyle.UTILITY);
        
        imageView = new ImageView();
        
        StackPane pane = new StackPane(imageView);
        
        ScrollPane scrollPane = new ScrollPane(pane);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        
        StackPane root = new StackPane();
        root.getChildren().add(scrollPane);
        
        Scene scene = new Scene(root, 700, 700);
        scene.getStylesheets().add(AppModel.CSS);
        
        //AppModel.getInstance().getFilteredImage().addListener(new FilteredImageChangeHandler());

        setTitle("multisampling image");
        setScene(scene);
        
        setOnCloseRequest(new OnCloseRequestHandler());
        
        updateUI();
        
        
        AppModel.getInstance().getMultiSamplingBitmapGrid().addListener(new BitmapGridChangeHandler());
        setBitmapGrid(com.rastergraphics.model.AppModel.getInstance().getMultiSamplingBitmapGrid().getValue());
        
        IsOpened = true;
    }
    
    private void setBitmapGrid(BitmapGrid bitmapGrid)
    {
        this.bitmapGrid = bitmapGrid;
        bitmapGrid.setUpdateEventHandler(new BitmapGridUpdateEventHandler());
        
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
    
    private void updateUI()
    {
        handleFilteredImage();
    }
    
    private void handleFilteredImage()
    {
        /*
        BufferedImage bufferedImage = AppModel.getInstance().getFilteredImage().getValue();
        if(bufferedImage != null)
        {
            WritableImage fxImage = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
            SwingFXUtils.toFXImage(bufferedImage, fxImage);
        
            imageView.setImage(fxImage); 
        }
        else
        {
           imageView.setImage(null);
           handleClose();
        }
                */
    }
    
    private void handleClose()
    {
        IsOpened = false;
        close();
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
    
    private class OnCloseRequestHandler implements EventHandler<WindowEvent>
    {
        @Override
        public void handle(WindowEvent event)
        {
            handleClose();
        }
    }
}
