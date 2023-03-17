package model;

import static helper.ImageUtil.getPixels;

/**
 * This class represents a Greyscale image.
 */
public class GrayscaleImage extends AbstractImage {

  public GrayscaleImage(int width, int height, int maxValue, Pixel[][] pixels) {
    super(width, height, maxValue);
    this.pixels = pixels;
  }

  @Override
  public void brighten(int increment) {
    // Brighten grayscale image by given increment
    if (increment < 0) {
      darken(Math.abs(increment));
      return;
    }
    int maxValue = maxColorValue;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = pixels[i][j];
        // In Greyscale, all channels have equal values.
        int intensity = pixel.getChannels(0) + increment;
        if (intensity > maxValue) {
          intensity = maxValue;
        }
        pixels[i][j] = new Pixel(intensity, intensity, intensity);
      }
    }
  }

  @Override
  public void darken(int decrement) {
    // Darken grayscale image by given decrement
    int minValue = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = pixels[i][j];
        // In Greyscale, all channels have equal values.
        int intensity = pixel.getChannels(0) - decrement;
        if (intensity < minValue) {
          intensity = minValue;
        }
        pixels[i][j] = new Pixel(intensity, intensity, intensity);
      }
    }
  }

  @Override
  public Image duplicate() {
    return new GrayscaleImage(width, height, maxColorValue,
            getPixels(pixels, width, height));
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

