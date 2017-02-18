/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rastergraphics;

import com.rastergraphics.model.AppModel;
import com.rastergraphics.view.RootPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author asilkaratas
 */
public class Main extends Application
{
    
    @Override
    public void start(Stage primaryStage)
    {
        RootPane root = new RootPane();
        
        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add(AppModel.CSS);
        
        primaryStage.setTitle("Raster Graphics");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
