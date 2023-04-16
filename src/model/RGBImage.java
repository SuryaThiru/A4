package model;

import static helper.ImageUtil.duplicatePixels;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Scanner;


/**
 * This class represents an RGB Image and contains the operations performed on it.
 */
public class RGBImage extends AbstractImage {

  GrayscaleImage redChannel;
  GrayscaleImage greenChannel;
  GrayscaleImage blueChannel;

  /**
   * This constructor initialises the variables of the AbstractImage and this class.
   *
   * @param width    represents the width of the Image.
   * @param height   represents the height of the Image.
   * @param maxValue represents the maxvalue of the Image.
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

    pixels = new PixelImpl[height][width];

    boolean isGrayscale = true;

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        if (!(r == g && r == b)) {
          isGrayscale = false;
        }
        pixels[i][j] = new PixelImpl(r, g, b);
      }
    }

    if (isGrayscale) {
      return new GrayscaleImage(width, height, maxColorValue, pixels);
    }
    split();
    return this;
  }

  @Override
  public Image load(BufferedImage image) {
    this.width = image.getWidth();
    this.height = image.getHeight();
    this.maxColorValue = 255;
    Pixel[][] pixels = new Pixel[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = image.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        pixels[y][x] = new PixelImpl(red, green, blue);
      }
    }

    this.pixels = pixels;
    split();
    return this;
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
        int newGreen = clamp(pixel.getChannel(1) + factor * increment);
        int newBlue = clamp(pixel.getChannel(2) + factor * increment);
        pixels[i][j] = new PixelImpl(newRed, newGreen, newBlue);
      }
    }
  }

  @Override
  public Image duplicate() {
    RGBImage t = new RGBImage(width, height, maxColorValue,
        duplicatePixels(pixels, width, height));
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
  public void combineChannels(Image[] images) throws IllegalArgumentException {

    int width = images[0].getWidth();
    int height = images[0].getHeight();

    this.width = width;
    this.height = height;
    this.maxColorValue = 255;
    pixels = new PixelImpl[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] channels = new int[images.length];
        channels[0] = images[0].getPixel(y, x).getChannel(0);
        channels[1] = images[1].getPixel(y, x).getChannel(0);
        channels[2] = images[2].getPixel(y, x).getChannel(0);

        pixels[y][x] = new PixelImpl(channels);
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

  /**
   * Get a mosaicked version of the image.
   *
   * @param seeds number of points in the image to cluster pixels to
   * @return mosaicked image
   */
  @Override
  public Image mosaick(int seeds) {
    List<List<int[]>> seedToPixels = getMosaicClusters(seeds);

    // assign pixels the average color of the cluster
    Pixel[][] outPixels = new PixelImpl[height][width];
    for (int s = 0; s < seeds; s++) {
      int[] avg = new int[3];
      int cnt = seedToPixels.get(s).size();
      if (cnt == 0) {
        continue;
      }

      for (int[] pt : seedToPixels.get(s)) {
        Pixel pixel = getPixel(pt[0], pt[1]);
        avg[0] += pixel.getChannel(0);
        avg[1] += pixel.getChannel(1);
        avg[2] += pixel.getChannel(2);
      }
      avg[0] = avg[0] / cnt;
      avg[1] = avg[1] / cnt;
      avg[2] = avg[2] / cnt;

      for (int[] pt : seedToPixels.get(s)) {
        outPixels[pt[0]][pt[1]] = new PixelImpl(avg[0], avg[1], avg[2]);
      }
    }
    return new RGBImage(width, height, maxColorValue, outPixels);
  }

  void split() {
    Pixel[][] pixels = this.pixels;
    int height = this.height;
    int width = this.width;

    Pixel[][] redPixels = new PixelImpl[height][width];
    Pixel[][] greenPixels = new PixelImpl[height][width];
    Pixel[][] bluePixels = new PixelImpl[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = pixels[i][j];
        redPixels[i][j] = new PixelImpl(pixel.getChannel(0),
            pixel.getChannel(0), pixel.getChannel(0));
        greenPixels[i][j] = new PixelImpl(pixel.getChannel(1),
            pixel.getChannel(1), pixel.getChannel(1));
        bluePixels[i][j] = new PixelImpl(pixel.getChannel(2),
            pixel.getChannel(2), pixel.getChannel(2));
      }
    }

    redChannel = new GrayscaleImage(width, height, maxColorValue, redPixels);
    greenChannel = new GrayscaleImage(width, height, maxColorValue, greenPixels);
    blueChannel = new GrayscaleImage(width, height, maxColorValue, bluePixels);
  }

}