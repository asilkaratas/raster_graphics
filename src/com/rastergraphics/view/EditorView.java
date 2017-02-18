/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics.view;

import com.rastergraphics.drawings.DrawingFactory;
import com.rastergraphics.model.AppModel;
import com.rastergraphics.drawings.Drawing;
import com.rastergraphics.pencils.Pencil;
import com.rastergraphics.pencils.PencilFactory;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author asilkaratas
 */
public class EditorView extends StackPane
{
    private ComboBox chooseDrawingComboBox;
    private ComboBox choosePencilComboBox;
    
    public EditorView()
    {
        VBox drawTypeBox = new VBox();
        drawTypeBox.setPrefWidth(250.0);
        drawTypeBox.setSpacing(5.0);
        
        ToggleGroup drawingTypeGroup = new ToggleGroup();
        drawingTypeGroup.selectedToggleProperty().addListener(new DrawingTypeChangeListener());
        
        
        ArrayList<DrawingFactory> drawingFactories = DrawingFactory.getFactories();
        for(DrawingFactory drawingFactory : drawingFactories)
        {
            RadioButton drawingTypeButton = new RadioButton(drawingFactory.toString());
            drawingTypeButton.setUserData(drawingFactory);
            drawingTypeButton.setToggleGroup(drawingTypeGroup);
            
            drawTypeBox.getChildren().add(drawingTypeButton);
        }
        
        BorderedTitledPane drawTypePane = new BorderedTitledPane("draw type", drawTypeBox);
        
        //chooseDrawingComboBox
        chooseDrawingComboBox = new ComboBox();
        chooseDrawingComboBox.valueProperty().addListener(new DrawingChangeListener());
        
        VBox chooseDrawingBox = new VBox();
        chooseDrawingBox.setPrefWidth(250.0);
        chooseDrawingBox.getChildren().addAll(chooseDrawingComboBox);
        
        BorderedTitledPane chooseDrawingPane = new BorderedTitledPane("choose drawing", chooseDrawingBox);
        
        
        //pencil
        
        choosePencilComboBox = new ComboBox();
        choosePencilComboBox.getItems().addAll(PencilFactory.getInstance().getPencils());
        choosePencilComboBox.valueProperty().addListener(new PencilChangeListener());
        choosePencilComboBox.getSelectionModel().select(0);
        
        VBox choosePencilBox = new VBox();
        choosePencilBox.setPrefWidth(250.0);
        choosePencilBox.getChildren().addAll(choosePencilComboBox);
        
        BorderedTitledPane choosePencilPane = new BorderedTitledPane("choose pencil", choosePencilBox);
        
        
        //editor
        VBox editorBox = new VBox();
        editorBox.getChildren().addAll(drawTypePane, chooseDrawingPane, choosePencilPane);
        
        BorderedTitledPane filtersPane = new BorderedTitledPane("editor", editorBox);
        getChildren().add(filtersPane);
        
        RadioButton selectedButton = (RadioButton)drawTypeBox.getChildren().get(0);
        selectedButton.setSelected(true);
        
        //filterVBox = new VBox();
        //BorderedTitledPane filterPane = new BorderedTitledPane("filter", filterVBox);
    }
    
    private void setDrawingFactory(DrawingFactory filterFactory)
    {
        ArrayList<Object> drawings = filterFactory.getDrawings();
        
        chooseDrawingComboBox.getItems().clear();
        chooseDrawingComboBox.getItems().addAll(drawings);
        chooseDrawingComboBox.getSelectionModel().select(0);
    }
    
    private class DrawingTypeChangeListener implements ChangeListener<Toggle>
    {
        @Override
        public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
        {
            DrawingFactory drawingFactory = (DrawingFactory)newValue.getUserData();
            setDrawingFactory(drawingFactory);       
            
        }
    }
    
    private class DrawingChangeListener implements ChangeListener<Object>
    {
        @Override
        public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue)
        {
            Drawing drawing = (Drawing)newValue;
            AppModel.getInstance().getDrawing().setValue(drawing);
        }
    }
    
    private class PencilChangeListener implements ChangeListener<Object>
    {
        @Override
        public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue)
        {
            Pencil pencil = (Pencil)newValue;
            AppModel.getInstance().getPencil().setValue(pencil);
        }
    }
}
