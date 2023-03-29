package model;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * This abstract class for Greyscale and RGBImage and contains the common functionality.
 */
public abstract class AbstractImage implements Image {

  int width;
  int height;
  int maxColorValue;
  Pixel[][] pixels;

  protected AbstractImage(int width, int height, int maxColorValue) {
    super();
    this.width = width;
    this.height = height;
    this.maxColorValue = maxColorValue;
    this.pixels = new Pixel[height][width];
  }

  @Override
  public Image load(String content) {
    throw new IllegalArgumentException("loading is currently supported in rgb model");
  }

  @Override
  public Image load(BufferedImage image) {
    this.width = image.getWidth();
    this.height = image.getHeight();
    this.maxColorValue = 255;
    Pixel[][] pixels = new Pixel[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = image.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        pixels[y][x] = new PixelImpl(red, green, blue);
      }
    }

    this.pixels = pixels;

    return this;
  }

  @Override
  public boolean isGrayscale() {
    return false;
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

  @Override
  public boolean validateCombineChannels(Image[] channels) throws IllegalArgumentException {
    return false;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public Pixel getPixel(int x, int y) {
    return pixels[x][y];
  }

  @Override
  public int getMaxColorValue() {
    return maxColorValue;
  }


  @Override
  public boolean compareImages(Image updatedImage)
          throws IllegalArgumentException {

    if (!((width == updatedImage.getWidth())
            && (height == updatedImage.getHeight()))) {
      throw new IllegalArgumentException("the two image should be of the same height and width");
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if ((updatedImage.getPixel(y, x).numChannels()
                != this.getPixel(y, x).numChannels())) {
          return false;
        }
        if (!(Arrays.equals(this.getPixel(y, x).getChannels(),
                updatedImage.getPixel(y, x).getChannels()))) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public Image combineByValue() {
    int numberOfChannels = pixels[0][0].numChannels();
    Pixel[][] p = new PixelImpl[width][height];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] channels = new int[numberOfChannels];
        int value = 0;
        for (int k = 0; k < numberOfChannels; k++) {
          value = Math.max(value, pixels[x][y].getChannel(k));
          channels[k] = value;
        }
        p[y][x] = new PixelImpl(channels);
      }
    }

    return new GrayscaleImage(width, height, maxColorValue, p);
  }

  @Override
  public Image combineByIntensity() {
    int numberOfChannels = pixels[0][0].numChannels();
    Pixel[][] p = new Pixel[width][height];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] channels = new int[numberOfChannels];
        for (int k = 0; k < numberOfChannels; k++) {
          channels[k] = calculateIntensity(pixels[x][y].getChannels());
        }
        p[y][x] = new PixelImpl(channels);
      }
    }

    return new GrayscaleImage(width, height, maxColorValue, p);
  }

  protected int calculateIntensity(int[] channels) {
    int sum = 0;
    int channelLength = channels.length;
    for (int channel : channels) {
      sum = sum + channel;
    }

    return sum / channelLength;
  }

  @Override
  public Image combineByLuma() throws UnsupportedOperationException {
    int numberOfChannels = pixels[0][0].numChannels();

    if (numberOfChannels != 3) {
      throw new IllegalArgumentException("image should contain rgb component");
    }

    Pixel[][] p = new Pixel[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] channels = new int[numberOfChannels];
        for (int k = 0; k < numberOfChannels; k++) {
          channels[k] = calculateLuma(pixels[y][x].getChannels());
        }
        p[y][x] = new PixelImpl(channels);
      }
    }

    return new GrayscaleImage(width, height, maxColorValue, p);
  }

  protected int calculateLuma(int[] channels) {
    return (int) Math.round(0.2126 * channels[0] + 0.7152 * channels[1] + 0.0722 * channels[2]);
  }

  private void filter(int channel, double[][] kernel) {
    int[][] newValues = new int[height][width];
    int kernelSize = kernel.length;


    // Loop over each pixel
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int sum = 0;

        // Loop over each kernel element
        for (int ky = 0; ky < kernelSize; ky++) {
          int pixelY = y + ky - kernelSize / 2;
          if (pixelY < 0 || pixelY >= height) {
            continue;
          }

          for (int kx = 0; kx < kernelSize; kx++) {
            int pixelX = x + kx - kernelSize / 2;
            if (pixelX < 0 || pixelX >= width) {
              continue;
            }

            sum += pixels[pixelY][pixelX].getChannel(channel) * kernel[ky][kx];
          }
        }

        newValues[y][x] = sum;
      }
    }

    // Set the new values
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] channels = new int[pixels[y][x].numChannels()];
        for (int c = 0; c < channels.length; c++) {
          channels[c] = pixels[y][x].getChannel(c);
        }
        channels[channel] = newValues[y][x];
        pixels[y][x] = new PixelImpl(channels);
      }
    }

  }

  @Override
  public void blur() {
    double[][] kernel = {{0.06, 0.13, 0.06}, {0.13, 0.25, 0.13}, {0.06, 0.13, 0.06}};
    if(pixels.length == 0 || pixels[0].length == 0) {
      throw new IllegalArgumentException("image has no pixels to blur");
    }

    for (int i = 0; i < pixels[0][0].numChannels(); i++) {
      filter(i, kernel);
    }
  }

  @Override
  public void sharpen() {
    // Call filter with the kernel constant.
    double centerWeight = 1.0;
    double edgeWeight = 0.25;
    double cornerWeight = -0.13;

    double[][] kernel = new double[5][5];

    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[0].length; j++) {
        if (i == 0 || i == kernel.length - 1 || j == 0 || j == kernel[0].length - 1) {
          kernel[i][j] = cornerWeight;
        } else if (i == 2 && j == 2) {
          kernel[i][j] = centerWeight;
        } else {
          kernel[i][j] = edgeWeight;
        }
      }
    }
    if(pixels.length == 0 || pixels[0].length == 0) {
      throw new IllegalArgumentException("image has no pixels to blur");
    }

    for (int i = 0; i < pixels[0][0].numChannels(); i++) {
      filter(i, kernel);
    }
  }

  @Override
  public Image combineBySepia() throws UnsupportedOperationException {
    int numberOfChannels = pixels[0][0].numChannels();

    if (numberOfChannels != 3) {
      throw new IllegalArgumentException("image should contain rgb component");
    }

    Image greyscale = this.combineByLuma();
    int width = greyscale.getWidth();
    int height = greyscale.getHeight();

    Pixel[][] p = new PixelImpl[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {

        int r = greyscale.getPixel(y, x).getChannel(0);
        int g = greyscale.getPixel(y, x).getChannel(1);
        int b = greyscale.getPixel(y, x).getChannel(2);

        int newR = (int) Math.round(0.393 * r + 0.769 * g + 0.189 * b);
        int newG = (int) Math.round(0.349 * r + 0.686 * g + 0.168 * b);
        int newB = (int) Math.round(0.272 * r + 0.534 * g + 0.131 * b);

        newR = Math.max(0, Math.min(255, newR));
        newG = Math.max(0, Math.min(255, newG));
        newB = Math.max(0, Math.min(255, newB));

        p[y][x] = new PixelImpl(newR, newG, newB);

      }
    }

    return new GrayscaleImage(width, height, maxColorValue, p);

  }

  @Override
  public Image dither() {
    int numberOfChannels = pixels[0][0].numChannels();

    if (numberOfChannels != 3) {
      throw new IllegalArgumentException("image should contain rgb component");
    }

    Image greyscale = this.combineByLuma();
    int width = greyscale.getWidth();
    int height = greyscale.getHeight();

    int[][] greyPixels = new int[height][width];
    Pixel[][] newPixels = new PixelImpl[height][width];

    // Convert to greyscale
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        greyPixels[y][x] = calculateLuma(pixels[y][x].getChannels());
      }
    }

    // Dither
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int oldColor = greyPixels[y][x];
        int newColor = oldColor > 127 ? 255 : 0;
        int error = oldColor - newColor;
        newPixels[y][x] = new PixelImpl(newColor, newColor, newColor);

        if (x < width - 1) {
          greyPixels[y][x+1] += (int) (7.0/16 * error);
        }
        if (y < height - 1 && x > 0) {
          greyPixels[y+1][x-1] += (int) (3.0/16 * error);
        }
        if (y < height - 1) {
          greyPixels[y+1][x] += (int) (5.0/16 * error);
        }
        if (y < height - 1 && x < width - 1) {
          greyPixels[y+1][x+1] += (int) (1.0/16 * error);
        }
      }
    }

    return new GrayscaleImage(width, height, maxColorValue, newPixels);

  }
}
