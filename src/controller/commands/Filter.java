package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.ImageController;
import view.ImageView;

/**
 * This class is used for executing the Filter command from the controller.
 */
public class Filter extends AbstractCommands {
  String filterType;

  /**
   * This constructor initialises the variables for this class.
   *
   * @param scan               represents the scanner object
   * @param imageControllerImp represents the controller object
   * @param view               represents the view object
   * @param filterType         represents the filterType either blur or sharpen
   */
  public Filter(Scanner scan, ImageController imageControllerImp,
                ImageView view, String filterType) {
    super(scan, imageControllerImp, view);
    this.filterType = filterType;
  }

  @Override
  public void execute() throws IOException {
    String imageName = scan.next();
    String updatedImageName = scan.next();

    if (filterType.equals("blur")) {
      imageControllerImp.filter(imageName, updatedImageName, "blur");
      view.display(String.format("blurred %s to %s "
              + "successfully", imageName, updatedImageName));
      return;
    }
    imageControllerImp.filter(imageName, updatedImageName, "sharpen");
    view.display(String.format("sharpened %s to %s"
            + " successfully", imageName, updatedImageName));
  }
}
