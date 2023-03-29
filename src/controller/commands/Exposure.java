package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.ImageController;
import view.ImageView;

/**
 * This class is used for executing the Exposure command from the controller.
 */
public class Exposure extends AbstractCommands {
  String exposureType;

  /**
   * This constructor initialises the variables for this class.
   *
   * @param scan               represents the scanner object
   * @param imageControllerImp represents the controller object
   * @param view               represents the view object
   * @param exposureType       represents the exposureType either brighten or darken
   */
  public Exposure(Scanner scan, ImageController imageControllerImp,
                  ImageView view, String exposureType) {
    super(scan, imageControllerImp, view);
    this.exposureType = exposureType;
  }

  @Override
  public void execute() throws IOException {
    int value = scan.nextInt();
    String imageName = scan.next();
    String updatedImageName = scan.next();

    if (exposureType.equals("b")) {
      imageControllerImp.brighten(value, imageName, updatedImageName);
      view.display(String.format("increased the brightness of %s by %d to %s "
              + "successfully", imageName, value, updatedImageName));
      return;
    }
    imageControllerImp.darken(value, imageName, updatedImageName);
    view.display(String.format("decreased the brightness of %s by %d to %s "
            + "successfully", imageName, value, updatedImageName));
  }
}