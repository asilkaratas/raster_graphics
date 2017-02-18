/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.view;

import com.rastergraphics.model.AppModel;
import com.rastergraphics.model.BitmapGrid;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author asilkaratas
 */
public class GridEditorView extends StackPane
{
    private TextField widthText;
    private TextField heightText;
    private TextField pixelSizeText;
    
    public GridEditorView()
    {
        Label widthLabel = new Label("width:");
        widthText = new TextField();
        Label heightLabel = new Label("height:");
        heightText = new TextField();
        Label pixelSizeLabel = new Label("pixel size:");
        pixelSizeText = new TextField();
        
        widthText.setPrefWidth(50);
        heightText.setPrefWidth(50);
        pixelSizeText.setPrefWidth(50);
        
        Button applyButton = new Button("apply");
        applyButton.setOnAction(new ApplyButtonHandler());
        
        Button clearButton = new Button("clear");
        clearButton.setOnAction(new ClearButtonHandler());
        
        Button showMultisamplingButton = new Button("show");
        showMultisamplingButton.setOnAction(new ShowMultisamplingHandler());
        
        GridPane.setConstraints(widthLabel, 0, 0);
        GridPane.setConstraints(widthText, 1, 0);
        GridPane.setConstraints(heightLabel, 2, 0);
        GridPane.setConstraints(heightText, 3, 0);
        GridPane.setConstraints(pixelSizeLabel, 4, 0);
        GridPane.setConstraints(pixelSizeText, 5, 0);
        GridPane.setConstraints(applyButton, 6, 0);
        GridPane.setConstraints(clearButton, 7, 0);
        GridPane.setConstraints(showMultisamplingButton, 8, 0);
        
        GridPane.setHalignment(widthLabel, HPos.RIGHT);
        GridPane.setHalignment(heightLabel, HPos.RIGHT);
        GridPane.setHalignment(pixelSizeLabel, HPos.RIGHT);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(5.0);
        gridPane.setVgap(5.0);
        gridPane.getChildren().addAll(widthLabel, widthText, 
                                      heightLabel, heightText,
                                      pixelSizeLabel, pixelSizeText, 
                                      applyButton, clearButton, showMultisamplingButton);
        
        BorderedTitledPane pane = new BorderedTitledPane("resize", gridPane);
        getChildren().add(pane);
        
        upateUI();
    }
    
    private void upateUI()
    {
        BitmapGrid bitmapGrid = AppModel.getInstance().getBitmapGrid().getValue();
        widthText.setText(String.valueOf(bitmapGrid.getWidth()));
        heightText.setText(String.valueOf(bitmapGrid.getHeight()));
        pixelSizeText.setText(String.valueOf(bitmapGrid.getPixelSize()));
    }
    
    private void apply()
    {
        int width = Integer.parseInt(widthText.getText());
        int height = Integer.parseInt(heightText.getText());
        int pixelSize = Integer.parseInt(pixelSizeText.getText());
        
        if(width < 1 || height < 1 || pixelSize < 1)
            return;
        
        BitmapGrid bitmapGrid = new BitmapGrid(width, height, pixelSize);
        
        AppModel.getInstance().getBitmapGrid().setValue(bitmapGrid);
                
    }
    
    private void clear()
    {
        BitmapGrid bitmapGrid = AppModel.getInstance().getBitmapGrid().getValue();
        bitmapGrid.clear();
    }
    
    private void showMultisampling()
    {
        if(!MultiSamplingImageView.IsOpened)
        {
            MultiSamplingImageView view = new MultiSamplingImageView();
            view.show();
        }
    }
    
    private class ApplyButtonHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            apply();
        }
    }
    
    private class ClearButtonHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            clear();
        }
    }
    
    private class ShowMultisamplingHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            showMultisampling();
        }
    }
}
