package edu.vanier.mainPackage.refraction;

import java.text.MessageFormat;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 2148289
 */
public class Refraction extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/refractionMenu6.fxml"));
        MenuController menuController = new MenuController(primaryStage);
        loader.setController(menuController);
        BorderPane root = loader.load();
        
        Line incidentRay = new Line();
        Line refractedRay = new Line();
        Line normalRay = new Line();
        Line horizontalRay = new Line();
        
        double angle1 = 45;
        double angle2 = Vector.CalculateAngle(Vector.NAIR, Vector.NWATER, angle1);
        System.out.println(angle1);
        System.out.println(angle2);
        
        //Incident Ray
        incidentRay.startXProperty().setValue(0);
        incidentRay.startYProperty().bind(menuController.animationPane.heightProperty().divide(2));
        incidentRay.endXProperty().bind(menuController.animationPane.widthProperty().divide(2));
        incidentRay.endYProperty().bind(menuController.animationPane.heightProperty().divide(2));
        
        Rotate rotate = new Rotate();
        rotate.pivotXProperty().bind(incidentRay.endXProperty());
        rotate.pivotYProperty().bind(incidentRay.endYProperty());
        rotate.setAngle(angle1);
        incidentRay.getTransforms().addAll(rotate);
        
        //Refracted Ray
        refractedRay.startXProperty().bind(menuController.animationPane.widthProperty().divide(2));
        refractedRay.startYProperty().bind(menuController.animationPane.heightProperty().divide(2));
        refractedRay.endXProperty().bind(menuController.animationPane.widthProperty());
        refractedRay.endYProperty().bind(menuController.animationPane.heightProperty().divide(2));
        
        Rotate rotate2 = new Rotate();
        rotate2.pivotXProperty().bind(incidentRay.endXProperty());
        rotate2.pivotYProperty().bind(incidentRay.endYProperty());
        rotate2.setAngle(90 - angle2);
        refractedRay.getTransforms().addAll(rotate2);
        
        //Horizontal Ray
        horizontalRay.startXProperty().setValue(0);
        horizontalRay.startYProperty().bind(menuController.animationPane.heightProperty().divide(2));
        horizontalRay.endXProperty().bind(menuController.animationPane.widthProperty());
        horizontalRay.endYProperty().bind(menuController.animationPane.heightProperty().divide(2));
        horizontalRay.getStrokeDashArray().addAll(3d);
        
        //Normal Ray
        normalRay.startXProperty().bind(menuController.animationPane.widthProperty().divide(2));
        normalRay.startYProperty().setValue(-20);
        normalRay.endXProperty().bind(menuController.animationPane.widthProperty().divide(2));
        normalRay.endYProperty().bind(menuController.animationPane.heightProperty().add(10));
        normalRay.getStrokeDashArray().addAll(10d);
        
        
        menuController.animationPane.getChildren().addAll(incidentRay, refractedRay, normalRay, horizontalRay);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        }
        public static void main(String[] args) {
            launch(args);
    }
//Group root = new Group();
//    Scene scene = new Scene(root, 500, 260);
//    
//    Rectangle roundRect = new Rectangle(50, 50, 400, 130);
//
//    roundRect.setArcWidth(30);
//    roundRect.setArcHeight(60);
//
//    roundRect.setFill(null);
//    roundRect.setStroke(Color.DARKORANGE);
//    roundRect.setStrokeWidth(2);
//    roundRect.setStrokeLineCap(StrokeLineCap.BUTT);
//
//    root.getChildren().add(roundRect);
//
//    Slider slider = new Slider(30, 150, 30);
//    slider.setLayoutX(250 - slider.getWidth() / 2);
//    slider.setLayoutY(115 - slider.getHeight() / 2);
//
//    slider.widthProperty().addListener((ov, curVal, newVal) -> {
//      slider.setLayoutX(250 - slider.getWidth() / 2);
//    });
//
//    slider
//        .heightProperty()
//        .addListener(
//            (ov, curVal, newVal) -> slider.setLayoutY(115 - slider.getHeight() / 2));
//
//    roundRect.arcWidthProperty().bind(slider.valueProperty());
//
//    root.getChildren().add(slider);
//
//    Slider slider2 = new Slider(10, 120, 50);
//    slider2.setLayoutX(50);
//    slider2.setLayoutY(230);
//
//    slider2.widthProperty().addListener((ov, curVal, newVal) -> {
//      slider2.setLayoutX(250 - slider2.getWidth() / 2);
//    });
//
//    roundRect.yProperty().bind(slider2.valueProperty());
//    root.getChildren().add(slider2);
//
//    slider2.valueProperty().addListener(
//        (ov, curVal, newVal) -> slider.setLayoutY(slider.getLayoutY()
//            + newVal.doubleValue() - curVal.doubleValue()));
//    primaryStage.setScene(scene);
//    primaryStage.show();
//  }
    
}
//import java.text.MessageFormat;
//
//import javafx.animation.FadeTransition;
//import javafx.animation.Interpolator;
//import javafx.application.Application;
//import javafx.beans.property.DoubleProperty;
//import javafx.beans.property.Property;
//import javafx.beans.property.SimpleDoubleProperty;
//import javafx.beans.property.SimpleObjectProperty;
//import javafx.geometry.Point2D;
//import javafx.scene.Cursor;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.effect.DropShadow;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Ellipse;
//import javafx.scene.shape.Line;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//public class Refraction extends Application
//{
//
//  @Override
//  public void start( final Stage primaryStage )
//  {
//    final Ellipse ellipse = createEllipse();
//
//    final Group root = new Group( ellipse );
//    final Property<Point2D> mousePressedPoint = new SimpleObjectProperty<>( Point2D.ZERO );
//    final DoubleProperty initialRotation = new SimpleDoubleProperty( 0 );
//    final DoubleProperty initialRadiusX = new SimpleDoubleProperty( 0 );
//    final DoubleProperty initialRadiusY = new SimpleDoubleProperty( 0 );
//
//    ellipse.setOnMousePressed( press ->
//    {
//      mousePressedPoint.setValue( new Point2D( press.getSceneX(), press.getSceneY() ) );
//      initialRotation.set( ellipse.getRotate() );
//      initialRadiusX.set( ellipse.getRadiusX() );
//      initialRadiusY.set( ellipse.getRadiusY() );
//    } );
//
//    ellipse.setOnMouseDragged( drag ->
//    {
//      final Point2D ellipseCenter = new Point2D( ellipse.getCenterX(), ellipse.getCenterY() );
//      final Point2D dragPoint = new Point2D( drag.getSceneX(), drag.getSceneY() );
//
//      if ( drag.isShortcutDown() )
//      {
//        final Point2D dragDistance = dragPoint.subtract( mousePressedPoint.getValue() );
//
//        if ( drag.isAltDown() )
//        {
//          final double radiusY = initialRadiusY.getValue() + dragDistance.getY();
//          if ( radiusY > 10 )
//          {
//            ellipse.setRadiusY( radiusY );
//            showText( MessageFormat.format( "{0}px", ellipse.getRadiusY() ),
//                ellipseCenter.subtract( 15, 15 ), Color.GREY, 100, root );
//          }
//        }
//
//        final double radiusX = initialRadiusX.getValue() + dragDistance.getX();
//        if ( radiusX > 10 )
//        {
//          ellipse.setRadiusX( radiusX );
//          showText( MessageFormat.format( "{0}px", ellipse.getRadiusX() ), ellipseCenter.add( 15, 15 ),
//              Color.GREY, 100, root );
//        }
//      }
//      else
//      {
//
//        final double angle = computeAngle( ellipseCenter, dragPoint, mousePressedPoint.getValue() );
//        ellipse.setRotate( initialRotation.get() + angle );
//
//        showLine( ellipseCenter, dragPoint, Color.DODGERBLUE, 200, root );
//        showText( MessageFormat.format( "{0}°", ellipse.getRotate() ), dragPoint, Color.WHITE, 100, root );
//      }
//    } );
//
//    final Scene scene = new Scene( root, 800, 600, Color.BLACK );
//    scene.setCursor( Cursor.CROSSHAIR );
//    primaryStage.setScene( scene );
//    primaryStage.show();
//  }
//
//  private Ellipse createEllipse()
//  {
//    final Ellipse ellipse = new Ellipse( 400, 300, 20, 40 );
//    ellipse.setFill( Color.AZURE );
//    ellipse.setStroke( Color.ANTIQUEWHITE );
//    ellipse.setStrokeWidth( 3 );
//    ellipse.setEffect( new DropShadow( 12, Color.WHITE ) );
//    return ellipse;
//  }
//
//  public static void main( final String[] args )
//  {
//    launch( args );
//  }
//
//  /**
//   * Stolen and afterwards adapted from:
//   * https://stackoverflow.com/questions/3365171/calculating-the-angle-between-two-lines-without-having-to-
//   * calculate-the-slope
//   */
//  private double computeAngle( final Point2D v, final Point2D a, final Point2D b )
//  {
//    final double angle1 = Math.atan2( v.getY() - a.getY(), v.getX() - a.getX() );
//    final double angle2 = Math.atan2( v.getY() - b.getY(), v.getX() - b.getX() );
//    return (angle1 - angle2) / Math.PI * 180;
//  }
//  private static void showLine( final Point2D from, final Point2D to, final Color color, final int duration,
//                                final Group inGroup )
//  {
//    final Line line = new Line( from.getX(), from.getY(), to.getX(), to.getY() );
//    line.setStroke( color );
//    line.setStrokeWidth( 3 );
//    line.setOpacity( .75 );
//    inGroup.getChildren().add( line );
//
//    final FadeTransition trans = new FadeTransition();
//    trans.setDuration( Duration.millis( duration ) );
//    trans.setNode( line );
//    trans.setToValue( 0 );
//
//    trans.setInterpolator( Interpolator.EASE_OUT );
//    trans.setOnFinished( finishHim -> inGroup.getChildren().remove( line ) );
//    trans.play();
//  }
//
//  private static void showText( final String textString, final Point2D position, final Color color,
//                                final int duration, final Group inGroup )
//  {
//    final Text text = new Text( textString );
//    text.setX( 15 + position.getX() );
//    text.setY( position.getY() );
//    text.setFill( color );
//    text.setFont( Font.font( "monospace", 32 ) );
//    text.setOpacity( .75 );
//    inGroup.getChildren().add( text );
//
//    final FadeTransition trans = new FadeTransition();
//    trans.setDuration( Duration.millis( duration ) );
//    trans.setNode( text );
//    trans.setToValue( 0 );
//
//    trans.setInterpolator( Interpolator.EASE_OUT );
//    trans.setOnFinished( finishHim -> inGroup.getChildren().remove( text ) );
//    trans.play();
  


