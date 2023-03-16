import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import controller.ImageController;
import controller.ImageControllerImp;
import model.GrayscaleImage;
import model.Image;
import model.Pixel;
import model.RGBImage;

import static helper.ImageUtil.getPixels;
import static helper.ImageUtil.ppmFileValidation;
import static org.junit.Assert.*;

/**
 * This class tests the whole application end-to-end.
 */
public class ImageProcessorTest {

  MockModel mockImage;
  @Before
  public void setup() {
    mockImage = new MockModel();
  }

  @Test
  public void testLoad() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/mockImage.ppm mockImage q");

    MainController controller = new MainController(in, out);
    ImageController ic = new ImageControllerImp(mockImage);
    controller.go(ic, mockImage);

    assertEquals("loaded mockImage successfully\n", out.toString());

  }

  @Test
  public void testFlipHorizontal() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load images/mockImage.ppm mockImage\n"
            + "horizontal-flip mockImage mockImage-horizontal\n "
            + "compare mockImage mockImage-horizontal\n");

    MainController controller = new MainController(in, out);
    ImageController ic = new ImageControllerImp(mockImage);
    controller.go(ic, mockImage);
    assertEquals("loaded mockImage successfully\n", out.toString());

//    controller.go(model, view);
  }

  static class MockModel implements Image {
    MockModel mainImage;
    int height;
    int width;
    int maxColorValue;
    Pixel[][] pixels;

    public MockModel() {
      this.height = 512;
      this.width = 512;
      this.maxColorValue = 255;
      pixels = new Pixel[height][width];

      for( int i=0 ; i<height ; i++) {
        for ( int j = 0 ; j<width ; j++) {
          pixels[i][j] = new Pixel(50,60,70);
        }
      }
    }


    public Pixel[][] getPixels() {
      return pixels;
    }
    @Override
    public Image load(String content) {
//      String imagePath = "images/flower.ppm";
//
//      Scanner sc1 = ppmFileValidation(imagePath);
//      String c = extractContent(sc1);
      Scanner sc = new Scanner(content);
      width = sc.nextInt();
      height = sc.nextInt();
      maxColorValue = sc.nextInt();

      pixels = new Pixel[height][width];

      boolean isGrayscale = true;

      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          if(!(r == g && r == b)) {
            isGrayscale = false;
          }
          pixels[i][j] = new Pixel(r, g, b);
        }
      }

      if (isGrayscale) {
        return new GrayscaleImage(width, height, maxColorValue, pixels);
      }

      //split();
      return this;
    }

    @Override
    public void save(String filePath) throws IOException {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        writer.write("P3" + "\n");
        writer.write(width + " " + height + "\n");
        writer.write(maxColorValue + "\n");

        // Write the image data
        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            Pixel pixel = pixels[i][j];
            writer.write(pixel.getChannels(0) + " " + pixel.getChannels(1)
                    + " " + pixel.getChannels(2) + " ");
          }
          writer.newLine();
        }
      }
    }

    @Override
    public void flipHorizontal() {
      //
    }

    @Override
    public void flipVertical() {
      //
    }

    @Override
    public void brighten(int increment) {
      if (increment < 0) {
        darken(Math.abs(increment));
        return;
      }
      for( int i=0 ; i<height ; i++) {
        for ( int j = 0 ; j<width ; j++) {
          Pixel pixel = pixels[i][j];
          int newRed = Math.min(pixel.getChannels(0) + increment, maxColorValue);
          int newGreen = Math.min(pixel.getChannels(1) + increment, maxColorValue);
          int newBlue = Math.min(pixel.getChannels(2) + increment, maxColorValue);
          pixels[i][j] = new Pixel(newRed, newGreen, newBlue);
        }
      }
    }

    @Override
    public void darken(int decrement) {
      if (decrement < 0) {
        brighten(Math.abs(decrement));
        return;
      }
      for( int i=0 ; i<height ; i++) {
        for ( int j = 0 ; j<width ; j++) {
          Pixel pixel = pixels[i][j];
          int newRed = Math.min(pixel.getChannels(0) + decrement, 0);
          int newGreen = Math.min(pixel.getChannels(1) + decrement, 0);
          int newBlue = Math.min(pixel.getChannels(2) + decrement, 0);
          pixels[i][j] = new Pixel(newRed, newGreen, newBlue);
        }
      }
    }

    @Override
    public Image duplicate() {
      return this;
    }

    @Override
    public Image[] splitChannels() throws UnsupportedOperationException {
      return new Image[0];
    }

    @Override
    public void combineChannels(Image[] channels) throws UnsupportedOperationException {

    }

    @Override
    public Image combineByValue() {
      return null;
    }

    @Override
    public Image combineByIntensity() {
      return null;
    }

    @Override
    public Image combineByLuma() throws UnsupportedOperationException {
      return null;
    }

    @Override
    public boolean compareImages(Image updatedImage) throws UnsupportedOperationException {
      return false;
    }

    @Override
    public boolean validateCombineChannels(Image[] channels) throws IllegalArgumentException {
      return false;
    }

    @Override
    public boolean isGrayscale() {
      return false;
    }

    @Override
    public int getWidth() {
      return 0;
    }

    @Override
    public int getHeight() {
      return 0;
    }

    @Override
    public Pixel getPixel(int x, int y) {
      return null;
    }

    private String extractContent(Scanner sc) {
      sc.useDelimiter("\\A");
      return sc.hasNext() ? sc.next() : null;
    }
  }


  @Test
  public void testContiguousFlow() {

  }

}