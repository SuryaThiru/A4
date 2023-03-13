package controller;

import java.io.IOException;

public interface ImageController {

  void load(String imagePath, String imageName) throws IOException;

}
