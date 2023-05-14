package edu.vanier.mainPackage.lens;

import edu.vanier.mainPackage.GeneralSettingsController;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author someo
 */
public class ResourceManager {

    public static final List<String> extensionList = Arrays.asList(".png", ".jpg", ".jpeg", ".bmp", ".gif");
    public static final String FOLDERPATH = "/images/lens/";
    public static final String CANDLE = "candle.png";
    public static final String CONVERGENTLENS = "convergentlens.png";
    public static final String DIVERGENTLENS = "divergentlens.png";

    public static ArrayList<String> pathList;

    /**
     * Changes the node image of a given source.
     *
     * @param source the SourceObject to adjust
     * @param fileName the filename of the image to apply
     */
    public static void changeSourceImage(SourceObject source, String fileName) {
        ((ImageView) source.getNode()).setImage(retrieveImage(fileName));
        ((ImageView) source.getImage().getNode()).setImage(retrieveImage(fileName));

        source.fixVerticalPosition();
        source.getImage().fixVerticalPosition();
        source.setRayPointHeight(350 - source.getNode().getLayoutBounds().getHeight() / 3);

        source.positionFix();
        source.getImage().update();
        Rays.updateRays();
    }

    /**
     * Resets source node image to default candle.
     *
     * @param source
     */
    public static void resetSourceImage(SourceObject source) {
        ((ImageView) source.getNode()).setImage(retrieveCandleImage());
        ((ImageView) source.getImage().getNode()).setImage(retrieveCandleImage());

        source.fixVerticalPosition();
        source.getImage().fixVerticalPosition();
        source.setRayPointHeight(350 - source.getNode().getLayoutBounds().getHeight() / 3);

        source.positionFix();
        source.getImage().update();
        Rays.updateRays();
    }

    /**
     * Returns a new image created by searching for a given fileName in the
     * settings-defined image folder path.
     *
     * @param fileName
     * @return the desired image
     */
    public static Image retrieveImage(String fileName) {
        return new Image(GeneralSettingsController.currentImageFolderPath + "/" + fileName);
    }

    /**
     * Creates a list of all file names from the current image folder path. Only
     * accepts images.
     *
     * @return the file name list
     */
    public static ArrayList<String> getPathList() {

        if (!GeneralSettingsController.currentImageFolderPath.equals("")) {
            pathList = new ArrayList<>();
            final File folder = new File(GeneralSettingsController.currentImageFolderPath);

            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isFile()) {
                    String fileName = fileEntry.getName();
                    if (extensionList.contains(fileName.substring(fileName.indexOf('.'), fileName.length()))) {
                        pathList.add(fileEntry.getName());
                    }

                }
            }
        } else {
            pathList = null;
        }

        return pathList;
    }

    /**
     * Retrieve the default convergent lens image
     *
     * @return
     */
    public static Image retrieveConvergentLensImage() {
        return new Image(ResourceManager.class
                .getResource(FOLDERPATH + CONVERGENTLENS).toString());
    }

    /**
     * Retrieve the default divergent lens image
     *
     * @return
     */
    public static Image
            retrieveDivergentLensImage() {
        return new Image(ResourceManager.class
                .getResource(FOLDERPATH + DIVERGENTLENS).toString());
    }

    /**
     * Retrieve the default candle image
     *
     * @return
     */
    public static Image
            retrieveCandleImage() {
        return new Image(ResourceManager.class
                .getResource(FOLDERPATH + CANDLE).toString());
    }
}
