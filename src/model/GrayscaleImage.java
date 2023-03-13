package model;

import java.util.Scanner;

import static helper.ImageUtil.getPixels;

/**
 * For a greyscale image there is only one value per pixel. On a scale of 0-1, 0 indicates black
 * and 1 indicates white. Values in between indicate shades of grey. This value is traditionally
 * represented using an integer. The number of bits used to store this value dictates how many
 * "levels of grey" are supported. For example, an 8-bit representation creates 256 distinct levels
 * (including black and white).
 */
public class GrayscaleImage extends AbstractImage {

  //TODO we can build a builder class within
  public GrayscaleImage(int width, int height, int maxValue) {
    super(width, height, maxValue);
  }

  public GrayscaleImage(int width, int height, int maxValue, Pixel[][] pixels) {
    super(width, height, maxValue);
    this.pixels = pixels;
  }

  @Override
  public void load(String content) {
    Scanner sc = new Scanner(content);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int intensity = sc.nextInt();
        pixels[i][j] = new Pixel(intensity, intensity, intensity);
      }
    }
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


  public Image[] splitChannels() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Splitting of greyscale images are not allowed");
  }

  public void combineChannels(Image[] channels) {
  }
}

