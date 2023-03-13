import java.io.IOException;

import controller.ImageController;
import controller.ImageControllerImp;
import model.Image;
import model.RGBImage;

public class ImageProcessor {
  public static void main(String[] args) throws IOException {
    Image model = new RGBImage(0, 0, 0);
    ImageController controller = new ImageControllerImp(model);
    controller.Load("images/Koala.ppm", "Koala");
  }

}