import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.NoSuchElementException;
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
 * This class is used to test the application as a whole.
 */
public class IMEApplicationTest {

  StringBuilder sb;

  Image model;

  ImageController ic;

  ImageView iv;

  @Before
  public void setUp() {
    sb = new StringBuilder();
    model = new RGBImage(0, 0, 0);
    ic = new ImageControllerImp(model);
    iv = new TextView(System.out);
  }

  @Test
  public void testLoadScript() {
    Reader in = new StringReader("run res/scripts/testScript2.txt\nq");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("loaded fractal successfully\napplication ended\n",
            iv.outputString().toString());

  }

  @Test
  public void testLoadScriptWrongFile() {
    Reader in = new StringReader("run res/scripts/flower.ppm\nrun images/flower.ppm\nq");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("invalid script being used. "
            + "only txt files are allowed. Try again\n"
            + "\n"
            + "invalid script being used. only txt files are allowed. Try again\n"
            + "\napplication ended\n", iv.outputString().toString());
  }


  @Test
  public void testLoadTerminal() {
    Reader in = new StringReader("load res/images/flower.ppm flower \nq");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();

    assertEquals("loaded flower successfully\napplication ended\n",
            iv.outputString().toString());

  }

  @Test(expected = NoSuchElementException.class)
  public void testLoadTerminalWrongFile() {
    Reader in = new StringReader("load res/images/test-image.ppm koala");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();

  }

  @Test
  public void testSaveBeforeLoad() {
    Reader in = new StringReader("save res/images/flower.ppm fractal"
            + "\nload res/images/flower.ppm fractal\nq\n");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("image not found\n"
            + "loaded fractal successfully\n", iv.outputString().toString());
  }

  @Test
  public void testSaveTerminal() {
    Reader in = new StringReader("load res/images/flower.ppm fractal"
            + "\nsave res/images/flower-save.ppm fractal\nq\n");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();

    assertEquals("loaded fractal successfully" + "\n"
            + "saved fractal successfully\napplication ended\n", iv.outputString().toString());
  }

  @Test
  public void testSaveScript() {
    Reader in = new StringReader("run res/scripts/testScript1.txt\n");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();

    assertEquals("loaded fractal successfully" + "\n"
                    + "increased the brightness of fractal by 10 to fractal-brighter successfully"
                    + "\n"
                    + "flipped fractal-brighter to fractal-brighter-vertical vertically "
                    + "successfully"
                    + "\nsaved fractal-brighter-vertical successfully\napplication ended\n",
            iv.outputString().toString());
  }

  @Test
  public void testDoubleLoad() {
    Reader in = new StringReader("load res/images/flower.ppm fractal"
            + "\n load res/images/flower-brightened.ppm flower-brightened \nq");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();

    assertEquals("loaded fractal successfully" + "\n"
                    + "loaded flower-brightened successfully" + "\napplication ended\n",
            iv.outputString().toString());
  }

  @Test
  public void testCombineBySepiaPPM() {
    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "sepia-tone fractal fractal-sepia\n"
            + "save res/images/fractal-sepia.ppm fractal-sepia\n"
            + "q\n");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();

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
            + "q\n");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();

    assertEquals("loaded fractal successfully" + "\n"
            + "converting fractal to a sepia-toned Image - fractal-sepia is successful\n"
            + "saved fractal-sepia successfully"
            + "\napplication ended\n", iv.outputString().toString());
  }

  @Test
  public void testDither() {
    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "dither fractal fractal-dithered\n"
            + "save res/images/fractal-dithered.ppm fractal-dithered\n"
            + "q\n");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();

    assertEquals("loaded fractal successfully" + "\n"
            + "dither conversion of fractal to fractal-dithered is successful\n"
            + "saved fractal-dithered successfully"
            + "\napplication ended\n", iv.outputString().toString());
  }

  @Test
  public void testBlur() {
    Reader in = new StringReader("load res/images/flower.ppm fractal\n"
            + "blur fractal fractal-blur\n"
            + "save res/images/flower-blur.ppm fractal-blur\n"
            + "q");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();

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
            + "save res/images/flower-sharpen-sh.ppm fractal-sharpen-sh\n"
            + "q");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
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
            + "blur fractal-blur fractal-blur-bl\n"
            + "save res/images/flower-blur-bl.ppm fractal-blur-bl\n"
            + "q");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();

    assertEquals("loaded fractal successfully" + "\n"
            + "blurred fractal to fractal-blur successfully\n"
            + "blurred fractal-blur to fractal-blur-bl successfully\n"
            + "saved fractal-blur-bl successfully"
            + "\n"
            + "application ended\n", iv.outputString().toString());
  }


  @Test
  public void testMainTestScript() {
    Reader in = new StringReader("run res/scripts/mainTestScript.txt\nq");

    CommandController controller = new CommandController(in, ic, iv);
    controller.startProgram();
    assertEquals("loaded fractal successfully\n"
                    + "increased the brightness of fractal by 20 to fractal-brighter "
                    + "successfully\n"
                    + "decreased the brightness of fractal-brighter by 40 to fractal-darker "
                    + "successfully\n"
                    + "flipped fractal-darker to fractal-darker-vertical vertically successfully\n"
                    + "flipped fractal-darker-vertical to fractal-darker-horizontal horizontally "
                    + "successfully\n"
                    + "greyscale split of fractal-darker-horizontal by value-component to "
                    + "fractal-darker-horizontal-greyscale is successful\n"
                    + "saved fractal-darker-horizontal-greyscale successfully\n"
                    + "greyscale split of fractal by luma-component to fractal-luma-grayscale "
                    + "is successful\n"
                    + "greyscale split of fractal by intensity-component to "
                    + "fractal-intensity-grayscale is successful\n"
                    + "greyscale split of fractal by red-component to "
                    + "fractal-red-grayscale is successful\n"
                    + "greyscale split of fractal by green-component to "
                    + "fractal-green-grayscale is successful\n"
                    + "greyscale split of fractal by blue-component to "
                    + "fractal-blue-grayscale is successful\n"
                    + "split fractal to red: fractal-red green: "
                    + "fractal-green blue: fractal-blue successfully\n"
                    + "combined red: fractal-red green: fractal-green blue: "
                    + "fractal-blue to fractal-combined successfully\n"
                    + "saved fractal-combined successfully\n"
                    + "loaded fractal-jpeg successfully\n"
                    + "loaded fractal-png successfully\n"
                    + "loaded fractal-bmp successfully\n"
                    + "converting fractal to a sepia-toned Image - fractal-sepia is successful\n"
                    + "dither conversion of fractal to fractal-dithered is successful\n"
                    + "blurred fractal to fractal-blurred successfully\n"
                    + "blurred fractal-blurred to fractal-more-blurred successfully\n"
                    + "sharpened fractal to fractal-sharpened successfully\n"
                    + "sharpened fractal-sharpened to fractal-more-sharpened successfully\n"
                    + "saved fractal-more-sharpened successfully\n"
                    + "application ended\n",
            iv.outputString().toString());
  }

}