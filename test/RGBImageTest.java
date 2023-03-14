import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;

import javax.imageio.ImageIO;

import controller.ImageController;
import controller.ImageControllerImp;
import model.Image;
import model.RGBImage;

import static org.junit.Assert.*;

/**
 * Two ways to test this.
 * 1. Use inbuilt BufferedImage class and add all that logic in a Mock class and check with the actual result.
 * 2. Save the to-be-tested image result files already in the images folder and compare them after getting the result form our implementation.
 *
 */

public class RGBImageTest {

  @Test
  public void testWithScript() throws IOException {

//    InputStream in = null;
//    try {
//      in = new FileInputStream("input.txt");
//    } catch (FileNotFoundException e) {
//      fail("File not present.");
//    }
//
//    int width = 0, height = 0, maxValue = 0;
//
    Image model = new RGBImage(0, 0, 0);
    ImageController controller = new ImageControllerImp(model);
    controller.Load("images/Koala.ppm", "Koala");
    controller.Brighten(10, "Koala", "Koala-brighten");


//    ImageUtil.readPPM("images/Koala.ppm");

  }

  @Test
  public void testSave() throws IOException {

    Image model = new RGBImage(0, 0, 0);
    ImageController controller = new ImageControllerImp(model);
    controller.Load("images/test-image.ppm", "img");
    controller.Save("images/test-image-ds.ppm", "img");
    controller.Load("images/test-image-ds.ppm", "img-dup");
//    PPMImageController.testBrighten(10);

  }

  @Test
  public void testHorizontalFlip() throws IOException {
    Image model = new RGBImage(0, 0, 0);
    ImageController controller = new ImageControllerImp(model);
    controller.Load("images/Koala.ppm", "Koala");
    controller.FlipHorizontal("Koala", "Koala-horizontalFlip");
    controller.Save("images/testHorizontal.ppm", "Koala-horizontalFlip");
  }

  @Test
  public void testVerticalFlip() throws IOException {
    Image model = new RGBImage(0, 0, 0);
    ImageController controller = new ImageControllerImp(model);
    controller.Load("images/Koala.ppm", "Koala");
    controller.FlipHorizontal("Koala", "Koala-verticalFlip");
    controller.Save("images/testVertical.ppm", "Koala-verticalFlip");
  }

  @Test
  public void testBrighten() throws IOException {
    Image model = new RGBImage(0, 0, 0);
    ImageController controller = new ImageControllerImp(model);
    controller.Load("images/Koala.ppm", "Koala");
    controller.Brighten(30, "Koala", "Koala-brighten");
    controller.Save("images/testBrighten.ppm", "Koala-brighten");
  }

  @Test
  public void testDarken() throws IOException {
    Image model = new RGBImage(0, 0, 0);
    ImageController controller = new ImageControllerImp(model);
    controller.Load("images/Koala.ppm", "Koala");
    controller.Darken(30, "Koala", "Koala-darken");
    controller.Save("images/testDarken.ppm", "Koala-darken");
  }




  // Method 1
  // Helper method to method 2
  public static int Truncate(int value)
  {

    if (value < 0) {
      value = 0;
    }
    else if (value > 255) {
      value = 255;
    }
    return value;
  }

  public static void AdjustBrightness(String inpPath,
                                      String outPath)
          throws IOException
  {

    // Taking image path and reading pixels
    File f = new File(inpPath);
    BufferedImage image = ImageIO.read(f);

    // Declaring an array for spectrum of colors
    int rgb[];

    // Setting custom brightness
    int brightnessValue = 25;

    // Outer loop for width of image
    for (int i = 0; i < image.getWidth(); i++) {

      // Inner loop for height of image
      for (int j = 0; j < image.getHeight(); j++) {

        rgb = image.getRaster().getPixel(
                i, j, new int[3]);

        // Using(calling) method 1
        int red
                = Truncate(rgb[0] + brightnessValue);
        int green
                = Truncate(rgb[1] + brightnessValue);
        int blue
                = Truncate(rgb[2] + brightnessValue);

        int arr[] = { red, green, blue };

        // Using setPixel() method
        image.getRaster().setPixel(i, j, arr);
      }
    }

    // Throwing changes over the image as read above
    ImageIO.write(image, "jpg", new File(outPath));
  }

  /**
   *
   */

}