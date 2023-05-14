package edu.vanier.mainPackage.lens;

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
    private static final DecimalFormat df = new DecimalFormat("######.##");

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

    /**
     * Update the textfields
     */
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
