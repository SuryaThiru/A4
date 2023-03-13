package model;

import java.util.Scanner;

/**
 * For a color image, the pixel's color is represented by breaking it into individual components
 * (usually 3) in some way. The most common representation is the red-green-blue (RGB) model.
 * In this model, a color is represented by three numbers (components): red, green, blue. Any color
 * is a combination of these three base colors. On a scale of 0-1, black is represented as (0,0,0),
 * white as (1,1,1) and bright, pure yellow is represented as (1,1,0).
 */
public class RGBImage extends AbstractImage {
  private GrayscaleImage redChannel;
  private GrayscaleImage greenChannel;
  private GrayscaleImage blueChannel;

  public RGBImage(int width, int height, int maxValue) {
    super(width, height, maxValue, "P3");
  }

  public void load(String content) {
    Scanner sc = new Scanner(content);

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        this.setPixel(i, j, new Pixel(r, g, b));
//        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }

    split();
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
        redPixels[i][j] = new Pixel(pixel.getRed(), 0, 0);
        greenPixels[i][j] = new Pixel(0, pixel.getGreen(), 0);
        bluePixels[i][j] = new Pixel(0, 0, pixel.getBlue());
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
        Pixel pixel = this.getPixel(j, i);
        int newRed = Math.min(pixel.getRed() + increment, maxColorValue);
        int newGreen = Math.min(pixel.getGreen() + increment, maxColorValue);
        int newBlue = Math.min(pixel.getBlue() + increment, maxColorValue);
        this.setPixel(i, j, new Pixel(newRed, newGreen, newBlue));
      }
    }
  }

  @Override
  public void darken(int decrement) {
    // Darken color image by given decrement
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = this.getPixel(j, i);
        int newRed = Math.max(pixel.getRed() - decrement, 0);
        int newGreen = Math.max(pixel.getGreen() - decrement, 0);
        int newBlue = Math.max(pixel.getBlue() - decrement, 0);
        this.setPixel(i, j, new Pixel(newRed, newGreen, newBlue));
      }
    }
  }


  @Override
  public Image[] splitChannels() throws UnsupportedOperationException {
    Image[] images = new Image[3];

    images[0] = redChannel;
    images[1] = greenChannel;
    images[2] = blueChannel;

    return images;
  }

  public void combineChannels(Image[] channels) {
    throw new UnsupportedOperationException("Splitting of greyscale images are not allowed");
  }

}