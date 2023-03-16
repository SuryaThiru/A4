import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import model.GrayscaleImage;
import model.Image;
import model.Pixel;
import model.RGBImage;

import static helper.ImageUtil.ppmFileValidation;
import static org.junit.Assert.*;

public class RGBImageTest {

  @Test(expected = NullPointerException.class)
  public void testWrongFile() {
    String imagePath = "images/flower.txt";
    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
  }

  @Test
  public void testFlipHorizontal() {
    String imagePath = "images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.flipHorizontal();
    //model.save("images/flower-flip-horizontal.ppm");
    assertEquals(true, checkImages(model, "images/flower-flip-horizontal.ppm"));
  }

  @Test
  public void testFlipVertical() throws IOException {
    String imagePath = "images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.flipVertical();
    //model.save("images/flower-flip-vertical.ppm");
    assertEquals(true, checkImages(model, "images/flower-flip-vertical.ppm"));
  }

  @Test
  public void testBrighten() {
    String imagePath = "images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.brighten(25);
//    model.save("images/flower-brighten.ppm");
    assertEquals(true, checkImages(model, "images/flower-brighten.ppm"));
  }

  @Test
  public void testBrightenNegative() {
    String imagePath = "images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.brighten(-25);
//    model.save("images/flower-brighten.ppm");
    assertEquals(true, checkImages(model, "images/flower-darken.ppm"));
  }

  @Test
  public void testMaxBrighten() {
    String imagePath = "images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.brighten(250);
//    model.save("images/flower-max-brighten.ppm");
    assertEquals(true, checkImages(model, "images/flower-max-brighten.ppm"));
  }

  @Test
  public void testDarken() {
    String imagePath = "images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.darken(25);
    //model.save("images/flower-darken.ppm");
    assertEquals(true, checkImages(model, "images/flower-darken.ppm"));

  }

  @Test
  public void testDarkenNegative() {
    String imagePath = "images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.darken(-25);
    //model.save("images/flower-darken.ppm");
    assertEquals(true, checkImages(model, "images/flower-brighten.ppm"));

  }

  @Test
  public void testMaxDarken() {
    String imagePath = "images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.darken(250);
    //model.save("images/flower-max-darken.ppm");
    assertEquals(true, checkImages(model, "images/flower-max-darken.ppm"));
  }

  @Test
  public void testSplit() {
    String imagePath = "images/flower.ppm";

    String redImagePath = "images/flower-red.ppm";
    String greenImagePath = "images/flower-green.ppm";
    String blueImagePath = "images/flower-blue.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    Image[] channels = model.splitChannels();

    assertEquals(3, channels.length);

//    Image flowerRedModel = new RGBImage(0, 0, 0);
//    Scanner scRed = ppmFileValidation(redImagePath);
//    String contentRed = extractContent(scRed);
//    flowerRedModel.load(contentRed);
//    assertEquals(true,channels[0].compareImages(flowerRedModel));
//
//    Image flowerGreenModel = new RGBImage(0, 0, 0);
//    Scanner scGreen = ppmFileValidation(greenImagePath);
//    String contentGreen = extractContent(scGreen);
//    flowerGreenModel.load(contentGreen);
//    assertEquals(true,channels[1].compareImages(flowerGreenModel));
//
//    Image flowerBlueModel = new RGBImage(0, 0, 0);
//    Scanner scBlue = ppmFileValidation(blueImagePath);
//    String contentBlue = extractContent(scBlue);
//    flowerBlueModel.load(contentBlue);
//    assertEquals(true,channels[2].compareImages(flowerBlueModel));


  }

  @Test
  public void testCombine() {

    String imagePath = "images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    Image modelCopy = model;
    Image[] channels = model.splitChannels();

    model.combineChannels(channels);
    assertEquals(true, model.compareImages(modelCopy));
  }

  @Test
  public void testDuplicate() {
    String imagePath = "images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    Image duplicate = model.duplicate();
    assertEquals(true, duplicate instanceof GrayscaleImage);
  }

  @Test
  public void testCombineByIntensity() {
    String imagePath = "images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);

    Image x = model.combineByIntensity();
    assertTrue(x instanceof GrayscaleImage);
  }

  @Test
  public void testCombineByValue() {
    String imagePath = "images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);

    Image x = model.combineByValue();
    assertTrue(x instanceof GrayscaleImage);
  }

  @Test
  public void testCombineByLuma() {
    String imagePath = "images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);

    Image x = model.combineByLuma();
    assertTrue(x instanceof GrayscaleImage);
  }

  private boolean checkImages(Image model, String imagePath) {

    // stored flipped image.
    Image originalModel = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    originalModel.load(content);

    // check the current flipped image with the stored image.

    int height = model.getHeight();
    int width = model.getWidth();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel a = model.getPixel(x, y);
        Pixel b = originalModel.getPixel(x, y);
        if (!a.equals(b)) {
          return false;
        }
      }
    }
    return true;
  }

  private String extractContent(Scanner sc) {
    sc.useDelimiter("\\A");
    return sc.hasNext() ? sc.next() : null;
  }

  /**
   * Allow non-contiguous scripting
   * <p>
   * Flipping should not change the size of the image.
   * <p>
   * Allow combined flipping - horizontal followed by vertical flip, or vice versa.
   * <p>
   * Brighten
   * <p>
   * Complete dark to visible  255
   * <p>
   * Normal - visible to brighter
   * <p>
   * Max bright to max bright
   * <p>
   * Darken
   * <p>
   * Complete dark to dark 0
   * <p>
   * Normal - visible to darker
   * <p>
   * Max bright to dark
   * <p>
   * Greyscale
   * <p>
   * Greyscale with value, intensity or luma
   * <p>
   * Grey to grey ?
   * <p>
   * Combining channels into one image
   * <p>
   * Load an image from an ASCII PPM file
   * <p>
   * Create images that visualize individual R,G,B components of an image.
   * <p>
   * Create images that visualize the value, intensity or luma of an image as defined above.
   * <p>
   * Split a single image into 3 images representing each of the three channels
   * <p>
   * Combine three greyscale image into a single color image whose R,G,B values come from the three images
   * <p>
   * Save an image to an ASCII PPM file.
   * <p>
   * Test text-based scripting.
   * <p>
   * Write very thorough documentation.
   * <p>
   * Null objects
   */

}