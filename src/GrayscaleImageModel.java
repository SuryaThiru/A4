import java.util.Scanner;

/**
 * For a greyscale image there is only one value per pixel. On a scale of 0-1, 0 indicates black
 * and 1 indicates white. Values in between indicate shades of grey. This value is traditionally
 * represented using an integer. The number of bits used to store this value dictates how many
 * "levels of grey" are supported. For example, an 8-bit representation creates 256 distinct levels
 * (including black and white).
 */
public class GrayscaleImageModel extends AbstractImageModel {

  public GrayscaleImageModel(int width, int height, int maxValue) {
    super(width, height, maxValue);
  }

  public GrayscaleImageModel(int width, int height, int maxValue, Pixel[][] pixels) {
    super(width, height, maxValue);
    this.pixels = pixels;
  }

  public void load(String content) {
    Scanner sc = new Scanner(content);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int intensity = sc.nextInt();
        setPixel(i, j, new Pixel(intensity, intensity, intensity));
      }
    }
  }

  public void save(String filePath) {
    // Save grayscale image to file
  }

  public void flipHorizontal() {
    // Flip grayscale image horizontally
  }

  public void flipVertical() {
    // Flip grayscale image vertically
  }

  public void brighten(int increment) {
    // Brighten grayscale image by given increment
  }

  public ImageModel[] splitChannels() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Splitting of greyscale images are not allowed");
  }

  public void combineChannels(ImageModel[] channels) {
  }
}

