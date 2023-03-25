package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.Image;
import model.Pixel;
import model.RGBImage;

import static helper.ImageUtil.getFileExtension;
import static helper.ImageUtil.ppmFileValidation;

/**
 * This class represents the implementation for the controller.
 */
public class ImageControllerImp implements ImageController {

  private final HashMap<String, Image> images;

  private Image image;

  /**
   * Used to initialize the instance variables.
   *
   * @param image represent images
   */
  public ImageControllerImp(Image image) {
    this.image = image;
    this.images = new HashMap<>();
  }

  @Override
  public void load(String imagePath, String imageName) throws IOException {
    String fileExtension = getFileExtension(imagePath);

    if (fileExtension.equals("png") || fileExtension.equals("jpeg")
            || fileExtension.equals("bmp")) {
      image = image.load(ImageIO.read(new File(imagePath)));
      images.put(imageName, image);
      return;
    }

    if (!fileExtension.equals("ppm")) {
      throw new IOException("the current file extension is not supported by the application");
    }

    Scanner sc = ppmFileValidation(imagePath);

    if (sc == null) {
      throw new IOException("Invalid file / file name");
    }

    String content = extractContent(sc);

    if (content == null) {
      throw new FileSystemException("no content in file");
    }

    image = image.load(content);
    images.put(imageName, image);
  }

  @Override
  public void split(String imageName, String redImageName, String greenImageName,
                    String blueImageName) throws IOException {
    image = images.get(imageName);
    if (image == null) {
      throw new IOException("image not found");
    }

    Image[] imageSplits = image.splitChannels();
    images.put(redImageName, imageSplits[0]);
    images.put(greenImageName, imageSplits[1]);
    images.put(blueImageName, imageSplits[2]);
  }

  @Override
  public void combine(String updatedName, String redImageName, String greenImageName,
                      String blueImageName) throws IOException {
    Image[] combineChannels = new Image[3];
    combineChannels[0] = images.get(redImageName);
    combineChannels[1] = images.get(greenImageName);
    combineChannels[2] = images.get(blueImageName);

    if (combineChannels[0] == null || combineChannels[1] == null || combineChannels[2] == null) {
      throw new IOException("image not found: " + redImageName + "- " + combineChannels[0] + " "
              + greenImageName + "- " + combineChannels[1] + " " + blueImageName + "- "
              + combineChannels[2]);
    }

    image = new RGBImage(0, 0, 255);

    image.validateCombineChannels(combineChannels);
    image.combineChannels(combineChannels);
    images.put(updatedName, image);
  }

  @Override
  public boolean compareImages(String imageName, String updatedName) throws IOException {
    image = images.get(imageName);
    if (image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = images.get(updatedName);
    if (updatedImage == null) {
      throw new IOException("updated image not found");
    }

    return image.compareImages(updatedImage);
  }

  @Override
  public void save(String filePath, String fileName) throws IOException {
    image = images.get(fileName);
    if (image == null) {
      throw new IOException("image not found");
    }

    int width = image.getWidth();
    int height = image.getHeight();
    int maxColorValue = image.getMaxColorValue();

    // extract extension from filePath and determine the type of save.
    String fileExtension = getFileExtension(filePath);
    if (fileExtension.equals("png") || fileExtension.equals("jpeg")
            || fileExtension.equals("bmp")) {
      BufferedImage bufferedImage = save(width, height);
      File output = new File(filePath);
      ImageIO.write(bufferedImage, fileExtension, output);
    }

    save(width, height, maxColorValue, filePath);
  }

  private void save(int width, int height, int maxColorValue, String filePath)
          throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
    writer.write("P3" + "\n");
    writer.write(width + " " + height + "\n");
    writer.write(maxColorValue + "\n");

    // Write the image data
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel pixel = image.getPixel(i, j);
        writer.write(pixel.getChannels(0) + " " + pixel.getChannels(1)
                + " " + pixel.getChannels(2) + " ");
      }
      writer.newLine();
    }

    writer.close();
  }

  private BufferedImage save(int width, int height) {
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        Pixel pixel = image.getPixel(x, y);
        int rgb = (pixel.getChannels(0) << 16) | (pixel.getChannels(1) << 8)
                | pixel.getChannels(2);
        bufferedImage.setRGB(y, x, rgb);
      }
    }

    return bufferedImage;
  }

  @Override
  public void brighten(int increment, String imageName, String updatedImageName)
          throws IOException {
    image = images.get(imageName);
    if (image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    updatedImage.brighten(increment);
    images.put(updatedImageName, updatedImage);
  }

  @Override
  public void darken(int increment, String imageName, String updatedImageName) throws IOException {
    image = images.get(imageName);
    if (image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    updatedImage.darken(increment);

    images.put(updatedImageName, updatedImage);
  }

  @Override
  public void flipVertical(String imageName, String updatedImageName) throws IOException {
    image = images.get(imageName);
    if (image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    updatedImage.flipVertical();

    images.put(updatedImageName, updatedImage);
  }

  @Override
  public void flipHorizontal(String imageName, String updatedImageName) throws IOException {
    image = images.get(imageName);
    if (image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    updatedImage.flipHorizontal();

    images.put(updatedImageName, updatedImage);
  }

  @Override
  public void combineByValue(String imageName, String updatedImageName) throws IOException {
    image = images.get(imageName);
    if (image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.combineByValue();
    images.put(updatedImageName, updatedImage);
  }

  @Override
  public void combineByLuma(String imageName, String updatedImageName) throws IOException {
    image = images.get(imageName);
    if (image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    updatedImage.combineByLuma();

    images.put(updatedImageName, updatedImage);
  }

  @Override
  public void combineByIntensity(String imageName, String updatedImageName) throws IOException {
    image = images.get(imageName);
    if (image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    updatedImage.combineByIntensity();

    images.put(updatedImageName, updatedImage);
  }

  @Override
  public void combineByComponent(int color, String imageName, String updatedImageName)
          throws IOException {
    image = images.get(imageName);
    if (image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    Image[] splitImages = updatedImage.splitChannels();

    images.put(updatedImageName, splitImages[color].duplicate());
  }

  private String extractContent(Scanner sc) {

    sc.useDelimiter("\\A");
    return sc.hasNext() ? sc.next() : null;
  }

}
