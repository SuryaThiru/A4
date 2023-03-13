package model;

import java.io.IOException;

/**
 * This interface acts as a Model in the Image Manipulation Application and holds all the
 * logic to the operations that can be performed by the user.
 */
public interface Image {
  void load(String content);

  void save(String filePath, String fileName) throws IOException;

  void flipHorizontal();

  void flipVertical();

  void brighten(int increment);

  Image[] splitChannels() throws UnsupportedOperationException;

  void combineChannels(Image[] channels) throws UnsupportedOperationException;
}