package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import controller.commands.Compare;
import controller.commands.Dither;
import controller.commands.Exposure;
import controller.commands.Filter;
import controller.commands.FlipImage;
import controller.commands.GrayscaleFunctions;
import controller.commands.LoadCommand;
import controller.commands.RgbFunctions;
import controller.commands.SaveCommand;
import controller.commands.SepiaTone;
import helper.ImageUtil;
import view.ImageView;

/**
 * This class represents the main controller and determines the steps after each input.
 */
public class CommandController {
  final Readable in;

  final ImageController imageControllerImp;
  final ImageView view;

  /**
   * The constructor is used to initialize the class variables.
   *
   * @param in                 represents the input stream.
   * @param imageControllerImp represents the ImageController
   * @param view represents the ImageView
   */
  public CommandController(Readable in, ImageController imageControllerImp, ImageView view) {
    this.in = in;
    this.imageControllerImp = imageControllerImp;
    this.view = view;
  }

  /**
   * This method is used as the main method which determines all the steps to be performed.
   */
  public void startProgram() {
    Scanner scan = new Scanner(this.in);
    try {
      while (menuScript(scan)) {

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  boolean menuScript(Scanner scan) throws IOException, NoClassDefFoundError {
    String t = scan.next();
    Command command = null;
    switch (t) {
      case "load":
        command = new LoadCommand(scan, imageControllerImp, view);
        break;
      case "save":
        command = new SaveCommand(scan, imageControllerImp, view);
        break;
      case "vertical-flip":
        command = new FlipImage(scan, imageControllerImp, view, "v");
        break;
      case "horizontal-flip":
        command = new FlipImage(scan, imageControllerImp, view, "h");
        break;
      case "brighten":
        command = new Exposure(scan, imageControllerImp, view, "b");
        break;
      case "darken":
        command = new Exposure(scan, imageControllerImp, view, "d");
        break;
      case "rgb-split":
        command = new RgbFunctions(scan, imageControllerImp, view, "split");
        break;
      case "rgb-combine":
        command = new RgbFunctions(scan, imageControllerImp, view, "combine");
        break;
      case "compare":
        command = new Compare(scan, imageControllerImp, view);
        break;
      case "greyscale":
        command = new GrayscaleFunctions(scan, imageControllerImp, view);
        break;
      case "sepia-tone":
        command = new SepiaTone(scan, imageControllerImp, view);
        break;
      case "dither":
        command = new Dither(scan, imageControllerImp, view);
        break;
      case "blur":
        command = new Filter(scan, imageControllerImp, view, "blur");
        break;
      case "sharpen":
        command = new Filter(scan, imageControllerImp, view, "sharpen");
        break;
      case "run":
        try {
          BufferedReader reader = ImageUtil.readTextFile(scan);
          String line;
          while ((line = reader.readLine()) != null
                  && menuScript(new Scanner(line))) {

          }
          return false;
        } catch (IOException e) {
          view.display(e.getMessage());
          while (menuScript(scan)) {

          }
          return false;
        }
      case "q":
        view.display("application ended");
        return false;
      default:
        view.display("Invalid command. Please try again.");
        while (menuScript(scan)) {

        }
        return false;
    }


    try {
      if (command != null && !t.equals("run")) {
        command.execute();
      }
    } catch (IOException e) {
      view.display(e.getMessage());
      menuScript(scan);
      return false;
    }

    return true;
  }

}


