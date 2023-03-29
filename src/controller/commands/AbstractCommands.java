package controller.commands;

import java.util.Scanner;

import controller.Command;
import controller.ImageController;
import view.ImageView;

/**
 * This abstract class holds the common variables from among all the command classes.
 */
public abstract class AbstractCommands implements Command {
  final Scanner scan;
  final ImageController imageControllerImp;
  final ImageView view;

  /**
   * This constructor initialises the class variables which are used by all the command classes.
   *
   * @param scan               represents the scanner object
   * @param imageControllerImp represents the controller object
   * @param view               represents the view object
   */
  protected AbstractCommands(Scanner scan, ImageController imageControllerImp, ImageView view) {
    this.scan = scan;
    this.imageControllerImp = imageControllerImp;
    this.view = view;
  }
}
