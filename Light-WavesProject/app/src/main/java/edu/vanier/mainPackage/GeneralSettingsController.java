package edu.vanier.mainPackage;

import java.io.File;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.Style;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
    public static String selectedTheme = "Dark";

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
                    changeTheme(primaryStage.getScene());
                }
            }
        });
        radioBtnLight.setSelected(selectedTheme.equalsIgnoreCase("Light"));
        radioBtnDark.setSelected(selectedTheme.equalsIgnoreCase("Dark"));

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        labelCurrentPath.setText(currentImageFolderPath);
        btnSearch.setOnAction(e -> {
            File file = directoryChooser.showDialog(primaryStage);
            if (file != null) {
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

    public static void changeTheme(Scene scene) {
        if (selectedTheme.equals("Dark")) {
            scene.getStylesheets().add(GeneralSettingsController.class.getResource("/fxml/moderna_dark.css").toString());
        } else {
            scene.getStylesheets().add(GeneralSettingsController.class.getResource("/fxml/moderna_light.css").toString());
        }
    }

}
