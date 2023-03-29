package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.Command;
import controller.ImageController;
import view.ImageView;

public class GrayscaleFunctions implements Command {
  private final Scanner scan;
  private final ImageController imageControllerImp;
  private final ImageView view;

  public GrayscaleFunctions(Scanner scan, ImageController imageControllerImp, ImageView view) {
    this.scan = scan;
    this.imageControllerImp = imageControllerImp;
    this.view = view;
  }

  @Override
  public void execute() throws IOException {
    String conversionType = scan.next();
    String imageName = scan.next();
    String updatedImageName = scan.next();

    switch (conversionType) {
      case "value-component":
        imageControllerImp.combineByValue(imageName, updatedImageName);
        view.display(String.format("greyscale split of %s by %s to %s is successful\n",
                imageName, conversionType, updatedImageName));
        break;
      case "luma-component":
        imageControllerImp.combineByLuma(imageName, updatedImageName);
        view.display(String.format("greyscale split of %s by %s to %s is successful\n",
                imageName, conversionType, updatedImageName));
        break;
      case "intensity-component":
        imageControllerImp.combineByIntensity(imageName, updatedImageName);
        view.display(String.format("greyscale split of %s by %s to %s is successful\n",
                imageName, conversionType, updatedImageName));
        break;
      case "red-component":
        imageControllerImp.combineByComponent(0, imageName, updatedImageName);
        view.display(String.format("greyscale split of %s by %s to %s is successful\n",
                imageName, conversionType, updatedImageName));
        break;
      case "green-component":
        imageControllerImp.combineByComponent(1, imageName, updatedImageName);
        view.display(String.format("greyscale split of %s by %s to %s is successful\n",
                imageName, conversionType, updatedImageName));
        break;
      case "blue-component":
        imageControllerImp.combineByComponent(2, imageName, updatedImageName);
        view.display(String.format("greyscale split of %s by %s to %s is successful\n",
                imageName, conversionType, updatedImageName));
        break;
      default:
        break;
    }
  }
}
