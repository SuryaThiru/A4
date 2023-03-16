package controller;

import java.io.IOException;

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

  boolean compareImages(String imageName, String updatedImageName) throws IOException;
  void combineByValue(String imageName, String updatedImageName) throws IOException;
  void combineByLuma(String imageName, String updatedImageName) throws IOException;
  void combineByIntensity(String imageName, String updatedImageName) throws IOException;
  void combineByComponent(int color, String imageName, String updatedImageName) throws IOException;

}
