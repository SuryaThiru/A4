package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.Command;
import controller.ImageController;
import view.ImageView;

public class Compare implements Command {
  private final Scanner scan;
  private final ImageController imageControllerImp;
  private final ImageView view;

  public Compare(Scanner scan, ImageController imageControllerImp, ImageView view) {
    this.scan = scan;
    this.imageControllerImp = imageControllerImp;
    this.view = view;
  }

  @Override
  public void execute() throws IOException {
    String imageName = scan.next();
    String updatedImageName = scan.next();
    boolean f = imageControllerImp.compareImages(imageName, updatedImageName);
    if (!f) {
      view.display(String.format("%s is not the same as %s\n", imageName,
              updatedImageName));
    } else {
      view.display(String.format("%s is the same as %s\n", imageName, updatedImageName));
    }

  }
}
