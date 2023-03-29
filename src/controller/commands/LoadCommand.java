package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.Command;
import controller.ImageController;
import view.ImageView;

/**
 * This class is used for executing the load command from the controller.
 */
public class LoadCommand extends AbstractCommands {

  /**
   * This constructor initialises the variables for this class.
   *
   * @param scan               represents the scanner object
   * @param imageControllerImp represents the controller object
   * @param view               represents the view object
   */
  public LoadCommand(Scanner scan, ImageController imageControllerImp, ImageView view) {
    super(scan, imageControllerImp, view);
  }

  @Override
  public void execute() throws IOException {
    String imagePath = scan.next();
    String imageName = scan.next();
    imageControllerImp.load(imagePath, imageName);
    view.display(String.format("loaded %s successfully", imageName));
  }
}

