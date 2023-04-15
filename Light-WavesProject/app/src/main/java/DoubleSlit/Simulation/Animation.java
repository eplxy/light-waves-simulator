package DoubleSlit.Simulation;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author sabri
 */
public class Animation {
    Rectangle rectCenter = new Rectangle();
    Rectangle rectTop = new Rectangle();
    Rectangle rectBottom = new Rectangle();

//     public void setup ()
//     
//      //Setting the properties of the rectangle 
//      rectCenter.setX(150.0f); 
//      rectangle.setY(75.0f); 
//      rectangle.setWidth(300.0f); 
//      rectangle.setHeight(150.0f);      
    //Creating a Group object  
    Group root = new Group(rectCenter, rectBottom, rectTop);
}
