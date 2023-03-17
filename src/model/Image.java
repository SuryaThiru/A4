package model;

import java.io.IOException;

/**
 * This interface acts as a Model in the Image Manipulation Application and holds all the
 * logic to the operations that can be performed by the user.
 */
public interface Image {

  /**
   * This method helps in loading an image to the application.
   * @param content represents the extracted content from the ppm file.
   * @return
   */
  Image load(String content);

  /**
   * This method is used for saving the image to a certain file path.
   * @param filePath represents file path to which the image is to be saved.
   * @throws IOException throws an Exception if the image to be saved is null.
   */
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

  /**
   * This method loops through all pixels in the image, and decreases their red, green, and blue
   * values by the given decrement parameter.
   *
   * @param decrement represents the decrement value.
   */
  void darken(int decrement);

  /**
   This method creates a duplicate of the image
   * values by the given decrement parameter.
   *
   * @return the duplicate image object with current image properties
   */
  Image duplicate();

  /**
   * Splits the image into 3 channels.
   * @return returns an array of 3 channels.
   * @throws UnsupportedOperationException if we are trying to split greyscale images.
   */
  Image[] splitChannels() throws UnsupportedOperationException;

  /**
   * This method is used to combine the 3 channels R, G, B respectively.
   * @param channels represents all the r, g, b channels.
   * @throws UnsupportedOperationException throws this exception when we try combining rgb model.
   */
  void combineChannels(Image[] channels) throws UnsupportedOperationException;

  /**
   * This method is used to combine the channels by the pixel value of the rgb components.
   * @return returns a combined image.
   */
  Image combineByValue();

  /**
   * This method is used to combine the channels by the Intensity value of the rgb components.
   * @return
   */
  Image combineByIntensity();

  /**
   * This method is used to combine the channels by the Luma value of the rgb components.
   * @return
   * @throws UnsupportedOperationException is thrown when trying to combine greyscale images.
   */
  Image combineByLuma() throws UnsupportedOperationException;

  /**
   * This method is used to compare 2 images.
   * @param updatedImage represents the second image.
   * @return return true if the images are identical and false otherwise.
   * @throws UnsupportedOperationException is thrown when height and width of the images dont match.
   */
  boolean compareImages(Image updatedImage) throws UnsupportedOperationException;

  /**
   * This method is used to validate the combined channels.
   * @param channels represents the r,g,b channels.
   * @return returns true if validation is successful otherwise returns false.
   * @throws IllegalArgumentException is thrown when there are more channels than supported.
   */
  boolean validateCombineChannels(Image[] channels) throws IllegalArgumentException;

  /**
   * This method is used to check if the given image object is a greyscale image.
   * @return returns true or false.
   */
  boolean isGrayscale();

  /**
   * This method is used to get the width of the image.
   * @return returns the width.
   */
  int getWidth();

  /**
   * This method is used to get the height of the image.
   * @return returns the height.
   */
  int getHeight();

  /**
   * This method is used to get the pixel value for a particular row & column.
   * @param x represents the row.
   * @param y represents the column.
   * @return returns a pixel object.
   */
  Pixel getPixel(int x, int y);
}