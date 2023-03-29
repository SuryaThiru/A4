import java.io.InputStreamReader;

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
   * This is the main function form which the program starts.
   *
   * @param args represents various arguments from the command line.
   */
  public static void main(String[] args) {

    Image imageModel = new RGBImage(0, 0, 0);
    ImageView view = new TextView(System.out);
    ImageController imageController = new ImageControllerImp(imageModel);

    new CommandController(new InputStreamReader(System.in), System.out)
            .startProgram(imageController, imageModel, view);
  }
}
