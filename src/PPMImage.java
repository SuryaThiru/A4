public class PPMImage {
  private int width;
  private int height;
  private int maxColorValue;
  private Pixel[][] pixels;

  public PPMImage(int width, int height, int maxColorValue) {
    this.width = width;
    this.height = height;
    this.maxColorValue = maxColorValue;
    this.pixels = new Pixel[height][width];
  }

  public PPMImage(int width, int height, int maxColorValue, Pixel [][] p) {
    this.width = width;
    this.height = height;
    this.maxColorValue = maxColorValue;
    this.pixels = new Pixel[height][width];
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getMaxColorValue() {
    return maxColorValue;
  }

  public Pixel getPixel(int x, int y) {
    return pixels[y][x];
  }

  public void setPixel(int x, int y, Pixel pixel) {
    pixels[y][x] = pixel;
  }

  public Pixel[][] getPixels() {
    return pixels;
  }
}