package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.ImageController;
import view.ImageView;

/**
 * This class is used for executing the Grayscale commands from the controller.
 */
public class GrayscaleFunctions extends AbstractCommands {

  /**
   * This constructor initialises the variables for this class.
   *
   * @param scan               represents the scanner object
   * @param imageControllerImp represents the controller object
   * @param view               represents the view object
   */
  public GrayscaleFunctions(Scanner scan, ImageController imageControllerImp, ImageView view) {
    super(scan, imageControllerImp, view);
  }

  @Override
  public void execute() throws IOException {
    String conversionType = scan.next();
    String imageName = scan.next();
    String updatedImageName = scan.next();

    switch (conversionType) {
      case "value-component":
        imageControllerImp.combineByValue(imageName, updatedImageName);
        view.display(String.format("greyscale split of %s by %s to %s is successful",
                imageName, conversionType, updatedImageName));
        break;
      case "luma-component":
        imageControllerImp.combineByLuma(imageName, updatedImageName);
        view.display(String.format("greyscale split of %s by %s to %s is successful",
                imageName, conversionType, updatedImageName));
        break;
      case "intensity-component":
        imageControllerImp.combineByIntensity(imageName, updatedImageName);
        view.display(String.format("greyscale split of %s by %s to %s is successful",
                imageName, conversionType, updatedImageName));
        break;
      case "red-component":
        imageControllerImp.combineByComponent(0, imageName, updatedImageName);
        view.display(String.format("greyscale split of %s by %s to %s is successful",
                imageName, conversionType, updatedImageName));
        break;
      case "green-component":
        imageControllerImp.combineByComponent(1, imageName, updatedImageName);
        view.display(String.format("greyscale split of %s by %s to %s is successful",
                imageName, conversionType, updatedImageName));
        break;
      case "blue-component":
        imageControllerImp.combineByComponent(2, imageName, updatedImageName);
        view.display(String.format("greyscale split of %s by %s to %s is successful",
                imageName, conversionType, updatedImageName));
        break;
      default:
        view.display("invalid command. Please try again.");
        break;
    }
  }
}
