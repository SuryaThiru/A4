import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.GrayscaleImage;
import model.Image;
import model.Pixel;
import model.RGBImage;

import static helper.ImageUtil.ppmFileValidation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class represents the test for RGB class.
 */
public class RGBImageTest {

  @Test(expected = NullPointerException.class)
  public void testWrongFile() {
    String imagePath = "res/images/flower.txt";
    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
  }

  @Test
  public void testFlipHorizontal() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.flipHorizontal();
    // model.save("res/images/flower-flipped-horizontal.ppm");
    assertTrue(checkImages(model, "res/images/flower-flipped-horizontal.ppm"));
  }

  @Test
  public void testFlipVertical() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.flipVertical();
    //model.save("res/images/flower-flipped-vertical.ppm");
    assertTrue(checkImages(model, "res/images/flower-flipped-vertical.ppm"));
  }

  @Test
  public void testBrighten() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.brighten(25);
    assertTrue(checkImages(model, "res/images/flower-brightened.ppm"));
  }

  @Test
  public void testBrightenDarken() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.brighten(25);
    model.brighten(-25);
    assertTrue(checkImages(model, "res/images/flower.ppm"));
  }

  @Test
  public void testBrightenNegative() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.brighten(-25);
    assertTrue(checkImages(model, "res/images/flower-darkened.ppm"));
  }

  @Test
  public void testMaxBrighten() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.brighten(255);
    assertTrue(checkImages(model, "res/images/flower-max-brighten.ppm"));
  }

  @Test
  public void testDarken() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.brighten(-25);
    assertTrue(checkImages(model, "res/images/flower-darkened.ppm"));

  }


  @Test
  public void testMaxDarken() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.brighten(-255);
    //model.save("images/flower-max-darken.ppm");
    assertEquals(true, checkImages(model, "res/images/flower-max-darken.ppm"));
  }

  @Test
  public void testSplit() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    Image[] channels = model.splitChannels();

    assertEquals(3, channels.length);

  }

  @Test
  public void testCombine() {

    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    Image modelCopy = model;
    Image[] channels = model.splitChannels();

    model.combineChannels(channels);
    assertTrue(model.compareImages(modelCopy));
  }

  @Test
  public void testDuplicate() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    Image duplicate = model.duplicate();
    assertTrue(duplicate instanceof RGBImage);
  }

  @Test
  public void testCombineByIntensity() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);

    Image x = model.combineByIntensity();
    assertTrue(x instanceof GrayscaleImage);
  }

  @Test
  public void testCombineByValue() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);

    Image x = model.combineByValue();
    assertTrue(x instanceof GrayscaleImage);
  }

  @Test
  public void testCombineByLuma() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);

    Image x = model.combineByLuma();
    assertTrue(x instanceof GrayscaleImage);
  }


  //  @Test
  //  public void testJpegLoading() throws IOException {
  //    String imagePath = "res/images/flower.jpeg";
  //    Image model = new RGBImage(0, 0, 0);
  //    model.load(ImageIO.read(new File(imagePath)));
  //    model.brighten(70);
  //    model.flipHorizontal();
  //    model.flipHorizontal();
  //    model.brighten(-70);
  //    assertTrue(checkImages(model, "res/images/flower.ppm"));
  //  }

  @Test
  public void testPngLoading() throws IOException {
    String imagePath = "res/images/flower.png";
    Image model = new RGBImage(0, 0, 0);
    model.load(ImageIO.read(new File(imagePath)));
    model.brighten(70);
    model.flipHorizontal();
    model.flipHorizontal();
    model.brighten(-70);
    assertTrue(checkImages(model, "res/images/flower.ppm"));
  }


  //  @Test
  //  public void testBmpLoading() throws IOException {
  //    String imagePath = "res/images/flower.bmp";
  //    Image model = new RGBImage(0, 0, 0);
  //    model.load(ImageIO.read(new File(imagePath)));
  //    model.brighten(70);
  //    model.flipHorizontal();
  //    model.flipHorizontal();
  //    model.brighten(-70);
  //    assertTrue(checkImages(model, "res/images/flower.ppm"));
  //  }

  @Test
  public void testBlur() {
    String imagePath = "res/images/flower.ppm";

    RGBImage model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.blur();
    //model.save("res/images/flower-blurred.ppm");
    assertTrue(checkImages(model, "res/images/flower-blurred.ppm"));
  }

  @Test
  public void testStackBlur() {
    String imagePath = "res/images/flower.ppm";

    RGBImage model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.blur();
    model.blur();
    //model.save("res/images/flower-blurred.ppm");
    assertTrue(checkImages(model, "res/images/flower-blur-bl.ppm"));
  }

  @Test
  public void testSharpen() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.sharpen();
    //model.save("res/images/flower-sharpened.ppm");
    assertTrue(checkImages(model, "res/images/flower-sharpened.ppm"));
  }

  @Test
  public void testStackSharpen() {
    String imagePath = "res/images/flower.ppm";

    Image model = new RGBImage(0, 0, 0);
    Scanner sc = ppmFileValidation(imagePath);
    String content = extractContent(sc);
    model.load(content);
    model.sharpen();
    model.sharpen();
    //model.save("res/images/flower-sharpened.ppm");
    assertTrue(checkImages(model, "res/images/flower-sharpen-sh.ppm"));
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

}