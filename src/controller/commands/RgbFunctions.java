package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.Command;
import controller.ImageController;
import view.ImageView;

/**
 * This class is used for executing the RGB commands from the controller.
 */
public class RgbFunctions extends AbstractCommands {
  String functionType;

  /**
   * This constructor initialises the variables for this class.
   *
   * @param scan               represents the scanner object
   * @param imageControllerImp represents the controller object
   * @param view               represents the view object
   * @param functionType       represents the type of the function being performed
   */
  public RgbFunctions(Scanner scan, ImageController imageControllerImp,
                      ImageView view, String functionType) {
    super(scan, imageControllerImp, view);
    this.functionType = functionType;
  }

  @Override
  public void execute() throws IOException {
    String imageName = scan.next();
    String redImageName = scan.next();
    String greenImageName = scan.next();
    String blueImageName = scan.next();

    if (functionType.equals("split")) {
      imageControllerImp.split(imageName, redImageName, greenImageName, blueImageName);
      view.display(String.format("split %s to red: %s green: %s blue: %s "
              + "successfully", imageName, redImageName, greenImageName, blueImageName));
      return;
    }
    imageControllerImp.combine(imageName, redImageName, greenImageName, blueImageName);
    view.display(String.format("combined red: %s green: %s blue: %s to %s"
            + "successfully", redImageName, greenImageName, blueImageName, imageName));

  }
}
