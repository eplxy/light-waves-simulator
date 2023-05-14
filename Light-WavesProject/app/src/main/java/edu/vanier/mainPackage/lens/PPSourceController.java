package edu.vanier.mainPackage.lens;

import java.text.DecimalFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 *
 * @author Steven
 */
public class PPSourceController extends PPController {

    //decimal format
    private static final DecimalFormat df = new DecimalFormat("######.##");
    private SourceObject source;
    @FXML
    TextField txtAbsPos, txtSize;
    
    @FXML
    Label labelItemID;
    
    @FXML
    MenuButton choiceImage;

    public PPSourceController(Item item) {
        this.source = (SourceObject) item;

    }

    public void initialize() {
        labelItemID.setText(source.toString());
        setupTextFields();
        updateTextFields();
        setupChoiceImage();
    }

    /**
     * Setup the source image chooser
     */
    private void setupChoiceImage() {
        
        MenuItem defaultCandle = new MenuItem("candle.png");
        defaultCandle.setOnAction(e ->{
            ResourceManager.resetSourceImage(source);
            choiceImage.setText("candle.png");
        });
        
        choiceImage.getItems().add(defaultCandle);

        if (ResourceManager.getPathList() != null) {

            for (String pathName : ResourceManager.getPathList()) {
                MenuItem menuItem = new MenuItem(pathName);
                choiceImage.getItems().add(menuItem);
                menuItem.setOnAction(e -> {
                    ResourceManager.changeSourceImage(source, pathName);
                    choiceImage.setText(pathName);
                });
            }
        }

    }

    /**
     * Setup the editable textfields
     */
    private void setupTextFields() {
        this.txtAbsPos.setOnAction(e -> {
            this.source.move(Double.parseDouble(txtAbsPos.getText()));
            this.source.setRelPos(LensPhysics.computeRelPos(source)[0]);
            LensPhysics.sourceSearch().getImage().update();
            updateTextFields();
        });

        this.txtSize.setOnAction(e -> {
            this.source.setSize(Double.parseDouble(txtSize.getText()));
            LensPhysics.sourceSearch().getImage().update();
            this.source.scaleNodeToSize();
            updateTextFields();

        });
    }

    /**
     * Updates the textfields
     */
    @Override
    public void updateTextFields() {
        Rays.updateRays();
        txtAbsPos.setText(df.format(source.getAbsPos()));
        txtSize.setText(df.format(source.getSize()));
    }
}
