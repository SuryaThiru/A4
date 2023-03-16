package controller;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.HashMap;
import java.util.Scanner;

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

  public void load(String imagePath, String imageName) throws IOException {
    String fileExtension = getFileExtension(imagePath);
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

  public void split(String imageName, String redImageName, String greenImageName,
                    String blueImageName) throws IOException {
    image = images.get(imageName);
    if(image == null) {
      throw new IOException("image not found");
    }

    Image[] imageSplits = image.splitChannels();
    images.put(redImageName, imageSplits[0]);
    images.put(greenImageName, imageSplits[1]);
    images.put(blueImageName, imageSplits[2]);
  }

  public void combine(String updatedName, String redImageName, String greenImageName,
                    String blueImageName) throws IOException {
    Image[] combineChannels = new Image[3];
    combineChannels[0] = images.get(redImageName);
    combineChannels[1] = images.get(greenImageName);
    combineChannels[2] = images.get(blueImageName);

    if(combineChannels[0] == null || combineChannels[1] == null || combineChannels[2] == null) {
      throw new IOException("image not found: " + redImageName + "- " + combineChannels[0] + " "
              + greenImageName + "- " + combineChannels[1] + " " + blueImageName + "- "
              + combineChannels[2]);
    }

    image = new RGBImage(0, 0, 255);

    image.validateCombineChannels(combineChannels);
    image.combineChannels(combineChannels);
    images.put(updatedName, image);
  }

  public void testCombine(String imageName, String updatedName) throws IOException {
    image = images.get(imageName);
    if(image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = images.get(updatedName);
    if(updatedImage == null) {
      throw new IOException("updated image not found");
    }

    boolean flag = image.compareImages(updatedImage);
    if(!flag) {
      System.out.println("Not the same images");
      return ;
    }
    System.out.println("same images");
  }

  public void save(String filePath, String fileName) throws IOException {
    image = images.get(fileName);
    if(image == null) {
      throw new IOException("image not found");
    }

    image.save(filePath);
  }

  public void brighten(int increment, String imageName, String updatedImageName)
          throws IOException {
    image = images.get(imageName);
    if(image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    updatedImage.brighten(increment);
    images.put(updatedImageName, updatedImage);
  }

  public void darken(int increment, String imageName, String updatedImageName) throws IOException {
    image = images.get(imageName);
    if(image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    updatedImage.darken(increment);

    images.put(updatedImageName, updatedImage);
  }

  public void flipVertical(String imageName, String updatedImageName) throws IOException {
    image = images.get(imageName);
    if(image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    updatedImage.flipVertical();

    images.put(updatedImageName, updatedImage);
  }

  public void flipHorizontal(String imageName, String updatedImageName) throws IOException {
    image = images.get(imageName);
    if(image == null) {
      throw new IOException("image not found");
    }

    Image updatedImage = image.duplicate();
    updatedImage.flipHorizontal();

    images.put(updatedImageName, updatedImage);
  }

  private String extractContent(Scanner sc) {

    sc.useDelimiter("\\A");
    return sc.hasNext() ? sc.next() : null;
  }

}
