import java.util.Scanner;

/**
 * For a color image, the pixel's color is represented by breaking it into individual components
 * (usually 3) in some way. The most common representation is the red-green-blue (RGB) model.
 * In this model, a color is represented by three numbers (components): red, green, blue. Any color
 * is a combination of these three base colors. On a scale of 0-1, black is represented as (0,0,0),
 * white as (1,1,1) and bright, pure yellow is represented as (1,1,0).
 */
public class RGBModel extends AbstractImageModel {
  private GrayscaleImageModel redChannel;
  private GrayscaleImageModel greenChannel;
  private GrayscaleImageModel blueChannel;

  public RGBModel(int width, int height, int maxValue) {
    super(width, height, maxValue);
  }

  private void split() {
    Pixel[][] pixels = this.pixels;
    int height = this.height;
    int width = this.width;

    Pixel[][] redPixels = new Pixel[height][width];
    Pixel[][] greenPixels = new Pixel[height][width];
    Pixel[][] bluePixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = pixels[i][j];
        redPixels[i][j] = new Pixel(pixel.getRed(), 0, 0);
        greenPixels[i][j] = new Pixel(0, pixel.getGreen(), 0);
        bluePixels[i][j] = new Pixel(0, 0, pixel.getBlue());
      }
    }

    redChannel = new GrayscaleImageModel(width, height, maxColorValue, redPixels);
    greenChannel = new GrayscaleImageModel(width, height, maxColorValue, greenPixels);
    blueChannel = new GrayscaleImageModel(width, height, maxColorValue, bluePixels);
  }

  public void load(String content) {
    Scanner sc = new Scanner(content);

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        this.setPixel(i, j, new Pixel(r, g, b));
      }
    }

    split();
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

  @Override
  public ImageModel[] splitChannels() throws UnsupportedOperationException {
    ImageModel[] imageModels = new ImageModel[3];

    imageModels[0] = redChannel;
    imageModels[1] = greenChannel;
    imageModels[2] = blueChannel;

    return imageModels;
  }

  public void combineChannels(ImageModel[] channels) {
    // Combine separate red, green, blue channels into a single color image
  }
}