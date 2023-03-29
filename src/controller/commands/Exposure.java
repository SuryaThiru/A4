package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.Command;
import controller.ImageController;
import view.ImageView;

public class Exposure implements Command {
  private final Scanner scan;
  private final ImageController imageControllerImp;
  private final ImageView view;

  String exposureType;

  public Exposure(Scanner scan, ImageController imageControllerImp,
           ImageView view, String exposureType) {
    this.scan = scan;
    this.imageControllerImp = imageControllerImp;
    this.view = view;
    this.exposureType = exposureType;
  }

  @Override
  public void execute() throws IOException {
    int value = scan.nextInt();
    String imageName = scan.next();
    String updatedImageName = scan.next();
    if (exposureType.equals("brighten")) {
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