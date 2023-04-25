package controller.commands;

import controller.ImageController;
import java.io.IOException;
import java.util.Scanner;
import view.ImageView;


/**
 * This class is used for executing the Mosaick command from the controller.
 */
public class Mosaick extends AbstractCommands {

  /**
   * This constructor initialises the variables for this class.
   *
   * @param scan               represents the scanner object
   * @param imageControllerImp represents the controller object
   * @param view               represents the view object
   */
  public Mosaick(Scanner scan, ImageController imageControllerImp, ImageView view) {
    super(scan, imageControllerImp, view);
  }

  @Override
  public void execute() throws IOException {
    int value = scan.nextInt();
    String imageName = scan.next();
    String updatedImageName = scan.next();

    imageControllerImp.mosaick(value, imageName, updatedImageName);
    view.display(
        String.format("mosaicked the image %s using %d seeds to %s successfully", imageName, value,
            updatedImageName));

  }
}
