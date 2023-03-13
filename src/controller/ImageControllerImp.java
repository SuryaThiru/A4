package controller;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.HashMap;
import java.util.Scanner;

import model.GrayscaleImage;
import model.Image;
import model.RGBImage;

import static helper.ImageUtil.getFileExtension;
import static helper.ImageUtil.ppmFileValidation;

public class ImageControllerImp implements ImageController {

  private HashMap<String, Image> images;

  private Image image;

  public ImageControllerImp(Image image) {
    this.image = image;
    this.images = new HashMap<>();
  }

  public void Load(String imagePath, String imageName) throws IOException {
    String fileExtension = getFileExtension(imagePath);
    if (!fileExtension.equals("ppm")) {
      throw new IOException("the current file extension is not supported by the application");
    }

    Scanner sc = ppmFileValidation(imagePath);

    if (sc == null) {
      throw new IOException("Invalid file / file name");
    }

    String content = extractContent(sc, imageName);

    if (content == null) {
      throw new FileSystemException("no content in file");
    }

    image.load(content);
  }

  public void Save(String filePath, String fileName) throws IOException {
    image = images.get(fileName);
    if(image == null) {
      throw new IOException("image not found");
    }

    image.save(filePath);
  }

  public void Brighten(int increment, String imageName, String updatedImageName) throws IOException {
    image = images.get(imageName);
    if(image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    images.put(updatedImageName, updatedImage);

    updatedImage.brighten(increment);
  }

  public void Darken(int increment, String imageName, String updatedImageName) throws IOException {
    image = images.get(imageName);
    if(image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    images.put(updatedImageName, updatedImage);

    updatedImage.darken(increment);
  }

  public void FlipVertical(String imageName, String updatedImageName) throws IOException {
    image = images.get(imageName);
    if(image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    images.put(updatedImageName, updatedImage);

    updatedImage.flipVertical();
  }

  public void FlipHorizontal(String imageName, String updatedImageName) throws IOException {
    image = images.get(imageName);
    if(image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    images.put(updatedImageName, updatedImage);

    updatedImage.flipHorizontal();
  }

  private String extractContent(Scanner sc, String imageName) {
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    if (isGrayscalePPM(maxValue)) {
      image = new GrayscaleImage(width, height, maxValue);
    } else if (isColourScalePPM(maxValue)) {
      image = new RGBImage(width, height, maxValue);
    } else {
      throw new IllegalArgumentException("Invalid max color value: " + maxValue);
    }

    images.put(imageName, image);
    sc.useDelimiter("\\A");
    return sc.hasNext() ? sc.next() : null;
  }

  private boolean isGrayscalePPM(int maxColorValue) {
    return maxColorValue == 1;
  }

  private boolean isColourScalePPM(int maxColorValue) {
    return maxColorValue == 255;
  }

}
