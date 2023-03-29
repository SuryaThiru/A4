import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import controller.CommandController;
import controller.ImageController;
import controller.ImageControllerImp;
import model.Pixel;
import model.RGBImage;
import model.Image;
import view.ImageView;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the various operations performed by the Image Controller.
 */
public class ImageControllerTest {

  private class ImageMock implements Image {

    private final StringBuilder log;

    private ImageMock(StringBuilder log) {
      this.log = log;
    }


    @Override
    public Image load(String content) {
      log.append("load-string\n");
      return null;
    }

    @Override
    public Image load(BufferedImage image) {
      log.append("load-buffered-image\n");
      return null;
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
      return null;
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
      return null;
    }

    @Override
    public Image combineByIntensity() {
      log.append("combine-by-intensity\n");
      return null;
    }

    @Override
    public Image combineByLuma() throws UnsupportedOperationException {
      log.append("combine-by-luma\n");
      return null;
    }

    @Override
    public boolean compareImages(Image updatedImage) throws UnsupportedOperationException {
      log.append("compare-images\n");
      return false;
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
      return null;
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
      return null;
    }

    @Override
    public Image dither() throws UnsupportedOperationException {
      log.append("dither\n");
      return null;
    }
  }

  @Test
  public void testLoadScript() throws IOException {

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("run res/scripts/testScript2.txt\nq");
    StringBuilder sb = new StringBuilder();

    Image model = new ImageMock(sb);
    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, model, iv);
    assertEquals("loaded fractal successfully\n", out.toString());

  }

  @Test(expected = IOException.class)
  public void testLoadScriptWrongFile() throws IOException {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("run res/scripts/flower.ppm\nrun images/flower.ppm\nq");

    Image model = new RGBImage(0, 0, 0);
    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, model, iv);
  }


  @Test
  public void testLoadTerminal() throws IOException {

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("load res/images/flower.ppm flower \nq");
    Image model = new RGBImage(0, 0, 0);

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, model, iv);

    assertEquals("loaded flower successfully\n", out.toString());

  }

  @Test(expected = IOException.class)
  public void testLoadTerminalWrongFile() throws IOException {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("load res/images/test-image.ppm koala");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, model, iv);

  }

  @Test(expected = IOException.class)
  public void testSaveBeforeLoad() throws IOException {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("save res/images/flower.ppm fractal");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, model, iv);
    assertEquals("image not loaded", out.toString());
  }

  @Test
  public void testSaveTerminal() throws IOException {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("load res/images/flower.ppm fractal" +
            "\nsave res/images/flower-save.ppm fractal\nq");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, model, iv);

    assertEquals("loaded fractal successfully" + "\n"
            + "saved fractal successfully\n", out.toString());
  }

  @Test
  public void testSaveScript() throws IOException {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("run res/scripts/testScript1.txt\nq");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, model, iv);

    assertEquals("loaded fractal successfully" + "\n"
            + "increased the brightness of fractal by 10 to fractal-brighter successfully" + "\n"
            + "flipped fractal-brighter to fractal-brighter-vertical vertically successfully"
            + "\nsaved fractal-brighter-vertical successfully\n", out.toString());
  }

  @Test
  public void testDoubleLoad() throws IOException {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("load res/images/flower.ppm fractal" +
            "\n load res/images/flower-brightened.ppm flower-brightened \nq");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, model, iv);

    assertEquals("loaded fractal successfully" + "\n"
            + "loaded flower-brightened successfully" + "\n", out.toString());
  }

  @Test
  public void testCombineBySepiaPPM() throws IOException {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "greyscale sepia-component fractal fractal-sepia\n"
            + "save res/images/fractal-sepia.ppm fractal-sepia\n"
            + "q");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, model, iv);

    assertEquals("loaded fractal successfully" + "\n"
            + "converting fractal to a sepia-toned Image - fractal-sepia is successful\n"
            + "saved fractal-sepia successfully"
            + "\n", out.toString());
  }

  @Test
  public void testCombineBySepiaPNG() throws IOException {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "greyscale sepia-component fractal fractal-sepia\n"
            + "save res/images/fractal-sepia.ppm fractal-sepia\n"
            + "q");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, model, iv);

    assertEquals("loaded fractal successfully" + "\n"
            + "converting fractal to a sepia-toned Image - fractal-sepia is successful\n"
            + "saved fractal-sepia successfully"
            + "\n", out.toString());
  }

  @Test
  public void testDither() throws IOException {
    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "dither fractal fractal-dithered\n"
            + "save res/images/fractal-dithered.ppm fractal-dithered\n"
            + "q");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, model, iv);

    assertEquals("loaded fractal successfully" + "\n"
            + "dither conversion of fractal to fractal-dithered is successful\n"
            + "saved fractal-dithered successfully"
            + "\n", out.toString());
  }

  @Test
  public void testCommandLineArgument() {

    Image model = new RGBImage(0, 0, 0);

    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "dither fractal fractal-dithered\n"
            + "save res/images/fractal-dithered.ppm fractal-dithered\n"
            + "q");
    StringBuffer out = new StringBuffer();

    CommandController controller = new CommandController(in, out);
    ImageController ic = new ImageControllerImp(model);
    ImageView iv = new TextView(System.out);
    controller.startProgram(ic, model, iv);

  }
}