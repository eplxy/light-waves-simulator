package edu.vanier.photoelectriceffect.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//@author Maesha Mahmud
 
public class MainApp extends Application {
    
    Color color;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("PhotoElectric Effect");
        
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PhotoelectricEffectMenu.fxml"));
        
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        
    }catch (Exception e){
            System.out.println("there was an exception in the program: " + e + " and " + e.getCause());
    }
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }

    private void addNodeToView(PointLight light) { throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}