public class ColorImage implements ImageModel {
  private byte[][][] data;
  private int width;
  private int height;

  public void load(String filePath) {
    // Load color image from file
  }

  public void save(String filePath) {
    // Save color image to file
  }

  public void flipHorizontal() {
    // Flip color image horizontally
  }

  public void flipVertical() {
    // Flip color image vertically
  }

  public void brighten(int increment) {
    // Brighten color image by given increment
  }

  public ImageModel[] splitChannels() {
    // Split color image into separate red, green, blue channels
    return new ImageModel[] {
            new GrayscaleImage(/* red channel data */),
            new GrayscaleImage(/* green channel data */),
            new GrayscaleImage(/* blue channel data */)
    };
  }

  public void combineChannels(ImageModel[] channels) {
    // Combine separate red, green, blue channels into a single color image
  }
}