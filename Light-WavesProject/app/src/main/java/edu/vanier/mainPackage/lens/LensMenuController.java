package edu.vanier.mainPackage.lens;

import edu.vanier.mainPackage.MainApp;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Stylesheet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author stella
 */
public class LensMenuController {

    LensMain lensMain;
    Stage primaryStage;
    Button ctrlPaneOpenBtn;
    @FXML
    TextField txtP, txtQ;

    @FXML
    Button btnToggleRays, btnToggleLabels, btnToggleLensLine;

    @FXML
    ImageView homeIcon, closeCtrlIcon, bgColorIcon, helpIcon;

    @FXML
    BorderPane borderPane;

    @FXML
    Line principalAxis, verticalLensLine;

    @FXML
    Pane animationPane, controlPane, itemPane, propertyPane, colorPickerPane, helpPane;

    @FXML
    ColorPicker bgColorPicker;

    @FXML
    Label labelHelp;

    @FXML
    HBox itemBox;

    @FXML
    AnchorPane midAnchorPane;

    @FXML
    ListView itemListView;

    Pane currentParameterPane;

    public static LensMenuController currentLMC;

    public static PPController currentPPController;

    public Item selectedItem;

    public LensMenuController(Stage stage, LensMain lensMain) {
        this.primaryStage = stage;
        this.lensMain = lensMain;

    }

    public void initialize() {
        Item.setLmc(this);
        ItemLabel.setLmc(this);
        currentLMC = this;

        this.toggleRaysSetup();
        this.toggleLabelSetup();
        this.setupPQ();
        this.lensLineSetup();
        this.ctrlPaneOpenBtnSetup();
        this.homeIconSetup();
        this.closeCtrlIconSetup();
        this.colorPickSetup();
        this.helpIconSetup();

        ctrlPaneOpenBtn = new Button();

        itemListView.setOnMouseClicked(e -> {
            itemListViewHandle();
        });

    }

    
    /**
     * Setup help icon text and hover over.
     */
    private void helpIconSetup() {
        labelHelp.setText(
                "This is a simulation on geometric optics."
                + "\nLight reflected off of objects and into thin lenses follow trajectories defined by the focal lengths of lenses."
                + "\nDepending on the positioning of an object relative to a lens, its image can vary in size, inversion, and position."
                + "\nAn image can be either real or virtual, given by whether or not the principal rays intersect before or after the lens."
                + "\n"
                + "\nClick and drag either the lens or the source object to adjust their position."
                + "\nSelect and item in the lower panel's list to observe and edit their properties."
                + "\nThe sprite used for the source object (by default, a candle) can be imported through the main menu settings."
                + "\nInput number values into the 'p' or 'q' textboxes to edit relative object and image positions respectively."
                + "\nVarious toggleable settings are also available, feel free to try them out."
                + "\nClick on the pallette button in the top right corner to change the background image."
        );

        helpIcon.setOnMouseEntered(e -> {

            helpPane.toFront();
            helpPane.setVisible(true);
            helpPane.toFront();
            Rays.clearRays();
        });
        helpIcon.setOnMouseExited(e -> {
            helpPane.setVisible(false);
            Rays.generateRays();
        });
    }

    /**
     * Setup toggle rays button.
     */
    private void toggleRaysSetup() {
        btnToggleRays.setOnAction(e -> {
            Rays.toggleRays();
            if (Rays.visible) {
                btnToggleRays.setText("Hide Rays");
            } else {
                btnToggleRays.setText("Show Rays");

            }
        });

    }

    /**
     * Setup toggle labels button.
     */
    private void toggleLabelSetup() {
        btnToggleLabels.setOnAction(e -> {
            ItemLabel.toggleAllLabelVisibility();
            if (ItemLabel.allVisible) {
                btnToggleLabels.setText("Show All Labels");
            } else {
                btnToggleLabels.setText("Hide All Labels");
            }

        });
    }

    /**
     * Setup toggle lens line button.
     */
    private void lensLineSetup() {

        btnToggleLensLine.setOnAction(e -> {
            if (verticalLensLine.isVisible()) {
                btnToggleLensLine.setText("Show Lens Line");
            } else {
                btnToggleLensLine.setText("Hide Lens Line");
            }
            verticalLensLine.setVisible(!verticalLensLine.isVisible());
        });

    }

    /**
     * Setup list view for items to open associated parameter panes.
     */
    public void itemListViewHandle() {
        selectedItem = (Item) itemListView.getSelectionModel().getSelectedItem();
        highlight(selectedItem);

        try {
            createParametersPane(selectedItem);
        } catch (IOException e) {

        }

    }

    /**
     * Update the list view based on the list of items.
     */
    public void itemListViewUpdate() {
        ObservableList<Item> obsItemList = FXCollections.observableArrayList();
        for (int i = 0; i < Item.getItemList().size(); i++) {
            obsItemList.add(Item.getItemList().get(i + 1));
        }
        itemListView.setItems(obsItemList);
    }

    /**
     * Setup the button to open the control pane that shows up when the control pane is closed.
     */
    private void ctrlPaneOpenBtnSetup() {
        ctrlPaneOpenBtn = new Button("Show");
        ctrlPaneOpenBtn.setVisible(true);

        ctrlPaneOpenBtn.setLayoutX(675);
        ctrlPaneOpenBtn.setLayoutY(800);
        animationPane.getChildren().add(ctrlPaneOpenBtn);

        ctrlPaneOpenBtn.setOnAction(e -> {
            controlPane.setVisible(true);
        });
    }

    /**
     * Setup the p and q textfields for relative position adjustments.
     */
    private void setupPQ() {

        txtP.setOnAction(e -> {
            if (Double.parseDouble(txtP.getText()) <= 0) {
                txtP.setText("error!");
            } else {
                SourceObject s = LensPhysics.sourceSearch();
                double p = Double.parseDouble(txtP.getText());

                s.move(LensPhysics.lensSearch().getAbsPos() - p);
                s.getImage().update();
                Rays.updateRays();
                currentPPController.updateTextFields();
            }
        });

        txtQ.setOnAction(e -> {

            if (LensPhysics.lensSearch().getLensType().equals(Lens.LENSTYPE_CONVERGENT)) {
                if (Double.parseDouble(txtQ.getText()) <= LensPhysics.lensSearch().getFocalLength()) {
                    txtQ.setText("error!");
                } else {
                    SourceObject s = LensPhysics.sourceSearch();
                    double q = Double.parseDouble(txtQ.getText());

                    s.move(LensPhysics.sourceAbsPosFromImageRelPos(q));
                    s.getImage().update();
                    Rays.updateRays();
                    currentPPController.updateTextFields();
                }
            } else {
                if (Double.parseDouble(txtQ.getText()) >= 0) {
                    txtQ.setText("error!");
                } else {
                    SourceObject s = LensPhysics.sourceSearch();
                    double q = Double.parseDouble(txtQ.getText());

                    s.move(LensPhysics.sourceAbsPosFromImageRelPos(q));
                    s.getImage().update();
                    Rays.updateRays();
                    currentPPController.updateTextFields();

                }
            }

        });
    }

    /**
     * Setup the background color picking.
     */
    private void colorPickSetup() {
        bgColorIcon.setOpacity(0.7);
        bgColorIcon.setOnMouseEntered(e -> {
            bgColorIcon.setOpacity(1);

        });
        bgColorIcon.setOnMouseExited(e -> {
            bgColorIcon.setOpacity(0.7);
        });
        bgColorIcon.setOnMouseClicked(e -> {
            colorPickerPane.setVisible(!colorPickerPane.isVisible());

        });

        bgColorPicker.setOnAction(e -> {
            borderPane.setBackground(new Background(new BackgroundFill(bgColorPicker.getValue(), new CornerRadii(0), Insets.EMPTY)));

        });
    }

    /**
     * Setup the back to main menu (home) icon.
     */
    private void homeIconSetup() {
        homeIcon.setOpacity(0.7);
        homeIcon.setOnMouseEntered(e -> {
            homeIcon.setOpacity(1);

        });
        homeIcon.setOnMouseExited(e -> {
            homeIcon.setOpacity(0.7);
        });
        homeIcon.setOnMouseClicked(e -> {
            MainApp main = new MainApp();
            try {
                main.start(primaryStage);
                Item.getItemList().clear();
                Rays.clearRays();
                lensMain.stop();

            } catch (Exception ex) {
                Logger.getLogger(LensMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    /**
     * Setup the close control pane (X) button.
     */
    private void closeCtrlIconSetup() {
        closeCtrlIcon.setOnMouseEntered(e -> {
            closeCtrlIcon.setOpacity(0.6);

        });
        closeCtrlIcon.setOnMouseExited(e -> {
            closeCtrlIcon.setOpacity(0.18);
        });
        closeCtrlIcon.setOnMouseClicked(e -> {
            controlPane.setVisible(false);
            ctrlPaneOpenBtn.setVisible(true);
        });
    }

    /**
     * Initialize the parameter pane based on which item is chosen.
     * @param item
     * @throws IOException 
     */
    public void createParametersPane(Item item) throws IOException {

        FXMLLoader loader = null;
        switch (item.itemType) {
            case Item.ITEMTYPE_IMAGE:
                loader = new FXMLLoader(getClass().getResource("/fxml/lensImagePropertyPane.fxml"));
                currentPPController = new PPImageController(item);
                loader.setController(currentPPController);

                break;
            case Item.ITEMTYPE_LENS:
                loader = new FXMLLoader(getClass().getResource("/fxml/lensLensPropertyPane.fxml"));
                currentPPController = new PPLensController(item);
                loader.setController(currentPPController);
                break;
            case Item.ITEMTYPE_SOURCE:
                loader = new FXMLLoader(getClass().getResource("/fxml/lensSourcePropertyPane.fxml"));
                currentPPController = new PPSourceController(item);
                loader.setController(currentPPController);
                break;
        }
        parameterClearAndLoad(loader);
    }

    /**
     * Clears current parameter pane and loads the new one
     * @param loader
     * @throws IOException 
     */
    private void parameterClearAndLoad(FXMLLoader loader) throws IOException {
        this.propertyPane.getChildren().clear();
        this.currentParameterPane = loader.load();
        this.propertyPane.getChildren().add(this.currentParameterPane);
    }

    /**
     * Adjusts the lens line to move with the lens.
     */
    public void lensLineMove() {
        verticalLensLine.setLayoutX(LensPhysics.lensSearch().getNode().getBoundsInParent().getCenterX());
    }

    /**
     * Highlights item that is clicked on in list view.
     * @param selectedItem 
     */
    private void highlight(Item selectedItem) {

        DropShadow outerFocus = new DropShadow();
        outerFocus.setColor(Color.HOTPINK);
        outerFocus.setBlurType(BlurType.ONE_PASS_BOX);
        outerFocus.setRadius(25);
        outerFocus.setSpread(0.7);

        selectedItem.getNode().setEffect(outerFocus);

        KeyFrame k1 = new KeyFrame(Duration.millis(3000), new KeyValue(selectedItem.getNode().effectProperty(), null, Interpolator.LINEAR));
        Timeline tl = new Timeline(k1);

        tl.play();

    }

}
