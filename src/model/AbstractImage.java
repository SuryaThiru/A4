package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.sun.tools.doclint.Entity.image;

public abstract class AbstractImage implements Image {

  protected int width;
  protected String magicNumber;
  protected int height;
  protected int maxColorValue;
  protected Pixel[][] pixels;

  protected AbstractImage(int width, int height, int maxColorValue, String magicNumber) {
    this.width = width;
    this.height = height;
    this.maxColorValue = maxColorValue;
    this.magicNumber = magicNumber;
    this.pixels = new Pixel[height][width];
  }

  protected void setPixel(int x, int y, Pixel pixel) {
    pixels[x][y] = pixel;
  }

  /**
   * This method return the pixel values at a particular row and column.
   * @param x represents the row.
   * @param y represents the column.
   * @return returns a pixel value at x, y of type Pixel.
   */
  protected Pixel getPixel(int x, int y) {
    return pixels[x][y];
  }

  public void save(String filePath, String fileName) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write(magicNumber);
      writer.write(width + " " + height + "\n");
      writer.write(maxColorValue + "\n");

      // Write the image data
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          Pixel pixel = getPixel(x, y);

          if(magicNumber == "P2") {
            writer.write(pixel.getRed() + " ");
            continue;
          }
          writer.write(pixel.getRed() + " " + pixel.getGreen() + " " + pixel.getBlue() + " ");
        }
        writer.newLine();
      }
    }
  }
}
