import java.io.IOException;

import controller.ImageController;
import controller.ImageControllerImp;
import model.Image;
import model.RGBImage;

public class ImageProcessor {
  public static void main(String[] args) throws IOException {
    Image model = new RGBImage(0, 0, 0);
    ImageController controller = new ImageControllerImp(model);
    controller.load("images/test-image.ppm", "flower");
    controller.save("images/test-image-d.ppm","flower");
    controller.split("flower", "flower-r", "flower-g",
            "flower-b");
    controller.combine("flower-new", "flower-r", "flower-g",
            "flower-b");
    controller.testCombine("flower", "flower-new");
  }

}