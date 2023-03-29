package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.Command;
import controller.ImageController;
import view.ImageView;

public class LoadCommand implements Command {
  private final Scanner scan;
  private final ImageController imageControllerImp;

  private final ImageView view;

  public LoadCommand(Scanner scan, ImageController imageControllerImp, ImageView view) {
    this.scan = scan;
    this.imageControllerImp = imageControllerImp;
    this.view = view;
  }

  @Override
  public void execute() throws IOException {
    String imagePath = scan.next();
    String imageName = scan.next();
    imageControllerImp.load(imagePath, imageName);
    view.display(String.format("loaded %s successfully\n", imageName));
  }
}
