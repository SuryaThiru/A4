package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.Command;
import controller.ImageController;
import view.ImageView;

public class FlipImage implements Command {
  private final Scanner scan;
  private final ImageController imageControllerImp;
  private final ImageView view;

  String orientation;

  public FlipImage(Scanner scan, ImageController imageControllerImp, ImageView view,
                   String orientation) {
    this.scan = scan;
    this.imageControllerImp = imageControllerImp;
    this.view = view;
    this.orientation = orientation;
  }

  @Override
  public void execute() throws IOException {
    String imageName = scan.next();
    String updatedImageName = scan.next();
    if (orientation.equals("v")) {
      imageControllerImp.flipVertical(imageName, updatedImageName);
      view.display(String.format("flipped %s to %s vertically successfully", imageName,
              updatedImageName));
      return;
    }
    imageControllerImp.flipHorizontal(imageName, updatedImageName);
    view.display(String.format("flipped %s to %s horizontally successfully", imageName,
            updatedImageName));
  }

  }
}
