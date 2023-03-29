package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.ImageController;
import view.ImageView;

/**
 * This class is used for executing the Compare command from the controller.
 */
public class Compare extends AbstractCommands {

  /**
   * This constructor initialises the variables for this class.
   *
   * @param scan               represents the scanner object
   * @param imageControllerImp represents the controller object
   * @param view               represents the view object
   */
  public Compare(Scanner scan, ImageController imageControllerImp, ImageView view) {
    super(scan, imageControllerImp, view);
  }

  @Override
  public void execute() throws IOException {
    String imageName = scan.next();
    String updatedImageName = scan.next();
    boolean f = imageControllerImp.compareImages(imageName, updatedImageName);
    if (!f) {
      view.display(String.format("%s is not the same as %s", imageName,
              updatedImageName));
      return;
    }
    view.display(String.format("%s is the same as %s", imageName, updatedImageName));

  }
}
