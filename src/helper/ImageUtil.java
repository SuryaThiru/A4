package helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import model.Pixel;
import model.PixelImpl;

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
   * @param fileName represents the filename to be parsed.
   * @return returns the file extension.
   */
  public static String getFileExtension(String fileName) {
    int lastDotIndex = fileName.lastIndexOf(".");
    if (lastDotIndex > 0) {
      return fileName.substring(lastDotIndex + 1);
    }
    return "";
  }

  /**
   * This method represents is used to duplicate a pixel array.
   *
   * @param pixels represents the pixels of an Image.
   * @param width  represents the width of the Image.
   * @param height represents the height of the Image.
   * @return returns a new Pixel array object that is a duplicate of the passed array.
   */
  public static Pixel[][] duplicatePixels(Pixel[][] pixels, int width, int height)
          throws IllegalArgumentException {
    if (pixels == null) {
      throw new IllegalArgumentException("pixels should not be null");
    }

    Pixel[][] p = new PixelImpl[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int channelLength = pixels[i][j].numChannels();
        int[] ch = new int[channelLength];

        for (int k = 0; k < channelLength; k++) {
          ch[k] = pixels[i][j].getChannel(k);
        }

        p[i][j] = new PixelImpl(ch);
      }
    }

    return p;
  }

  /**
   * This method represents is used to read a text file.
   *
   * @param scan represents the scanner object of this instance.
   * @return returns a new BufferedReader object containing file content.
   * @throws IOException when there is a discrepancy in the file provided.
   */
  public static BufferedReader readTextFile(Scanner scan)
          throws IOException {
    String scriptFilePath = scan.next();
    int lastDotIndex = scriptFilePath.lastIndexOf(".");

    if (lastDotIndex > 0 && !scriptFilePath.substring(lastDotIndex + 1)
            .equals("txt")) {
      throw new IOException("invalid script being used. only txt files are allowed. "
              + "Try again\n");
    }
    return new BufferedReader(new FileReader(scriptFilePath));
  }
}
