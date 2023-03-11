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

