package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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



}
