package DoubleSlit.UI;

import DoubleSlit.Simulation.Parameters;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author sabri
 */
//TODO: 
/*
remove redundant comments (licence)
empty line btween fields if the field has an annotation
use lombok to generate getters and setters
simplify the formula
remove useless empty lines
create constants 
private functions
changing text changes slider value

 */
public class DoubleSlitMenuController {

    Stage primaryStage;

    private Parameters parameters;

    @FXML
    Slider sliderWavelength;

    @FXML
    Slider sliderWidth;

    @FXML
    Slider sliderScreen;

    @FXML
    Slider sliderSpacing;

    @FXML
    TextField txtFieldWavelength;

    @FXML
    TextField txtFieldWidth;

    @FXML
    TextField txtFieldScreen;

    @FXML
    TextField txtFieldSpacing;

    @FXML
    RadioButton radioBtnAnimation;

    @FXML
    RadioButton radioBtnGraph;

    @FXML
    Pane paneView;

    @FXML
    Button btnEnter;

    GraphController graphController;
    String selectedView;

    public DoubleSlitMenuController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initialize() throws IOException {
        ToggleGroup viewTG = new ToggleGroup();
        radioBtnAnimation.setToggleGroup(viewTG);
        radioBtnGraph.setToggleGroup(viewTG);
        viewTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
                RadioButton rb = (RadioButton) viewTG.getSelectedToggle();
                if (rb != null) {
                    selectedView = rb.getText();
                    changeView(selectedView);
                }
            }
        });
        radioBtnGraph.setSelected(true);
        sliderWavelength.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        txtFieldWavelength.setText(newValue.toString());
                        //Parameters.setWavelength(Double.parseDouble(txtFieldWavelength.getText()));
            }
        });
        sliderWidth.valueProperty().addListener(
                new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                txtFieldWidth.setText(newValue.toString());
                //Parameters.setWidth (Double.parseDouble(txtFieldWidth.getText()));

            }
        });
        sliderScreen.valueProperty().addListener(
                new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                txtFieldScreen.setText(newValue.toString());
                //Parameters.setScreen(Double.parseDouble(txtFieldScreen.getText()));

            }
        });
        sliderSpacing.valueProperty().addListener(
                new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                txtFieldSpacing.setText(newValue.toString());
                //Parameters.setSpacing( Double.parseDouble(txtFieldSpacing.getText()));
            }
        });
        btnEnter.setOnAction((event) -> {
            handleEnter(event, this.primaryStage);
        });
        defaultValues();
    }

    public void changeView(String selectedView) {
        if (selectedView.equals("Animation")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/animation.fxml"));
                AnimationController animationController = new AnimationController(primaryStage, this.parameters);
                loader.setController(animationController);
                BorderPane root = loader.load();
                paneView.getChildren().clear();
                paneView.getChildren().add(root);

            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/graph.fxml"));
                GraphController graphController = new GraphController(primaryStage);
                this.graphController = graphController;
                loader.setController(graphController);
                BorderPane root = loader.load();
                paneView.getChildren().clear();
                paneView.getChildren().add(root);

            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    public void handleEnter(ActionEvent event, Stage primaryStage) {
        Parameters.setWavelength(Double.parseDouble(txtFieldWavelength.getText()));
        Parameters.setWidth(Double.parseDouble(txtFieldWidth.getText()));
        Parameters.setScreen(Double.parseDouble(txtFieldScreen.getText()));
        Parameters.setSpacing(Double.parseDouble(txtFieldSpacing.getText()));
        if (selectedView.equals("Graph")) {
            this.graphController.plotLine();
        }
    }

    public void defaultValues() {
        sliderWavelength.setValue(generateRandom(380, 750));
        sliderWidth.setValue(generateRandom(0, 10));
        sliderScreen.setValue(generateRandom(0, 20));
        sliderSpacing.setValue(generateRandom(0, 10));
        if (selectedView.equals("Graph")) {
            //this.graphController.plotLine();
        }
    }

    private double generateRandom(double min, double max) {
        return Math.random() * (max - min + 1) + min;
    }

}
