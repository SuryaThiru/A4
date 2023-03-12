import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.Scanner;

public class ImageControllerImpl implements ImageController {
  private ImageModel image;

  private int width;

  private int height;

  private int maxValue;

  public void load(String imagePath, String imageName) throws IOException {

    Scanner sc = ppmFileValidation(imagePath);

    if (sc == null) {
      throw new IOException("Invalid file / file name");
    }

    String content = extractSpecification(sc);

    if (maxValue == 1) {
      image = new GrayscaleImageModel(width, height, maxValue);
    } else if (maxValue == 255) {
      image = new RGBModel(width, height, maxValue);
    } else {
      throw new IllegalArgumentException("Invalid max color value: " + maxValue);
    }

    if (content == null) {
      throw new FileSystemException("no content in file");
    }

    image.load(content);
  }

  private String extractSpecification(Scanner sc) {
    width = sc.nextInt();
    System.out.println("Width of image: " + width);
    height = sc.nextInt();
    System.out.println("Height of image: " + height);
    maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    sc.useDelimiter("\\A");
    return sc.hasNext() ? sc.next() : null;
  }

  private Scanner ppmFileValidation(String filename) {
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
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");

      return null;
    }

    return sc;
  }
}
