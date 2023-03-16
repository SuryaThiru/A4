package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public abstract class AbstractImage implements Image {

  protected int width;
  protected int height;
  protected int maxColorValue;
  protected Pixel[][] pixels;

  protected AbstractImage(int width, int height, int maxColorValue) {
    this.width = width;
    this.height = height;
    this.maxColorValue = maxColorValue;
    this.pixels = new Pixel[height][width];
  }

  @Override
  public Image load(String content) {
    throw new IllegalArgumentException("loading is currently supported in rgbmodel");
  }

  @Override
  public void save(String filePath) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write("P3" + "\n");
      writer.write(width + " " + height + "\n");
      writer.write(maxColorValue + "\n");

      // Write the image data
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Pixel pixel = pixels[i][j];
          writer.write(pixel.getChannels(0) + " " + pixel.getChannels(1)
                  + " " + pixel.getChannels(2) + " ");
        }
        writer.newLine();
      }
    }
  }

  @Override
  public boolean isGrayscale() {
    return false;
  }

  @Override
  public void flipHorizontal() {
    // Flip color image horizontally
    Pixel[][] pixels = this.pixels;
    int width = this.width;
    int height = this.height;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width / 2; j++) {
        Pixel temp = pixels[i][j];
        pixels[i][j] = pixels[i][width - j - 1];
        pixels[i][width - j - 1] = temp;
      }
    }
  }

  @Override
  public void flipVertical() {
    // Flip color image vertically
    Pixel[][] pixels = this.pixels;
    int width = this.width;
    int height = this.height;

    for (int i = 0; i < height / 2; i++) {
      for (int j = 0; j < width; j++) {
        Pixel temp = pixels[i][j];
        pixels[i][j] = pixels[height - i - 1][j];
        pixels[height - i - 1][j] = temp;
      }
    }
  }

  @Override
  public boolean validateCombineChannels(Image[] channels) throws IllegalArgumentException {
    return false;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public Pixel getPixel(int x, int y) {
    return pixels[x][y];
  }

  @Override
  public boolean compareImages(Image updatedImage)
          throws IllegalArgumentException {

    if (!((width == updatedImage.getWidth())
            && (height == updatedImage.getHeight()))) {
      throw new IllegalArgumentException("the two image should be of the same height and width");
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (!(updatedImage.getPixel(y, x).channels.length
                == this.getPixel(y, x). channels.length)) {
          return false;
        }
        if (!(Arrays.equals(this.getPixel(y, x).channels, updatedImage.getPixel(y,x).channels))) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public Image combineByValue() {
    int numberOfChannels = pixels[0][0].channels.length;
    Pixel[][] p = new Pixel[width][height];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = new Pixel();
        pixel.channels = new int[numberOfChannels];
        int value = 0;
        for(int k = 0; k < numberOfChannels; k++) {
          value = Math.max(value, pixels[x][y].getChannels(k));
          pixel.channels[k] = value;
        }
        p[y][x] = pixel;
      }
    }

    return new GrayscaleImage(width, height, maxColorValue, p);
  }

  @Override
  public Image combineByIntensity() {
    int numberOfChannels = pixels[0][0].channels.length;
    Pixel[][] p = new Pixel[width][height];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = new Pixel();
        pixel.channels = new int[numberOfChannels];
        for(int k = 0; k < numberOfChannels; k++) {
          pixel.channels[k] = calculateIntensity(pixels[x][y].channels);
        }
        p[y][x] = pixel;
      }
    }

    return new GrayscaleImage(width, height, maxColorValue, p);
  }

  protected int calculateIntensity(int[] channels) {
    int sum = 0;
    int channelLength = channels.length;
    for(int i = 0; i < channelLength; i++) {
      sum = sum + channels[i];
    }

    return sum/channelLength;
  }

  @Override
  public Image combineByLuma() throws UnsupportedOperationException {
    int numberOfChannels = pixels[0][0].channels.length;

    if(numberOfChannels != 3) {
      throw new IllegalArgumentException("image should contain rgb component");
    }
    Pixel[][] p = new Pixel[width][height];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = new Pixel();
        pixel.channels = new int[numberOfChannels];
        for(int k = 0; k < numberOfChannels; k++) {
          pixel.channels[k] = calculateLuma(pixels[x][y].channels);
        }
        p[y][x] = pixel;
      }
    }

    return new GrayscaleImage(width, height, maxColorValue, p);
  }

  protected int calculateLuma(int[] channels) {
    return (int)(0.2126 * channels[0] + 0.7152 * channels[1] + 0.0722 * channels[2]);
  }




}
