package edu.vanier.mainPackage;

import java.io.File;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.Style;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author someo
 */
public class GeneralSettingsController {

    @FXML
    BorderPane generalSettingsPane;
    
    @FXML
    Label labelCurrentPath;
    
    @FXML
    Button btnSearch, btnMainMenu;
    
    @FXML
    RadioButton radioBtnLight, radioBtnDark;
            

    private final Stage primaryStage;
    public static String currentImageFolderPath = "";
    private static String selectedTheme;


    public GeneralSettingsController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize() throws IOException {
        ToggleGroup themeTG = new ToggleGroup();
        radioBtnLight.setToggleGroup(themeTG);
        radioBtnDark.setToggleGroup(themeTG);
        themeTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
                RadioButton rb = (RadioButton) themeTG.getSelectedToggle();
                if (rb != null) {
                    selectedTheme = rb.getText();
                    changeTheme(selectedTheme);
                }
            }
        });
        radioBtnLight.setSelected(true);
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
    
    public void changeTheme(String selectedView) {
        if (selectedView.equals("Dark")) {
            
        } else {
            
        }
    }



}
