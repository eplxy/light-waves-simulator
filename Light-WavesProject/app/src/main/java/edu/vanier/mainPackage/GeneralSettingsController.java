package edu.vanier.mainPackage;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author someo
 */
public class GeneralSettingsController {

    @FXML
    Label labelCurrentPath;
    @FXML
    Button btnSearch, btnMainMenu;

    private final Stage primaryStage;
    public static String currentImageFolderPath = "";

    public GeneralSettingsController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize() throws IOException {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        labelCurrentPath.setText(currentImageFolderPath);
        btnSearch.setOnAction(e->{
            File file = directoryChooser.showDialog(primaryStage);
            if(file !=null){
                currentImageFolderPath = file.getPath();
                labelCurrentPath.setText(currentImageFolderPath);
            }
        });
        
        btnMainMenu.setOnAction(e -> {
            MainApp mainApp = new MainApp();
            try {
                mainApp.start(primaryStage);
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        });
        
        
    }


}
