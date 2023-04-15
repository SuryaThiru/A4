import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.CommandController;
import controller.GUIController;
import controller.ImageController;
import controller.ImageControllerImp;
import model.Image;
import model.RGBImage;
import view.GUIView;
import view.GUIViewImp;
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
    ImageController imageController = new ImageControllerImp(imageModel);
    if (args.length > 1 && args[0].equals("-file")) {
      try {
        ImageView view = new TextView(System.out);
        String fileName = args[1];
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        new CommandController(reader, imageController, view).startProgram();
        reader.close();
        System.exit(0);
      } catch (IOException e) {
        System.err.println("Failed to read script file: " + e.getMessage());
        System.exit(1);
      }
    } else if (args.length > 1 && args[0].equals("-text")) {
      ImageView view = new TextView(System.out);
      new CommandController(new InputStreamReader(System.in), imageController, view)
              .startProgram();
    } else {
      ImageView view = new TextView(System.out);
      new CommandController(new InputStreamReader(System.in), imageController, view)
              .startProgram();
      GUIView guiView = new GUIViewImp("Image Manipulator");
      new GUIController(imageController, guiView);
    }
  }
}
