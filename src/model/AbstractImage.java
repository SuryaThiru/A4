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

  /**
   * This method sets the pixel values at a particular row and column.
   * @param x represents the row.
   * @param y represents the column.
   * @param pixel represents the pixel object to be set at the given place.
   */
  public void setPixel(int x, int y, Pixel pixel) {
    pixels[y][x] = pixel;
  }

  /**
   * This method return the pixel values at a particular row and column.
   * @param x represents the row.
   * @param y represents the column.
   * @return returns a pixel value at x, y of type Pixel.
   */
  public Pixel getPixel(int x, int y) {
    return pixels[x][y];
  }
}
