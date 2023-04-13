import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.Reader;
import java.io.StringReader;
import java.util.Random;

import controller.CommandController;
import controller.ImageController;
import controller.ImageControllerImp;
import model.Pixel;
import model.PixelImpl;
import model.RGBImage;
import model.Image;
import view.ImageView;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the various operations performed by the Image Controller.
 */
public class ImageControllerTest {

  Image image;

  StringBuilder sb;

  Image model;

  ImageController ic;

  ImageView iv;
  Pixel[][] pixels;

  @Before
  public void setUp() {
    pixels = new Pixel[2][2];
    Random rand = new Random();
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        int[] channels = new int[3];
        channels[0] = rand.nextInt(255);
        channels[1] = rand.nextInt(255);
        channels[2] = rand.nextInt(255);

        pixels[i][j] = new PixelImpl(channels);
      }
    }

    image = new RGBImage(2, 2, 255, pixels);

    sb = new StringBuilder();
    model = new ImageMock(sb);
    ic = new ImageControllerImp(model);
    iv = new TextView(System.out);
  }

  private class ImageMock implements Image {

    private final StringBuilder log;

    private ImageMock(StringBuilder log) {
      this.log = log;
    }


    @Override
    public Image load(String content) {
      log.append("load-string\n");
      return this;
    }

    @Override
    public Image load(BufferedImage images) {
      log.append("load-buffered-image\n");
      return this;
    }

    @Override
    public void flipHorizontal() {
      log.append("flip-horizontal\n");
    }

    @Override
    public void flipVertical() {
      log.append("flip-vertical\n");
    }

    @Override
    public void brighten(int increment) {
      log.append("brighten by " + increment + "\n");
    }

    @Override
    public Image duplicate() {
      log.append("duplicate image\n");
      return this;
    }

    @Override
    public Image[] splitChannels() throws UnsupportedOperationException {
      log.append("split-channel\n");
      return new Image[0];
    }

    @Override
    public void combineChannels(Image[] channels) throws UnsupportedOperationException {
      log.append("combine-channels\n");
    }

    @Override
    public Image combineByValue() {
      log.append("combine-by-value\n");
      return this;
    }

    @Override
    public Image combineByIntensity() {
      log.append("combine-by-intensity\n");
      return this;
    }

    @Override
    public Image combineByLuma() throws UnsupportedOperationException {
      log.append("combine-by-luma\n");
      return this;
    }

    @Override
    public boolean compareImages(Image updatedImage) throws UnsupportedOperationException {
      log.append("compare-images\n");
      return true;
    }

    @Override
    public boolean validateCombineChannels(Image[] channels) throws IllegalArgumentException {
      log.append("validate-combine-channels\n");
      return true;
    }

    @Override
    public boolean isGrayscale() {
      log.append("is-grayscale\n");
      return false;
    }

    @Override
    public int getWidth() {
      log.append("get-width\n");
      return 0;
    }

    @Override
    public int getHeight() {
      log.append("get-height\n");
      return 0;
    }

    @Override
    public Pixel getPixel(int x, int y) {
      log.append("get-pixel\n");
      return pixels[x][y];
    }

    @Override
    public void blur() {
      log.append("blur\n");
    }

    @Override
    public void sharpen() {
      log.append("sharpen\n");
    }

    @Override
    public int getMaxColorValue() {
      log.append("get-max-color-value\n");
      return 0;
    }

    @Override
    public Image combineBySepia() throws UnsupportedOperationException {
      log.append("combine-by-sepia\n");
      return this;
    }

    @Override
    public Image dither() throws UnsupportedOperationException {
      log.append("dither\n");
      return this;
    }

    @Override
    public int[][] calculateHistogram() {
      return new int[0][];
    }
  }

  @Test
  public void testLoadScript() {
    Reader in = new StringReader("run res/scripts/testScript2.txt\nq");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("load-string\n", sb.toString());
    assertEquals("loaded fractal successfully\n"
            + "application ended\n", iv.outputString().toString());

  }

  @Test
  public void testLoadScriptWrongFile() {
    Reader in = new StringReader("run res/scripts/flower.ppm\nrun images/flower.ppm\nq");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("", sb.toString());
    assertEquals("invalid script being used. only txt files are allowed. Try again\n\n"
                    + "invalid script being used. only txt files are allowed. Try again\n\n"
                    + "application ended\n",
            iv.outputString().toString());
  }


  @Test
  public void testLoadTerminal() {
    Reader in = new StringReader("load res/images/flower.ppm flower \nq");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("load-string\n", sb.toString());
    assertEquals("loaded flower successfully\n"
            + "application ended\n", iv.outputString().toString());

  }

  @Test
  public void testLoadTerminalWrongFile() {
    Reader in = new StringReader("load res/images/test-image.ppm koala\nq");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("", sb.toString());
    assertEquals("Invalid file / file name\n"
            + "application ended\n", iv.outputString().toString());
  }

  @Test
  public void testSaveBeforeLoad() {
    Reader in = new StringReader("save res/images/flower.ppm fractal\nq");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("", sb.toString());
    assertEquals("image not found\n"
            + "application ended\n", iv.outputString().toString());
  }

  @Test
  public void testSaveTerminal() {
    Reader in = new StringReader("load res/images/flower.ppm fractal"
            + "\nsave res/images/flower-save-mock.ppm fractal\nq");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("load-string\n"
            + "get-width\n"
            + "get-height\n"
            + "get-max-color-value\n", sb.toString());
    assertEquals("loaded fractal successfully\n"
            + "saved fractal successfully\n"
            + "application ended\n", iv.outputString().toString());
  }

  @Test
  public void testSaveScript() {
    Reader in = new StringReader("run res/scripts/testScript1.txt\nq");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("load-string\nduplicate image\n"
            + "brighten by 10\n"
            + "duplicate image\n"
            + "flip-vertical\n"
            + "get-width\n"
            + "get-height\n"
            + "get-max-color-value\n", sb.toString());
    assertEquals("loaded fractal successfully" + "\n"
            + "increased the brightness of fractal by 10 to fractal-brighter successfully" + "\n"
            + "flipped fractal-brighter to fractal-brighter-vertical vertically successfully"
            + "\nsaved fractal-brighter-vertical successfully\n"
            + "application ended\n", iv.outputString().toString());
  }

  @Test
  public void testDoubleLoad() {
    Reader in = new StringReader("load res/images/flower.ppm fractal"
            + "\n load res/images/flower-brightened.ppm flower-brightened \nq");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("load-string\nload-string\n", sb.toString());
    assertEquals("loaded fractal successfully" + "\n"
            + "loaded flower-brightened successfully" + "\n"
            + "application ended\n", iv.outputString().toString());
  }

  @Test
  public void testCombineBySepiaPPM() {
    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "sepia-tone fractal fractal-sepia\n"
            + "save res/images/fractal-sepia-mock.ppm fractal-sepia\n"
            + "q");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("load-string\nduplicate image\n"
            + "combine-by-sepia\n"
            + "get-width\n"
            + "get-height\n"
            + "get-max-color-value\n", sb.toString());
    assertEquals("loaded fractal successfully" + "\n"
            + "converting fractal to a sepia-toned Image - fractal-sepia is successful\n"
            + "saved fractal-sepia successfully"
            + "\napplication ended\n", iv.outputString().toString());
  }

  @Test
  public void testCombineBySepiaPNG() {
    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "sepia-tone fractal fractal-sepia\n"
            + "save res/images/fractal-sepia.ppm fractal-sepia\n"
            + "q");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("load-string\nduplicate image\n"
            + "combine-by-sepia\n"
            + "get-width\n"
            + "get-height\n"
            + "get-max-color-value\n", sb.toString());
    assertEquals("loaded fractal successfully" + "\n"
            + "converting fractal to a sepia-toned Image - fractal-sepia is successful\n"
            + "saved fractal-sepia successfully"
            + "\napplication ended\n", iv.outputString().toString());
  }

  @Test
  public void testDither() {
    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "dither fractal fractal-dithered\n"
            + "save res/images/fractal-dithered-mock.ppm fractal-dithered\n"
            + "q");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("load-string\nduplicate image\n"
            + "dither\n"
            + "get-width\n"
            + "get-height\n"
            + "get-max-color-value\n", sb.toString());
    assertEquals("loaded fractal successfully" + "\n"
            + "dither conversion of fractal to fractal-dithered is successful\n"
            + "saved fractal-dithered successfully"
            + "\n"
            + "application ended\n", iv.outputString().toString());
  }

  @Test
  public void testBlur() {
    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "blur fractal fractal-blur\n"
            + "save res/images/flower-blur-mock.ppm fractal-blur\n"
            + "q");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("load-string\nduplicate image\n"
            + "blur\n"
            + "get-width\n"
            + "get-height\n"
            + "get-max-color-value\n", sb.toString());
    assertEquals("loaded fractal successfully" + "\n"
            + "blurred fractal to fractal-blur successfully\n"
            + "saved fractal-blur successfully"
            + "\n"
            + "application ended\n", iv.outputString().toString());
  }

  @Test
  public void testSharpen() {
    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "sharpen fractal fractal-sharpen\n"
            + "save res/images/flower-sharpen.ppm fractal-sharpen\n"
            + "q");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("load-string\nduplicate image\n"
            + "sharpen\n"
            + "get-width\n"
            + "get-height\n"
            + "get-max-color-value\n", sb.toString());
    assertEquals("loaded fractal successfully" + "\n"
            + "sharpened fractal to fractal-sharpen successfully\n"
            + "saved fractal-sharpen successfully"
            + "\n"
            + "application ended\n", iv.outputString().toString());
  }

  @Test
  public void testStackSharpen() {
    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "sharpen fractal fractal-sharpen\n"
            + "sharpen fractal-sharpen fractal-sharpen-sh\n"
            + "save res/images/flower-sharpen-sh-mock.ppm fractal-sharpen-sh\n"
            + "q");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("load-string\nduplicate image\n"
            + "sharpen\n"
            + "duplicate image\n"
            + "sharpen\n"
            + "get-width\n"
            + "get-height\n"
            + "get-max-color-value\n", sb.toString());
    assertEquals("loaded fractal successfully" + "\n"
            + "sharpened fractal to fractal-sharpen successfully\n"
            + "sharpened fractal-sharpen to fractal-sharpen-sh successfully\n"
            + "saved fractal-sharpen-sh successfully"
            + "\n"
            + "application ended\n", iv.outputString().toString());
  }

  @Test
  public void testStackBlur() {
    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "blur fractal fractal-blur\n"
            + "blur fractal-blur fractal-blur-blur\n"
            + "save res/images/flower-blur2-mock.ppm fractal-blur-blur\n"
            + "q");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("load-string\nduplicate image\n"
            + "blur\n"
            + "duplicate image\n"
            + "blur\n"
            + "get-width\n"
            + "get-height\n"
            + "get-max-color-value\n", sb.toString());
    assertEquals("loaded fractal successfully" + "\n"
            + "blurred fractal to fractal-blur successfully\n"
            + "blurred fractal-blur to fractal-blur-blur successfully\n"
            + "saved fractal-blur-blur successfully"
            + "\n"
            + "application ended\n", iv.outputString().toString());
  }

  @Test
  public void testLoadJpegAndSavePPM() {
    Reader in = new StringReader("load res/images/flower.jpeg fractal\n"
            + "dither fractal fractal-dither\n"
            + "save res/images/flower-dither-mock.ppm fractal-dither\n"
            + "q");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("load-buffered-image\n"
            + "duplicate image\n"
            + "dither\n"
            + "get-width\n"
            + "get-height\n"
            + "get-max-color-value\n", sb.toString());
    assertEquals("loaded fractal successfully" + "\n"
            + "dither conversion of fractal to fractal-dither is successful\n"
            + "saved fractal-dither successfully\n"
            + "application ended\n", iv.outputString().toString());
  }
}