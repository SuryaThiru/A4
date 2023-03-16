import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;

import controller.ImageController;
import controller.ImageControllerImp;
import model.RGBImage;
import model.Image;

import static org.junit.Assert.*;

/**
 * This class is used to test the various operations performed by the Image Controller.
 */
public class ImageControllerTest {

  private HashMap<String, Image> images;

  private Image image;

  @Test
  public void testLoadScript() {

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("res/scripts/testScript1.txt");


    Image model = new RGBImage(0, 0, 0);
    Image mockModel = new Mock(model); // load actual image
    ImageController controller= new ImageControllerImp(mockModel);
//    ImagerViewer viewer = new ImageVimewerImpl();
//    ImageController controller = new ImageControllerImp(in, out);
//
//    controller.go(model, view);
    assertEquals("text from the script", out.toString());

  }

  @Test(expected = IOException.class)
  public void testLoadScriptWrongFile() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("res/images/testScriptFile.png");

    Image model = new RGBImage(0, 0, 0);
    ImagerViewer viewer = new ImageViewerImpl();
    ImageController controller = new ImageControllerImp(in, out);

  }

  @Test
  public void testLoadTerminal() {

    Image model = new RGBImage(0, 0, 0);
    ImagerViewer viewer = new ImageViewerImpl();

    Reader in = new StringReader("load res/images/test-image.ppm koala");
    StringBuffer out = new StringBuffer();

    ImageController controller = new ImageControllerImp(in, out);
    controller.go(model, view);

    assertEquals("loaded koala successfully", out.toString());
  }

  @Test(expected = IOException.class)
  public void testLoadTerminalWrongFile() {
    Image model = new RGBImage(0, 0, 0);
    ImagerViewer viewer = new ImageViewerImpl();

    Reader in = new StringReader("load res/images/test-image.ppm koala");
    StringBuffer out = new StringBuffer();

    ImageController controller = new ImageControllerImp(in, out);
  }

//  ----

  @Test(expected = IOException.class)
  public void testSaveBeforeLoad() {
    Image model = new RGBImage(0, 0, 0);
    ImagerViewer viewer = new ImageViewerImpl();

    Reader in = new StringReader("save res/images/test-image.ppm fractal");
    StringBuffer out = new StringBuffer();

    ImageController controller = new ImageControllerImp(in, out);
    controller.go(model, view);

    assertEquals("image not loaded", out.toString());
  }

  @Test
  public void testSaveTerminal() {
    Image model = new RGBImage(0, 0, 0);
    ImagerViewer viewer = new ImageViewerImpl();

    Reader in = new StringReader("load res/images/test-image.ppm fractal" +
            "\n save res/images/fractal.ppm saveFractal");
    StringBuffer out = new StringBuffer();

    ImageController controller = new ImageControllerImp(in, out);
    controller.go(model, view);

    assertEquals("loaded image fractal" + "\n"
            + "saved image saveFractal", out.toString());
  }

  /**
   * load res/images/test-image.ppm fractal
   * brighten 10 fractal fractal-brighter
   * vertical-flip fractal-brighter fractal-brighter-vertical
   * save res/images/fractal-brighter-vertical.ppm fractal-brighter-vertical
   */
  @Test
  public void testSaveScript() {
    Image model = new RGBImage(0, 0, 0);
    ImagerViewer viewer = new ImageViewerImpl();

    Reader in = new StringReader("res/scripts/testScript1.txt");
    StringBuffer out = new StringBuffer();

    ImageController controller = new ImageControllerImp(in, out);
    controller.go(model, view);

    assertEquals("loaded image fractal" + "\n"
            + "increased brightness by 10 for fractal" + "\n"
            + "successfully flipped fractal-brighter vertically" + "\n"
            + "saved image fractal-brighter-vertical", out.toString());
  }


  /**
   * This class is used as a Mock class for the Image Model to test its functionality.
   */
  class Mock implements Image {

    private Image image;

    public Mock(Image image) {
      this.image = image;
    }

    @Override
    public Image load(String content) {
      return null;
    }

    @Override
    public void save(String filePath) throws IOException {

    }

    @Override
    public void flipHorizontal() {

    }

    @Override
    public void flipVertical() {

    }

    @Override
    public void brighten(int increment) {

    }

    @Override
    public void darken(int decrement) {

    }

    @Override
    public Image duplicate() {
      return null;
    }

    @Override
    public Image[] splitChannels() throws UnsupportedOperationException {
      return new Image[0];
    }

    @Override
    public void combineChannels(Image[] channels) throws UnsupportedOperationException {

    }

    @Override
    public boolean isGrayscale() {
      return false;
    }
  }

}