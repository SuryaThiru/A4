package model;

import static helper.ImageUtil.duplicatePixels;

/**
 * This class represents a Greyscale image.
 */
public class GrayscaleImage extends AbstractImage {

  /**
   * This constructor initialises the variables of the AbstractImage and this class.
   *
   * @param width    represents the width of the Image.
   * @param height   represents the height of the Image.
   * @param maxValue represents the maxvalue of the Image.
   * @param pixels   represents the pixels in an Image.
   */
  public GrayscaleImage(int width, int height, int maxValue, Pixel[][] pixels) {
    super(width, height, maxValue);
    this.pixels = pixels;
  }

  @Override
  public void brighten(int increment) {
    int factor = 1;
    if (increment < 0) {
      factor = -1;
      increment = -increment;
    }
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = pixels[i][j];
        int newRed = clamp(pixel.getChannel(0) + factor * increment);
        pixels[i][j] = new PixelImpl(newRed, newRed, newRed);
      }
    }
  }

  @Override
  public Image duplicate() {
    return new GrayscaleImage(width, height, maxColorValue,
            duplicatePixels(pixels, width, height));
  }

  @Override
  public Image[] splitChannels() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Splitting of one bit images are not allowed");
  }

  @Override
  public boolean isGrayscale() {
    return true;
  }

  @Override
  public void combineChannels(Image[] channels) {
    throw new UnsupportedOperationException("Splitting of greyscale images are not allowed");
  }
}

