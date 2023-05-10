package edu.vanier.mainPackage.lens;

import java.awt.font.TextHitInfo;
import javafx.scene.shape.Line;

/**
 *
 * @author Stella
 */
public class Rays {

    //source to lens
    public static Line line1a, line2a, line3a;
    //lens to infinity
    public static Line line1b, line2b, line3b;
    //dotted lines
    public static Line line1c, line2c, line3c;
    private static LensMenuController lmc = LensMenuController.currentLMC;

    public static void generateRays() {
        lmc = LensMenuController.currentLMC;

        SourceObject source = LensPhysics.sourceSearch();
        ImageObject image = source.getImage();
        Lens lens = LensPhysics.lensSearch();

        //set A
        line1a = new Line(source.getNode().getBoundsInParent().getCenterX(), source.getRayPointHeight(), lens.getNode().getBoundsInParent().getCenterX(), source.getRayPointHeight());
        line2a = new Line(source.getNode().getBoundsInParent().getCenterX(), source.getRayPointHeight(), lens.getNode().getBoundsInParent().getCenterX(), 350);
        line3a = new Line(source.getNode().getBoundsInParent().getCenterX(), source.getRayPointHeight(), lens.getNode().getBoundsInParent().getCenterX(), compute1cExtrapolation(lens, source));

        //set B
        double[] line1bext = extendLines(
                lens.getNode().getBoundsInParent().getCenterX(),
                line1a.getEndY(),
                lens.getNode().getBoundsInParent().getCenterX() + lens.getFocalLength() * LensMain.CONVERSIONFACTOR,
                350);
        line1b = new Line(line1a.getEndX(), line1a.getEndY(), line1bext[0], line1bext[1]);

        double[] line2bext = extendLines(line2a.getStartX(),
                line2a.getStartY(),
                line2a.getEndX(),
                line2a.getEndY());
        line2b = new Line(line2a.getEndX(), line2a.getEndY(), line2bext[0], line2bext[1]);

        line3b = new Line(line3a.getEndX(), line3a.getEndY(), 1400, line3a.getEndY());

        //set C
        //line1c = new Line(line1a.getEndX(),line1a.getEndY(),image.node.getBoundsInParent().getCenterX(), );
        
        
        lmc.animationPane.getChildren().addAll(line1a, line2a, line3a,
                line1b, line2b, line3b);

    }

    public static void clearRays() {
        lmc.animationPane.getChildren().removeAll(line1a, line2a, line3a, line1b, line2b, line3b);
    }

    public static void updateRays() {
        SourceObject source = LensPhysics.sourceSearch();
        ImageObject image = source.getImage();
        Lens lens = LensPhysics.lensSearch();

        //set A
        line1a.setStartX(source.getNode().getBoundsInParent().getCenterX());
        line2a.setStartX(source.getNode().getBoundsInParent().getCenterX());
        line3a.setStartX(source.getNode().getBoundsInParent().getCenterX());

        line1a.setStartY(source.getRayPointHeight());
        line2a.setStartY(source.getRayPointHeight());
        line3a.setStartY(source.getRayPointHeight());

        line1a.setEndX(lens.getNode().getBoundsInParent().getCenterX());
        line2a.setEndX(lens.getNode().getBoundsInParent().getCenterX());
        line3a.setEndX(lens.getNode().getBoundsInParent().getCenterX());

        line1a.setEndY(source.getRayPointHeight());
        line2a.setEndY(350);
        line3a.setEndY(computeYExtrapolation(source.getNode().getBoundsInParent().getCenterX(),
                source.getRayPointHeight(),
                lens.getNode().getBoundsInParent().getCenterX() - lens.getFocalLength() * LensMain.CONVERSIONFACTOR,
                350, lens.getNode().getBoundsInParent().getCenterX()));

        //set B
        double[] line1bext = extendLines(
                lens.getNode().getBoundsInParent().getCenterX(),
                line1a.getEndY(),
                lens.getNode().getBoundsInParent().getCenterX() + lens.getFocalLength() * LensMain.CONVERSIONFACTOR,
                350);
        double[] line2bext = extendLines(line2a.getStartX(),
                line2a.getStartY(),
                line2a.getEndX(),
                line2a.getEndY());

        line1b.setStartX(line1a.getEndX());
        line2b.setStartX(line2a.getEndX());
        line3b.setStartX(line3a.getEndX());

        line1b.setStartY(line1a.getEndY());
        line2b.setStartY(line2a.getEndY());
        line3b.setStartY(line3a.getEndY());

        line1b.setEndX(line1bext[0]);
        line2b.setEndX(line2bext[0]);
        line3b.setEndX(1400);

        line1b.setEndY(line1bext[1]);
        line2b.setEndY(line2bext[1]);
        line3b.setEndY(line3a.getEndY());

    }

    private static double[] extendLines(double x1, double y1, double x2, double y2) {
        if (computeYExtrapolation(x1, y1, x2, y2, 1400) > 800) {
            return new double[]{computeXExtrapolation(x1, y1, x2, y2, 800), 800};
        } else {
            return new double[]{1400, computeYExtrapolation(x1, y1, x2, y2, 1400)};
        }
    }

    private static double computeYExtrapolation(double x1, double y1, double x2, double y2, double destX) {
        double slope = (y2 - y1) / (x2 - x1);
        double yint = y2 - slope * x2;
        return slope * destX + yint;
    }

    private static double computeXExtrapolation(double x1, double y1, double x2, double y2, double destY) {
        double slope = (y2 - y1) / (x2 - x1);
        double yint = y2 - slope * x2;
        return (destY - yint) / slope;
    }

    private static double compute1cExtrapolation(Lens lens, SourceObject source) {
        double x2 = lens.getNode().getBoundsInParent().getCenterX() - lens.getFocalLength() * LensMain.CONVERSIONFACTOR;
        double x1 = source.getNode().getBoundsInParent().getCenterX();
        double y1 = source.getRayPointHeight();
        //double y2 = LensMenuController.currentLMC.principalAxis.getLayoutY();
        double y2 = 350;
        double slope = (y2 - y1) / (x2 - x1);
        double yint = y2 - slope * x2;
        return slope * lens.getNode().getBoundsInParent().getCenterX() + yint;
    }

}
