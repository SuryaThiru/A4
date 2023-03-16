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


  public Image[] splitChannels() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Splitting of one bit images are not allowed");
  }

  public boolean isGrayscale() {
    return true;
  }


  public void combineChannels(Image[] channels) throws IllegalArgumentException {
//    if (channels.length != 3) {
//      throw new IllegalArgumentException("three images are expected to combine the channels");
//    }
//
//    for (int i = 0; i < channels; i++) {
//      if(!channels[i].isGrayscale()) {
//        throw new IllegalArgumentException("three images are expected to be grayscale images");
//      }
//    }
//
//    if (!((channels[0].width == channels[1].width && channels[0].width == channels[2].width)
//            && (channels[0].height == channels[1].height
//            && channels[0].height == channels[2].height))) {
//      throw new IllegalArgumentException("the three image should be of the same height and width");
//    }
//
//    int width = channels[0].width;
//    int height = channels[0].height;
//
//    RGBImage rgbImage = new RGBImage(width, height, 255);
//    Pixel pixels[][] = new Pixel[width][height];
//
//    for (int y = 0; y < height; y++) {
//      for (int x = 0; x < width; x++) {
//        Pixel pixel = new Pixel();
//        pixel.channels[0] = channels[0].pixels[x][y].getChannels(0);
//        pixel.channels[1] = channels[1].pixels[x][y].getChannels(0);
//        pixel.channels[2] = channels[2].pixels[x][y].getChannels(0);
//
//        pixels[x][y] = pixel;
//      }
//    }
//
//    rgbImage.pixels = pixels;
  }
}

