import org.junit.Test;

import static org.junit.Assert.*;

public class ImageModelTest {

  @Test
  public void testFlipHorizontal(){

  }

  @Test
  public void testFlipHorizontalSameSize(){

  }

  @Test
  public void testFlipVertical(){

  }

  @Test
  public void testFlipVerticalSameSize(){

  }

  @Test
  public void testFlipVerticalAndHorizontal(){

  }

  @Test
  public void testFlipHorizontalAndVertical(){

  }

  @Test
  public void testBrighten(){

  }

  @Test
  public void testMaxBrighten(){

  }

  @Test
  public void testDarken(){

  }

  @Test
  public void testMaxDarken(){

  }

  @Test
  public void testSplit(){

  }

  @Test
  public void testCombine(){

  }

  @Test
  public void testDuplicate(){

  }
/**
 * Allow non-contiguous scripting
 *
 * Flipping should not change the size of the image.
 *
 * Allow combined flipping - horizontal followed by vertical flip, or vice versa.
 *
 * Brighten
 *
 * Complete dark to visible  255
 *
 * Normal - visible to brighter
 *
 * Max bright to max bright
 *
 * Darken
 *
 * Complete dark to dark 0
 *
 * Normal - visible to darker
 *
 * Max bright to dark
 *
 * Greyscale
 *
 * Greyscale with value, intensity or luma
 *
 * Grey to grey ?
 *
 * Combining channels into one image
 *
 * Load an image from an ASCII PPM file
 *
 * Create images that visualize individual R,G,B components of an image.
 *
 * Create images that visualize the value, intensity or luma of an image as defined above.
 *
 * Split a single image into 3 images representing each of the three channels
 *
 * Combine three greyscale image into a single color image whose R,G,B values come from the three images
 *
 * Save an image to an ASCII PPM file.
 *
 * Test text-based scripting.
 *
 * Write very thorough documentation.
 *
 * Null objects
 */

}