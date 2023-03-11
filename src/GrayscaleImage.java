/**
 * For a greyscale image there is only one value per pixel. On a scale of 0-1, 0 indicates black
 * and 1 indicates white. Values in between indicate shades of grey. This value is traditionally
 * represented using an integer. The number of bits used to store this value dictates how many
 * "levels of grey" are supported. For example, an 8-bit representation creates 256 distinct levels
 * (including black and white).
 */
public class GrayscaleImage implements ImageModel {
  private byte[] data;
  private int width;
  private int height;

  public void load(String filePath) {
    // Load grayscale image from file
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

  public ImageModel[] splitChannels() {
    // Grayscale image has only one channel
    return new ImageModel[] { this };
  }

  public void combineChannels(ImageModel[] channels) {
    throw new UnsupportedOperationException("Cannot combine channels for grayscale image");
  }
}

