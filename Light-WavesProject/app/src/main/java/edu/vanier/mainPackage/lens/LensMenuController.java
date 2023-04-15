package edu.vanier.mainPackage.lens;

import edu.vanier.mainPackage.MainApp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author stella
 */
public class LensMenuController {

    LensMain lensMain;
    Stage primaryStage;
    Button ctrlPaneOpenBtn;
    @FXML
    TextField ctrlTextField2, ctrlTextField3, ctrlTextField4, ctrlTextField5, ctrlTextField6;
    @FXML
    ImageView homeIcon;
    @FXML
    ImageView closeCtrlIcon;
    @FXML
    TextField ctrlTextField1;
    @FXML
    BorderPane borderPane;
    @FXML
    Line principalAxis;
    @FXML
    Pane animationPane, controlPane, itemPane;
    @FXML
    HBox itemBox;
    @FXML
    AnchorPane midAnchorPane;
    @FXML
    ListView itemListView;

    public LensMenuController(Stage stage, LensMain lensMain) {
        this.primaryStage = stage;
        this.lensMain = lensMain;
        Item.setLmc(this);
        ItemLabel.setLmc(this);
    }

    public void initialize() {

        this.ctrlPaneOpenBtnSetup();
        this.homeIconSetup();
        this.closeCtrlIconSetup();

        ctrlTextField1.setOnAction(e -> {
            SourceObject so = LensPhysics.sourceSearch();
            so.move(Double.parseDouble(ctrlTextField1.getText()));
            so.getImage().update();
        });

        ctrlTextField2.setOnAction(e -> {
            Lens l = LensPhysics.lensSearch();
            SourceObject so = LensPhysics.sourceSearch();
            l.setFocalLength(Double.parseDouble(ctrlTextField2.getText()));
            so.getImage().update();
        });

        ctrlTextField3.setOnAction(e -> {
            SourceObject so = LensPhysics.sourceSearch();
            so.getNode().setLayoutX(Double.parseDouble(ctrlTextField3.getText()));
        });

        ctrlTextField4.setOnAction(e -> {
            SourceObject so = LensPhysics.sourceSearch();
            so.getImage().getNode().setLayoutX(Double.parseDouble(ctrlTextField4.getText()));
        });
        
        ctrlPaneOpenBtn = new Button(); 
        
    }
    
    public void itemListViewUpdate(){
        ObservableList<Item> obsItemList = FXCollections.observableArrayList();
        for (int i = 0; i < Item.getItemList().size(); i++) {
            obsItemList.add(Item.getItemList().get(i+1));
        }
        itemListView.setItems(obsItemList);
    } 

    private void ctrlPaneOpenBtnSetup(){
        ctrlPaneOpenBtn = new Button("Show");
        ctrlPaneOpenBtn.setVisible(true);
        
        ctrlPaneOpenBtn.setLayoutX(675);
        ctrlPaneOpenBtn.setLayoutY(675);
        animationPane.getChildren().add(ctrlPaneOpenBtn);
        //ctrlPaneOpenBtn.setLayoutX(700);
        //ctrlPaneOpenBtn.setLayoutY(700);
        
        ctrlPaneOpenBtn.setOnAction(e->{
            controlPane.setVisible(true);
            //ctrlPaneOpenBtn.setVisible(false);
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

}
