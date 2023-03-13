import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import controller.ImageController;
import controller.PPMImageController;
import model.Image;
import model.RGBImage;

import static org.junit.Assert.*;

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
    ImageController controller = new PPMImageController(model);
    controller.load("images/Koala.ppm", "Koala");
    PPMImageController.testBrighten(10);


//    ImageUtil.readPPM("images/Koala.ppm");



  }


}