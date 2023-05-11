package edu.vanier.mainPackage.lens;

import edu.vanier.mainPackage.MainApp;
import edu.vanier.mainPackage.lens.propertyPanes.PPController;
import edu.vanier.mainPackage.lens.propertyPanes.PPImageController;
import edu.vanier.mainPackage.lens.propertyPanes.PPLensController;
import edu.vanier.mainPackage.lens.propertyPanes.PPSourceController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    Button btnPulseRays, btnLockItem, btnToggleLensLine;
    @FXML
    ImageView homeIcon;
    @FXML
    ImageView closeCtrlIcon;
    @FXML
    BorderPane borderPane;
    @FXML
    Line principalAxis, verticalLensLine;
    @FXML
    Pane animationPane, controlPane, itemPane, propertyPane;
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
        
        this.setupPQ();
        this.lensLineSetup();
        this.ctrlPaneOpenBtnSetup();
        this.homeIconSetup();
        this.closeCtrlIconSetup();

        ctrlPaneOpenBtn = new Button();

        itemListView.setOnMouseClicked(e -> {
            itemListViewHandle();
        });

    }

    private void lensLineSetup() {
        btnToggleLensLine.setOnAction(e -> {
            verticalLensLine.setVisible(!verticalLensLine.isVisible());
        });

    }

    public void itemListViewHandle() {
        selectedItem = (Item) itemListView.getSelectionModel().getSelectedItem();
        highlight(selectedItem);

        try {
            createParametersPane(selectedItem);
        } catch (IOException e) {

        }

    }

    public void itemListViewUpdate() {
        ObservableList<Item> obsItemList = FXCollections.observableArrayList();
        for (int i = 0; i < Item.getItemList().size(); i++) {
            obsItemList.add(Item.getItemList().get(i + 1));
        }
        itemListView.setItems(obsItemList);
    }

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

    private void setupPQ() {

        txtP.setOnAction(e -> {
            SourceObject s = LensPhysics.sourceSearch();
            double p = Double.parseDouble(txtP.getText());

            s.move(LensPhysics.lensSearch().getAbsPos() - p);
            s.getImage().update();
            Rays.updateRays();
        });

        txtQ.setOnAction(e -> {
            SourceObject s = LensPhysics.sourceSearch();
            double q = Double.parseDouble(txtQ.getText());

            s.move(LensPhysics.sourceAbsPosFromImageRelPos(q));
            s.getImage().update();
            Rays.updateRays();
        });
    }

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

    private void createParametersPane(Item item) throws IOException {

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

    private void parameterClearAndLoad(FXMLLoader loader) throws IOException {
        this.propertyPane.getChildren().clear();
        this.currentParameterPane = loader.load();
        this.propertyPane.getChildren().add(this.currentParameterPane);
    }

    public void lensLineMove() {
        verticalLensLine.setLayoutX(LensPhysics.lensSearch().getNode().getBoundsInParent().getCenterX());
    }

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
