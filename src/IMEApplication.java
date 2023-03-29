//import java.io.InputStreamReader;
//
//import controller.CommandController;
//import controller.ImageController;
//import controller.ImageControllerImp;
//import model.Image;
//import model.RGBImage;
//import view.ImageView;
//import view.TextView;
//
///**
// * This class represents the main application and consists of the main method.
// */
//public class IMEApplication {
//  /**
//   * This is the main function form which the program starts.
//   *
//   * @param args represents various arguments from the command line.
//   */
//  public static void main(String[] args) {
//
//    Image imageModel = new RGBImage(0, 0, 0);
//    ImageView view = new TextView(System.out);
//    ImageController imageController = new ImageControllerImp(imageModel);
//
//    new CommandController(new InputStreamReader(System.in), System.out)
//            .startProgram(imageController, imageModel, view);
//  }
//}

import java.io.*;

import controller.CommandController;
import controller.ImageController;
import controller.ImageControllerImp;
import model.Image;
import model.RGBImage;
import view.ImageView;
import view.TextView;

/**
 * This class represents the main application and consists of the main method.
 */
public class IMEApplication {
  /**
   * This is the main function from which the program starts.
   *
   * @param args represents various arguments from the command line.
   */
  public static void main(String[] args) {
    Image imageModel = new RGBImage(0, 0, 0);
    ImageView view = new TextView(System.out);
    ImageController imageController = new ImageControllerImp(imageModel);

    if (args.length > 1 && args[0].equals("-file")) {
      try {
        String fileName = args[1];
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        new CommandController(reader, System.out).startProgram(imageController, imageModel, view);
        reader.close();
        System.exit(0);
      } catch (IOException e) {
        System.err.println("Failed to read script file: " + e.getMessage());
        System.exit(1);
      }
    } else {
      new CommandController(new InputStreamReader(System.in), System.out)
              .startProgram(imageController, imageModel, view);
    }
  }
}
