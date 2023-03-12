//import java.io.*;
//import java.util.*;
//
//public class ImageManipulator {
//
//  private Map<String, Image> images = new HashMap<String, Image>();
//
//  public void executeCommand(String command) throws IOException {
//    String[] tokens = command.split("\\s+");
//    String commandName = tokens[0];
//
//    switch (commandName) {
//      case "load":
//        loadImage(tokens[1], tokens[2]);
//        break;
//      case "save":
//        saveImage(tokens[1], tokens[2]);
//        break;
//      case "greyscale":
//        applyGreyscale(tokens[1], tokens[2], tokens[3]);
//        break;
//      case "horizontal-flip":
//        applyHorizontalFlip(tokens[1], tokens[2]);
//        break;
//      case "vertical-flip":
//        applyVerticalFlip(tokens[1], tokens[2]);
//        break;
//      case "brighten":
//        applyBrightness(tokens[1], tokens[2], tokens[3]);
//        break;
//      case "rgb-split":
//        applyRGBSplit(tokens[1], tokens[2], tokens[3], tokens[4]);
//        break;
//      case "rgb-combine":
//        applyRGBCombine(tokens[1], tokens[2], tokens[3], tokens[4]);
//        break;
//      case "run":
//        runScript(tokens[1]);
//        break;
//      default:
//        System.out.println("Invalid command: " + command);
//        break;
//    }
//  }
//
//  private void loadImage(String imagePath, String imageName) throws IOException {
//    Image image = new PPMImage(imagePath);
//    images.put(imageName, image);
//  }
//
//  private void saveImage(String imagePath, String imageName) throws IOException {
//    Image image = images.get(imageName);
//    if (image == null) {
//      System.out.println("Image not found: " + imageName);
//      return;
//    }
//    image.save(imagePath);
//  }
//
//  private void applyGreyscale(String component, String sourceImageName, String destImageName) {
//    Image sourceImage = images.get(sourceImageName);
//    if (sourceImage == null) {
//      System.out.println("Image not found: " + sourceImageName);
//      return;
//    }
//    Image destImage = sourceImage.createGreyscaleImage(component);
//    images.put(destImageName, destImage);
//  }
//
//  private void applyHorizontalFlip(String sourceImageName, String destImageName) {
//    Image sourceImage = images.get(sourceImageName);
//    if (sourceImage == null) {
//      System.out.println("Image not found: " + sourceImageName);
//      return;
//    }
//    Image destImage = sourceImage.createHorizontalFlipImage();
//    images.put(destImageName, destImage);
//  }
//
//  private void applyVerticalFlip(String sourceImageName, String destImageName) {
//    Image sourceImage = images.get(sourceImageName);
//    if (sourceImage == null) {
//      System.out.println("Image not found: " + sourceImageName);
//      return;
//    }
//    Image destImage = sourceImage.createVerticalFlipImage();
//    images.put(destImageName, destImage);
//  }
//
//  private void applyBrightness(String increment, String sourceImageName, String destImageName) {
//    Image sourceImage = images.get(sourceImageName);
//    if (sourceImage == null) {
//      System.out.println("Image not found: " + sourceImageName);
//      return;
//    }
//    Image destImage = sourceImage.createBrightnessImage(Integer.parseInt(increment));
//    images.put(destImageName, destImage);
//  }
//
//  private void applyRGBSplit(String sourceImageName, String destImageNameRed, String destImage
//
//
//model.Pixel
//  Red  (0, 255)
//  Green (0, 255)
//  Blue  (0, 255)