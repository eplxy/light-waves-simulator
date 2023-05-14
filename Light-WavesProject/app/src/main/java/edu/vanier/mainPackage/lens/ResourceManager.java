package edu.vanier.mainPackage.lens;

import edu.vanier.mainPackage.GeneralSettingsController;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author someo
 */
public class ResourceManager {

    public static final String FOLDERPATH = "/images/lens/";
    public static final String CANDLE = "candle.png";
    public static final String CONVERGENTLENS = "convergentlens.png";
    public static final String DIVERGENTLENS = "divergentlens.png";

    public static ArrayList<String> pathList;

    public static void changeSourceImage(SourceObject source, String fileName) {
        ((ImageView) source.getNode()).setImage(retrieveImage(fileName));
        ((ImageView) source.getImage().getNode()).setImage(retrieveImage(fileName));

        source.fixVerticalPosition();
        source.getImage().fixVerticalPosition();
        source.setRayPointHeight(350-source.getNode().getLayoutBounds().getHeight()/3);

        source.positionFix();
        source.getImage().update();
        Rays.updateRays();
    }

    public static Image retrieveImage(String fileName) {
        return new Image(GeneralSettingsController.currentImageFolderPath + "/" + fileName);
    }

    public static ArrayList<String> getPathList() {

        if (!GeneralSettingsController.currentImageFolderPath.equals("")) {
            pathList = new ArrayList<>();
            final File folder = new File(GeneralSettingsController.currentImageFolderPath);

            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isFile()) {
                    pathList.add(fileEntry.getName());
                }
            }
        } else {
            pathList = null;
        }

        return pathList;
    }

//default retrieves
    public static Image retrieveConvergentLensImage() {
        return new Image(ResourceManager.class
                .getResource(FOLDERPATH + CONVERGENTLENS).toString());
    }

    public static Image
            retrieveDivergentLensImage() {
        return new Image(ResourceManager.class
                .getResource(FOLDERPATH + DIVERGENTLENS).toString());
    }

    public static Image
            retrieveCandleImage() {
        return new Image(ResourceManager.class
                .getResource(FOLDERPATH + CANDLE).toString());
    }
}
