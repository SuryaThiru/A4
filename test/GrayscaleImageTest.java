import org.junit.Test;

import java.util.Scanner;

import model.GrayscaleImage;
import model.Image;

import static helper.ImageUtil.ppmFileValidation;

/**
 * This class represents the tests for the greyscale images.
 */
public class GrayscaleImageTest {

  @Test(expected = IllegalArgumentException.class)
  public void testLoad() {
    String imagePath = "res/images/flower-greyscale.ppm";

    Image model = new GrayscaleImage(0, 0, 0, null);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
  }

  private String extractContent(Scanner sc) {
    sc.useDelimiter("\\A");
    return sc.hasNext() ? sc.next() : null;
  }


}