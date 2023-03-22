package model;

import java.awt.image.BufferedImage;
import java.util.Scanner;

import static helper.ImageUtil.getPixels;

/**
 * This class represents an RGB Image and contains the operations performed on it.
 */
public class RGBImage extends AbstractImage {
  private GrayscaleImage redChannel;
  private GrayscaleImage greenChannel;
  private GrayscaleImage blueChannel;

  /**
   * Instantiates the class objects.
   *
   * @param width    represents the width of the image.
   * @param height   represents the height of the image.
   * @param maxValue represents the maxvalue of the image.
   */
  public RGBImage(int width, int height, int maxValue) {
    super(width, height, maxValue);
  }

  /**
   * Instantiates the class objects.
   *
   * @param width    represents the width of the image.
   * @param height   represents the height of the image.
   * @param maxValue represents the maxvalue of the image.
   * @param pixels   represents the pixels of the image.
   */
  public RGBImage(int width, int height, int maxValue, Pixel[][] pixels) {
    super(width, height, maxValue);
    this.pixels = pixels;
  }

  @Override
  public Image load(String content) {
    Scanner sc = new Scanner(content);
    width = sc.nextInt();
    height = sc.nextInt();
    maxColorValue = sc.nextInt();

    pixels = new Pixel[height][width];

    boolean isGrayscale = true;

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        if (!(r == g && r == b)) {
          isGrayscale = false;
        }
        pixels[i][j] = new Pixel(r, g, b);
      }
    }

    if (isGrayscale) {
      return new GrayscaleImage(width, height, maxColorValue, pixels);
    }

    split();
    return this;
  }

  private void split() {
    Pixel[][] pixels = this.pixels;
    int height = this.height;
    int width = this.width;

    Pixel[][] redPixels = new Pixel[height][width];
    Pixel[][] greenPixels = new Pixel[height][width];
    Pixel[][] bluePixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = pixels[i][j];
        redPixels[i][j] = new Pixel(pixel.getChannels(0),
                pixel.getChannels(0), pixel.getChannels(0));
        greenPixels[i][j] = new Pixel(pixel.getChannels(1),
                pixel.getChannels(1), pixel.getChannels(1));
        bluePixels[i][j] = new Pixel(pixel.getChannels(2),
                pixel.getChannels(2), pixel.getChannels(2));
      }
    }

    redChannel = new GrayscaleImage(width, height, maxColorValue, redPixels);
    greenChannel = new GrayscaleImage(width, height, maxColorValue, greenPixels);
    blueChannel = new GrayscaleImage(width, height, maxColorValue, bluePixels);
  }

  @Override
  public void brighten(int increment) {
    // Brighten color image by given increment
    if (increment < 0) {
      darken(Math.abs(increment));
      return;
    }
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = pixels[i][j];
        int newRed = Math.min(pixel.getChannels(0) + increment, maxColorValue);
        int newGreen = Math.min(pixel.getChannels(1) + increment, maxColorValue);
        int newBlue = Math.min(pixel.getChannels(2) + increment, maxColorValue);
        pixels[i][j] = new Pixel(newRed, newGreen, newBlue);
      }
    }
  }

  @Override
  public void darken(int decrement) {
    if (decrement < 0) {
      brighten(Math.abs(decrement));
      return;
    }
    // Darken color image by given decrement
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = pixels[i][j];
        int newRed = Math.max(pixel.getChannels(0) - decrement, 0);
        int newGreen = Math.max(pixel.getChannels(1) - decrement, 0);
        int newBlue = Math.max(pixel.getChannels(2) - decrement, 0);
        pixels[i][j] = new Pixel(newRed, newGreen, newBlue);
      }
    }
  }

  @Override
  public Image duplicate() {
    RGBImage t = new RGBImage(width, height, maxColorValue,
            getPixels(pixels, width, height));
    t.split();

    return t;
  }

  @Override
  public Image[] splitChannels() throws UnsupportedOperationException {
    Image[] images = new Image[3];

    images[0] = redChannel;
    images[1] = greenChannel;
    images[2] = blueChannel;

    return images;
  }

  @Override
  public void combineChannels(Image[] channels) throws IllegalArgumentException {

    int width = channels[0].getWidth();
    int height = channels[0].getHeight();

    this.width = width;
    this.height = height;
    this.maxColorValue = 255;
    pixels = new Pixel[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = new Pixel();
        pixel.channels = new int[channels.length];
        pixel.channels[0] = channels[0].getPixel(y, x).getChannels(0);
        pixel.channels[1] = channels[1].getPixel(y, x).getChannels(0);
        pixel.channels[2] = channels[2].getPixel(y, x).getChannels(0);

        pixels[y][x] = pixel;
      }
    }

    split();
  }

  @Override
  public boolean validateCombineChannels(Image[] channels) throws IllegalArgumentException {
    int length = channels.length;

    if (length != 3) {
      throw new IllegalArgumentException("three images are expected to combine the channels");
    }

    for (Image channel : channels) {
      if (!channel.isGrayscale()) {
        throw new IllegalArgumentException("three images are expected to be grayscale images");
      }
    }

    if (!((channels[0].getWidth() == channels[1].getWidth()
            && channels[0].getWidth() == channels[2].getWidth())
            && (channels[0].getHeight() == channels[1].getHeight()
            && channels[0].getHeight() == channels[2].getHeight()))) {
      throw new IllegalArgumentException("the three image should be of the same height and width");
    }

    return true;
  }

}