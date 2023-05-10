package edu.vanier.mainPackage.lens.propertyPanes;

import edu.vanier.mainPackage.lens.ImageObject;
import edu.vanier.mainPackage.lens.Item;
import java.text.DecimalFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Steven
 */
public class PPImageController extends PPController {

    //decimal format
    private static DecimalFormat df = new DecimalFormat("######.##");

    private ImageObject img;
    @FXML
    TextField txtAbsPos, txtSize, txtSource, txtMagnification, txtInversion, txtImgType;
    @FXML
    Label labelItemID;

    public PPImageController(Item item) {
        this.img = (ImageObject) item;
    }

    public void initialize() {
        labelItemID.setText(img.toString());
        updateTextFields();
    }
    
    

    @Override
    public void updateTextFields() {

        txtAbsPos.setText(df.format(img.getAbsPos()));
        txtSize.setText(df.format(img.getSize()));
        txtSource.setText(img.getSource().toString());
        txtMagnification.setText(df.format(img.getMagnification()));
        txtInversion.setText(Boolean.toString(img.isInverted()));
        txtImgType.setText(img.getImgType());

    }
}
