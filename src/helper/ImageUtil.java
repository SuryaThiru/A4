package helper;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Pixel;

/**
 * This file contains the utility methods being used in the application.
 */
public class ImageUtil {

  /**
   * This method takes in the file name and validates it based on the allowed file types.
   *
   * @param filename Represents the name of the input file.
   * @return returns the scanner object.
   */
  public static Scanner ppmFileValidation(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3. "
              + "Your file magic number: " + token);

      return null;
    }

    return sc;
  }

  /**
   * This method is used to get file extension.
   *
   * @param fileName filename.
   * @return returns
   */
  public static String getFileExtension(String fileName) {
    int lastDotIndex = fileName.lastIndexOf(".");
    if (lastDotIndex > 0) {
      return fileName.substring(lastDotIndex + 1);
    } else {
      return "";
    }
  }

  /**
   * This method represents get pixels.
   *
   * @param pixels represents the pixels of an Image.
   * @param width  represents the width of the Image.
   * @param height represents the height of the Image.
   * @return returns
   */
  public static Pixel[][] getPixels(Pixel[][] pixels, int width, int height)
          throws IllegalArgumentException {
    if (pixels == null) {
      throw new IllegalArgumentException("pixels should not be null");
    }

    Pixel[][] p = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int channelLength = pixels[i][j].numChannels();
        int[] ch = new int[channelLength];

        for (int k = 0; k < channelLength; k++) {
          ch[k] = pixels[i][j].getChannels(k);
        }

        p[i][j] = new Pixel(ch);
      }
    }

    return p;
  }

  //  public static Pixel[][] imageConvert(BufferedImage image) {
  //    int width = image.getWidth();
  //    int height = image.getHeight();
  //    Pixel[][] pixels = new Pixel[width][height];
  //
  //    for (int x = 0; x < width; x++) {
  //      for (int y = 0; y < height; y++) {
  //        int rgb = image.getRGB(x, y);
  //        int red = (rgb >> 16) & 0xFF;
  //        int green = (rgb >> 8) & 0xFF;
  //        int blue = rgb & 0xFF;
  //        pixels[x][y] = new Pixel(red, green, blue);
  //      }
  //    }
  //
  //    return pixels;
  //  }

  //  /**
  //   * Read an image file in the PPM format and print the colors.
  //   *
  //   * @param filename the path of the file.
  //   */
  //  public static RGBImage readPPM(String filename) {
  //    Scanner sc;
  //
  //    try {
  //      sc = new Scanner(new FileInputStream(filename));
  //    } catch (FileNotFoundException e) {
  //      System.out.println("File " + filename + " not found!");
  //      return null;
  //    }
  //    StringBuilder builder = new StringBuilder();
  //    //read the file line by line, and populate a string. This will throw away any comment lines
  //    while (sc.hasNextLine()) {
  //      String s = sc.nextLine();
  //      if (s.charAt(0) != '#') {
  //        builder.append(s + System.lineSeparator());
  //      }
  //    }
  //
  //    //now set up the scanner to read from the string we just built
  //    sc = new Scanner(builder.toString());
  //
  //    String token;
  //
  //    token = sc.next();
  //    if (!token.equals("P3")) {
  //      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
  //    }
  //    int width = sc.nextInt();
  //    System.out.println("Width of image: " + width);
  //    int height = sc.nextInt();
  //    System.out.println("Height of image: " + height);
  //    int maxValue = sc.nextInt();
  //    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
  //
  //    Pixel[][] p = new Pixel[height][width];
  //    for (int i = 0; i < height; i++) {
  //      for (int j = 0; j < width; j++) {
  //        int r = sc.nextInt();
  //        int g = sc.nextInt();
  //        int b = sc.nextInt();
  //        p[i][j] = new Pixel(r, g, b);
  //      }
  //    }
  //
  //    return new RGBImage(width, height, maxValue, p);
  //  }
}
