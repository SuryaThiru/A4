package controller;

import java.io.IOException;

/**
 * This interface acts as a controller in our Image Manipulation Application and delegates tasks
 * respectively.
 */
public interface ImageController {
  void load(String imagePath, String imageName) throws IOException;
}
