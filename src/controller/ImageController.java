package controller;

import java.io.IOException;

public interface ImageController {

  void Load(String imagePath, String imageName) throws IOException;

  void Save(String filePath, String fileName) throws IOException;

  void Brighten(int increment, String imageName, String updatedImageName) throws IOException;

  void Darken(int increment, String imageName, String updatedImageName) throws IOException;

  void FlipVertical(String imageName, String updatedImageName) throws IOException;

  void FlipHorizontal(String imageName, String updatedImageName) throws IOException;

}
