import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import controller.Command;
import controller.ImageController;
import controller.ImageControllerImp;
import controller.commands.Compare;
import controller.commands.Dither;
import controller.commands.Exposure;
import controller.commands.FlipImage;
import controller.commands.GrayscaleFunctions;
import controller.commands.LoadCommand;
import controller.commands.RgbFunctions;
import controller.commands.SaveCommand;
import controller.commands.SepiaTone;
import model.Image;
import model.RGBImage;
import view.ImageView;
import view.TextView;

/**
 * This class represents the main controller and determines the steps after each input.
 */
public class MainController {
  final Readable in;
  final Appendable out;

  Map<String, Function<Scanner, Command>> knownCommands;


  /**
   * The constructor is used to initialize the class variables.
   *
   * @param in  represents the input stream.
   * @param out represents the output stream.
   */
  MainController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
    configure();
  }

  private void configure() {
    knownCommands = new HashMap<>();
    // knownCommands.put("load", s -> new LoadCommand(scan.nextLine()));
  }

  /**
   * This method is used as the main method which determines all the steps to be performed.
   *
   * @param imageControllerImp represents the image controller.
   * @param image              represents an image.
   * @throws IOException throws exception when inout is not valid.
   */
  public void startProgram(ImageController imageControllerImp, Image image, ImageView view)
          throws IOException {
    Objects.requireNonNull(image);
    Scanner scan = new Scanner(this.in);
    while (menuScript(scan, image, imageControllerImp, view)) {
      ;
    }
  }


//----------------------------------------------------------------------------------------------

  public boolean menuScript(Scanner scan, Image image, ImageController imageControllerImp, ImageView view)
          throws IOException, NoSuchElementException {
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
      case "run":
        // command = new RunFile(scan, image, imageControllerImp, view);
        String scriptFilePath = scan.next();
        int lastDotIndex = scriptFilePath.lastIndexOf(".");
        if (lastDotIndex > 0 && !scriptFilePath.substring(lastDotIndex + 1)
                .equals("txt")) {
          throw new IOException("invalid script being used. only txt files are allowed. "
                  + "Try again\n");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {
          String line;
          while ((line = reader.readLine()) != null) {
            menuScript(new Scanner(line), image, imageControllerImp, view);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case "q":
        return false;
      default:
        //        throw new IllegalArgumentException("Invalid command: " + t);
        startProgram(imageControllerImp, image, view);

    }

    if (command != null && !t.equals("run")) {
      command.execute();
    }


    return true;
  }

  /**
   * This is the main function form which the program starts.
   *
   * @param args represents various arguments from the command line.
   */
  public static void main(String[] args) {

    Image imageModel = new RGBImage(0, 0, 0);
    ImageView view = new TextView(System.out);
    ImageController imageController = new ImageControllerImp(imageModel);

    try {
      new MainController(new InputStreamReader(System.in), System.out)
              .startProgram(imageController, imageModel, view);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}


