package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.Command;
import controller.ImageController;
import view.ImageView;

public class RgbFunctions implements Command {
  private final Scanner scan;
  private final ImageController imageControllerImp;
  private final ImageView view;
  String functionType;

  public RgbFunctions(Scanner scan, ImageController imageControllerImp,
               ImageView view, String functionType) {
    this.scan = scan;
    this.imageControllerImp = imageControllerImp;
    this.view = view;
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
              + "successfully\n", imageName, redImageName, greenImageName, blueImageName));
    } else {
      imageControllerImp.combine(imageName, redImageName, greenImageName, blueImageName);
      view.display(String.format("combined red: %s green: %s blue: %s to %s"
              + "successfully\n", redImageName, greenImageName, blueImageName, imageName));
    }

  }
}
