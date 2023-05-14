package edu.vanier.mainPackage.lens;

import edu.vanier.mainPackage.lens.Item;
import edu.vanier.mainPackage.lens.Lens;
import edu.vanier.mainPackage.lens.LensPhysics;
import edu.vanier.mainPackage.lens.Rays;
import edu.vanier.mainPackage.lens.SourceObject;
import java.text.DecimalFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author Steven
 */
public class PPLensController extends PPController {

    //decimal format
    private static DecimalFormat df = new DecimalFormat("######.##");

    private Lens lens;
    @FXML
    TextField txtAbsPos, txtFocalLength;
    @FXML
    Label labelIndex, labelRadius, labelRadiusName;
    @FXML
    Slider sliderIndex, sliderRadius;
    @FXML
    Button btnLensType;
    @FXML
    Label labelItemID;

    public PPLensController(Item item) {
        this.lens = (Lens) item;
    }

    public void initialize() {
        labelItemID.setText(lens.toString());
        updateTextFields();
        setupControls();
    }

    private void setupControls() {
        this.txtAbsPos.setOnAction(e -> {
            this.lens.move(Double.parseDouble(txtAbsPos.getText()));
            SourceObject tempSource = LensPhysics.sourceSearch();
            tempSource.getImage().update();
            tempSource.setRelPos(LensPhysics.computeRelPos(tempSource)[0]);

            updateTextFields();
        });
        this.txtFocalLength.setOnAction(e -> {
            this.lens.setFocalLength(Double.parseDouble(txtFocalLength.getText()));
            lens.updateRadius();
            LensPhysics.sourceSearch().getImage().update();
            lens.updateLensImage();
            updateTextFields();
        });
        this.sliderIndex.setOnMouseDragged(e -> {
            this.lens.setRefractionIndex(sliderIndex.getValue());
            lens.updateFocalLength();
            LensPhysics.sourceSearch().getImage().update();
            updateTextFields();
        });
        this.sliderRadius.setOnMouseDragged(e -> {
            if (lens.getLensType().equals(Lens.LENSTYPE_CONVERGENT)) {
                this.lens.setRadius(sliderRadius.getValue());
            } else {
                this.lens.setRadius(-sliderRadius.getValue());
            }

            lens.updateFocalLength();
            lens.updateLensImage();
            LensPhysics.sourceSearch().getImage().update();
            updateTextFields();
        });
        this.btnLensType.setOnAction(e -> {
            if (lens.getLensType().equals(Lens.LENSTYPE_CONVERGENT)) {
                this.lens.setFocalLength(-Math.abs(this.lens.getFocalLength()));
                this.lens.setRadius(-Math.abs(this.lens.getRadius()));
                this.lens.setLensType(Lens.LENSTYPE_DIVERGENT);
            } else {
                this.lens.setFocalLength(Math.abs(this.lens.getFocalLength()));
                this.lens.setRadius(Math.abs(this.lens.getRadius()));
                this.lens.setLensType(Lens.LENSTYPE_CONVERGENT);
            }
            lens.updateFocalLength();
            lens.updateLensImage();
            LensPhysics.sourceSearch().getImage().update();
            updateTextFields();
        });

    }

    @Override
    public void updateTextFields() {
        Rays.updateRays();

        txtAbsPos.setText(df.format(lens.getAbsPos()));
        txtFocalLength.setText(df.format(lens.getFocalLength()));
        labelIndex.setText("n = " + df.format(lens.getRefractionIndex()));
        labelRadius.setText("R = " + df.format(lens.getRadius()));
        if (lens.getLensType().equals(Lens.LENSTYPE_CONVERGENT)) {
            labelRadiusName.setText("Radius of Curvature");
            btnLensType.setTextFill(Color.valueOf("4C3FFF"));
        } else {
            btnLensType.setTextFill(Color.valueOf("FF4040"));
            labelRadiusName.setText("Radius of Curvature (-)");
        }
        btnLensType.setText(lens.getLensType());

    }
}
