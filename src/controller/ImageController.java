package controller;

import java.io.IOException;

import model.Image;

public interface ImageController {

  void load(String imagePath, String imageName) throws IOException;

  void save(String filePath, String fileName) throws IOException;

  void brighten(int increment, String imageName, String updatedImageName) throws IOException;

  void darken(int increment, String imageName, String updatedImageName) throws IOException;

  void flipVertical(String imageName, String updatedImageName) throws IOException;

  void flipHorizontal(String imageName, String updatedImageName) throws IOException;

  void split(String imageName, String redImageName, String greenImageName,
             String blueImageName) throws IOException;
  void combine(String imageName, String redImageName, String greenImageName,
               String blueImageName) throws IOException;

  void testCombine(String imageName, String updatedImageName) throws IOException;

}
