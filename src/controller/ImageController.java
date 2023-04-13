package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This interface represents the controller in the application.
 */
public interface ImageController {

  /**
   * This method helps in loading an image to the application.
   *
   * @param imagePath represents teh path of the image.
   * @param imageName represents image name.
   * @throws IOException throws an exception.
   */
  void load(String imagePath, String imageName) throws IOException;

  /**
   * This method is used for saving the image to a certain file path.
   *
   * @param filePath represents the file path.
   * @param fileName represents the file name.
   * @throws IOException throws an exception.
   */
  void save(String filePath, String fileName) throws IOException;

  /**
   * his method loops through all pixels in the image, and increases their red, green, and blue.
   *
   * @param increment        represents the increment value.
   * @param imageName        represents the image name.
   * @param updatedImageName represents the new image name.
   * @throws IOException throws an exception.
   */
  void brighten(int increment, String imageName, String updatedImageName) throws IOException;

  /**
   * This method loops through all pixels in the image, and decreases their red, green, and blue.
   *
   * @param increment        represents the increment value.
   * @param imageName        represents the imageName value.
   * @param updatedImageName represents the updatedImageName value.
   * @throws IOException throws an exception.
   */
  void darken(int increment, String imageName, String updatedImageName) throws IOException;

  /**
   * This method function flips the image vertically by swapping each pixel in a column with its
   * corresponding pixel on the other side of the column.
   *
   * @param imageName        represents the imageName value.
   * @param updatedImageName represents the updatedImageName value.
   * @throws IOException throws an exception.
   */
  void flipVertical(String imageName, String updatedImageName) throws IOException;

  /**
   * This method flips the image horizontally by swapping each pixel in a row with its
   * corresponding pixel on the other side of the row.
   *
   * @param imageName        represents the imageName value.
   * @param updatedImageName represents the updatedImageName value.
   * @throws IOException throws an exception.
   */
  void flipHorizontal(String imageName, String updatedImageName) throws IOException;

  /**
   * Splits the image into 3 channels.
   *
   * @param imageName      represents the imageName value.
   * @param redImageName   represents the red value.
   * @param greenImageName represents the green value.
   * @param blueImageName  represents the blue value.
   * @throws IOException throws an exception.
   */
  void split(String imageName, String redImageName, String greenImageName,
             String blueImageName) throws IOException;

  /**
   * Combines the image from 3 channels.
   *
   * @param imageName      represents the imageName value.
   * @param redImageName   represents the red value.
   * @param greenImageName represents the green value.
   * @param blueImageName  represents the blue value.
   * @throws IOException throws an exception.
   */
  void combine(String imageName, String redImageName, String greenImageName,
               String blueImageName) throws IOException;

  /**
   * Compares 2 images.
   *
   * @param imageName        represents the imageName value.
   * @param updatedImageName represents the updatedImageName value.
   * @return returns true or false.
   * @throws IOException throws an exception.
   */
  boolean compareImages(String imageName, String updatedImageName) throws IOException;

  /**
   * This method is used to combine the channels by the pixel value of the rgb components.
   *
   * @param imageName        represents the imageName value.
   * @param updatedImageName represents the updatedImageName value.
   * @throws IOException throws an exception.
   */
  void combineByValue(String imageName, String updatedImageName) throws IOException;

  /**
   * This method is used to combine the channels by the Luma value of the rgb components.
   *
   * @param imageName        represents the imageName value.
   * @param updatedImageName represents the updatedImageName value.
   * @throws IOException throws an exception.
   */
  void combineByLuma(String imageName, String updatedImageName) throws IOException;

  /**
   * This method is used to combine the channels by the Intensity value of the rgb components.
   *
   * @param imageName        represents the imageName value.
   * @param updatedImageName represents the updatedImageName value.
   * @throws IOException throws an exception.
   */
  void combineByIntensity(String imageName, String updatedImageName) throws IOException;

  /**
   * This combines the all the different components of an Image.
   *
   * @param color            represents the color of the Channel.
   * @param imageName        imageName represents the image name.
   * @param updatedImageName represents the updated Image name.
   * @throws IOException throws an exception if the operation is not supported.
   */
  void combineByComponent(int color, String imageName, String updatedImageName) throws IOException;

  /**
   * This method converts an GRB image into a sepia toned Image.
   *
   * @param imageName        represents the current Image name
   * @param updatedImageName represents the updatedImage name.
   * @throws IOException throws an exception if the operation is not supported.
   */
  void combineBySepia(String imageName, String updatedImageName) throws IOException;

  /**
   * This method is used to convert an RGB Image to a Dithered Image.
   *
   * @param imageName        represents the current Image name
   * @param updatedImageName represents the updatedImage name.
   * @throws IOException throws an exception if the operation is not supported.
   */
  void dither(String imageName, String updatedImageName) throws IOException;

  void filter(String imageName, String updatedImageName, String s) throws IOException;

  /**
   * Perform histogram calculation based on current image data. This should take into account
   * any image operations that have been applied.
   *
   * @param imageName represents the current Image name
   * @return histogram array
   * @throws IOException throws an exception if the operation is not supported.
   */
  int[][] calculateHistogram(String imageName) throws IOException;

  /**
   * Update the current image data and notify the view to update the histogram display.
   *
   * @param imageName represents the current Image name
   * @return updated image object
   */
  BufferedImage updatedImage(String imageName);
}
