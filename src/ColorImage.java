/**
 * For a color image, the pixel's color is represented by breaking it into individual components
 * (usually 3) in some way. The most common representation is the red-green-blue (RGB) model.
 * In this model, a color is represented by three numbers (components): red, green, blue. Any color
 * is a combination of these three base colors. On a scale of 0-1, black is represented as (0,0,0),
 * white as (1,1,1) and bright, pure yellow is represented as (1,1,0).
 */
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