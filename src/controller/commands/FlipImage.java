package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.ImageController;
import view.ImageView;

/**
 * This class is used for executing the Flip command from the controller.
 */
public class FlipImage extends AbstractCommands {
  String orientation;

  /**
   * This constructor initialises the variables for this class.
   *
   * @param scan               represents the scanner object
   * @param imageControllerImp represents the controller object
   * @param view               represents the view object
   * @param orientation        represents the orientation either horizontal or vertical
   */
  public FlipImage(Scanner scan, ImageController imageControllerImp, ImageView view,
                   String orientation) {
    super(scan, imageControllerImp, view);
    this.orientation = orientation;
  }

  @Override
  public void execute() throws IOException {
    String imageName = scan.next();
    String updatedImageName = scan.next();
    if (orientation.equals("v")) {
      imageControllerImp.flipVertical(imageName, updatedImageName);
      view.display(String.format("flipped %s to %s vertically successfully", imageName,
              updatedImageName));
      return;
    }
    imageControllerImp.flipHorizontal(imageName, updatedImageName);
    view.display(String.format("flipped %s to %s horizontally successfully", imageName,
            updatedImageName));
  }

}
