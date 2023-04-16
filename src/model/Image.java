package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This interface acts as a Model in the Image Manipulation Application and holds all the
 * logic to the operations that can be performed by the user.
 */
public interface Image {

  /**
   * This method helps in loading an image to the application.
   *
   * @param content represents the extracted content from the ppm file.
   * @return returns an Image object.
   */
  Image load(String content);

  /**
   * This method is used to load other types of image formats apart from ppm.
   *
   * @param image represents a BufferedImage object.
   * @return returns an Image object.
   */
  Image load(BufferedImage image);

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
   * This method creates a duplicate of the image
   * values by the given decrement parameter.
   *
   * @return the duplicate image object with current image properties
   */
  Image duplicate();

  /**
   * Splits the image into 3 channels.
   *
   * @return returns an array of 3 channels.
   * @throws UnsupportedOperationException if we are trying to split greyscale images.
   */
  Image[] splitChannels() throws UnsupportedOperationException;

  /**
   * This method is used to combine the 3 channels R, G, B respectively.
   *
   * @param channels represents all the r, g, b channels.
   * @throws UnsupportedOperationException throws this exception when we try combining rgb model.
   */
  void combineChannels(Image[] channels) throws UnsupportedOperationException;

  /**
   * This method is used to combine the channels by the pixel value of the rgb components.
   *
   * @return returns a combined image.
   */
  Image combineByValue();

  /**
   * This method is used to combine the channels by the Intensity value of the rgb components.
   *
   * @return
   */
  Image combineByIntensity();

  /**
   * This method is used to combine the channels by the Luma value of the rgb components.
   *
   * @return returns the Image object.
   * @throws UnsupportedOperationException is thrown when trying to combine greyscale images.
   */
  Image combineByLuma() throws UnsupportedOperationException;

  /**
   * This method is used to compare 2 images.
   *
   * @param updatedImage represents the second image.
   * @return return true if the images are identical and false otherwise.
   * @throws UnsupportedOperationException is thrown when height and width of the images dont match.
   */
  boolean compareImages(Image updatedImage) throws UnsupportedOperationException;

  /**
   * This method is used to validate the combined channels.
   *
   * @param channels represents the r,g,b channels.
   * @return returns true if validation is successful otherwise returns false.
   * @throws IllegalArgumentException is thrown when there are more channels than supported.
   */
  boolean validateCombineChannels(Image[] channels) throws IllegalArgumentException;

  /**
   * This method is used to check if the given image object is a greyscale image.
   *
   * @return returns true or false.
   */
  boolean isGrayscale();

  /**
   * This method is used to get the width of the image.
   *
   * @return returns the width.
   */
  int getWidth();

  /**
   * This method is used to get the height of the image.
   *
   * @return returns the height.
   */
  int getHeight();

  /**
   * This method is used to get the pixel value for a particular row & column.
   *
   * @param x represents the row.
   * @param y represents the column.
   * @return returns a pixel object.
   */
  Pixel getPixel(int x, int y);

  /**
   * This method is used to blur the current image.
   */
  void blur();

  /**
   * This method is used to sharpen the current image.
   */
  void sharpen();

  /**
   * This method is used to get the maxColorValue of the image.
   *
   * @return returns the maxColorValue.
   */
  int getMaxColorValue();

  /**
   * This method is used to convert an Image to a sepia toned Image.
   *
   * @return returns the Image object.
   * @throws UnsupportedOperationException is thrown when trying to combine greyscale images.
   */
  Image combineBySepia() throws UnsupportedOperationException;

  /**
   * This method is used to convert an RGB Image to a Dithered Image.
   *
   * @return returns the Image Object.
   * @throws UnsupportedOperationException is thrown when trying to combine greyscale images.
   */
  Image dither() throws UnsupportedOperationException;

  /**
   * Get a mosaicked version of the image.
   *
   * @param seeds number of points in the image to cluster pixels to
   * @return mosaicked image
   */
  Image mosaick(int seeds);

  /**
   * Calculates the histogram for the image.
   *
   * @return a 2D array of integers representing the histogram
   * @throws IOException is thrown when histogram operation is no possible.
   */
  int[][] calculateHistogram() throws IOException;
}