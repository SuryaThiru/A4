import java.util.Scanner;

/**
 * For a color image, the pixel's color is represented by breaking it into individual components
 * (usually 3) in some way. The most common representation is the red-green-blue (RGB) model.
 * In this model, a color is represented by three numbers (components): red, green, blue. Any color
 * is a combination of these three base colors. On a scale of 0-1, black is represented as (0,0,0),
 * white as (1,1,1) and bright, pure yellow is represented as (1,1,0).
 */
public class ColorImageModel implements ImageModel {

  private PPMImage image;
  private GrayscaleImageModel redChannel;
  private GrayscaleImageModel greenChannel;
  private GrayscaleImageModel blueChannel;

  public ColorImageModel(PPMImage image) {
    this.image = image;
    split();
  }

  private void split() {
    Pixel[][] pixels = image.getPixels();
    int height = image.getHeight();
    int width = image.getWidth();

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

    redChannel = new GrayscaleImageModel(new PPMImage(width, height, image.getMaxColorValue(),
            redPixels));
    greenChannel = new GrayscaleImageModel(new PPMImage(width, height, image.getMaxColorValue(),
            greenPixels));
    blueChannel = new GrayscaleImageModel(new PPMImage(width, height, image.getMaxColorValue(),
            bluePixels));
  }

  public void load(String content) {
    Scanner sc = new Scanner(content);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        image.setPixel(i, j, new Pixel(r, g, b));
      }
    }
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
    return new ImageModel[0];
  }

//  public ImageModel[] splitChannels() {
//    // Split color image into separate red, green, blue channels
//    return new ImageModel[] {
//            new GrayscaleImage(width, height),
//            new GrayscaleImage(width, height),
//            new GrayscaleImage(width, height)
//    };
//  }

  public void combineChannels(ImageModel[] channels) {
    // Combine separate red, green, blue channels into a single color image
  }
}