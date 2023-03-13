package model;

import java.io.IOException;

/**
 * This interface acts as a Model in the Image Manipulation Application and holds all the
 * logic to the operations that can be performed by the user.
 */
public interface Image {
  void load(String content);

  void save(String filePath) throws IOException;

  /**
   * This method flips the image horizontally by swapping each pixel in a row with its
   * corresponding pixel on the other side of the row.
   */
  void flipHorizontal();

  /**
   * This method function flips the image vertically by swapping each pixel in a column with its
   * corresponding pixel on the other side of the column.
   */
  void flipVertical();

  /**
   * This method loops through all pixels in the image, and increases their red, green, and blue
   * values by the given increment parameter.
   *
   * @param increment represents the increment value.
   */
  void brighten(int increment);

  Image[] splitChannels() throws UnsupportedOperationException;

  void combineChannels(Image[] channels) throws UnsupportedOperationException;
}