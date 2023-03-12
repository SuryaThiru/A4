package model;

public abstract class AbstractImage implements Image {

  protected int width;
  protected int height;
  protected int maxColorValue;
  protected Pixel[][] pixels;

  public AbstractImage(int width, int height, int maxColorValue) {
    this.width = width;
    this.height = height;
    this.maxColorValue = maxColorValue;
    this.pixels = new Pixel[height][width];
  }

  public void setPixel(int x, int y, Pixel pixel) {
    pixels[y][x] = pixel;
  }
}
