package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.ImageController;
import view.ImageView;

/**
 * This class is used for executing the Dither command from the controller.
 */
public class Dither extends AbstractCommands {

  /**
   * This constructor initialises the variables for this class.
   *
   * @param scan               represents the scanner object
   * @param imageControllerImp represents the controller object
   * @param view               represents the view object
   */
  public Dither(Scanner scan, ImageController imageControllerImp, ImageView view) {
    super(scan, imageControllerImp, view);
  }

  @Override
  public void execute() throws IOException {
    String imageName = scan.next();
    String updatedImageName = scan.next();

    imageControllerImp.dither(imageName, updatedImageName);
    view.display(String.format("dither conversion of %s to %s is successful",
            imageName, updatedImageName));
  }
}