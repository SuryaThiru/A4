import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import controller.CommandController;
import controller.ImageController;
import controller.ImageControllerImp;
import model.RGBImage;
import model.Image;
import view.ImageView;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the various operations performed by the Image Controller.
 */
public class ImageControllerTest {

  @Test
  public void testLoadScript() throws IOException {

    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("run res/scripts/testScript2.txt\nq");

    Image model = new RGBImage(0, 0, 0);
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
            +"greyscale sepia-component fractal fractal-sepia\n"
            +"save res/images/fractal-sepia.ppm fractal-sepia\n"
            +"q");
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
            +"greyscale sepia-component fractal fractal-sepia\n"
            +"save res/images/fractal-sepia.ppm fractal-sepia\n"
            +"q");
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
            +"dither fractal fractal-dithered\n"
            +"save res/images/fractal-dithered.ppm fractal-dithered\n"
            +"q");
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

}