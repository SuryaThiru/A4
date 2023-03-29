package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.Command;
import controller.ImageController;
import view.ImageView;

/**
 * This class represents the controller that delegates the operation of applying sepia
 * tone on image to model.
 */
public class SepiaTone extends AbstractCommands {

  /**
   * This method constructs the object SepiaTone and initializes
   * its variables with the passed arguments.
   *
   * @param scan               represents the Scanner object
   * @param imageControllerImp represents the ImageController object
   * @param view               represents the ImageView object
   */
  public SepiaTone(Scanner scan, ImageController imageControllerImp, ImageView view) {
    super(scan, imageControllerImp, view);
  }

  @Override
  public void execute() throws IOException {
    String imageName = scan.next();
    String updatedImageName = scan.next();
    imageControllerImp.combineBySepia(imageName, updatedImageName);
    view.display(String.format("converting %s to a sepia-toned "
                    + "Image - %s is successful\n",
            imageName, updatedImageName));
  }
}
