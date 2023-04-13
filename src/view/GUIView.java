package view;

import java.awt.image.BufferedImage;

import controller.Features;

/**
 * This interface contains all operations that is required for image view gui.
 */
public interface GUIView {

  /**
   * This method handles all the events triggered by the user.
   *
   * @param features represents an object of type Features
   */
  void addFeatures(Features features);

  /**
   * This method is used to load the image into the application.
   *
   * @return returns a String
   */
  String load();

  /**
   * This method is used to save the image to a file path selected by the user.
   *
   * @return returns a String
   */
  String save();

  /**
   * This method handles the brightening and darkening events.
   *
   * @return returns an integer
   */
  int brighten();


  /**
   * This method handles the filter button click event by the user.
   *
   * @return returns a string
   */
  String filter();

  /**
   * This method handles the flip button click event by the user.
   *
   * @return returns a string
   */
  String flip();

  /**
   * This method handles the split button click event by the user.
   *
   * @return returns a string
   */
  String split();

  /**
   * This method handles the greyscaleFunction button click event by the user.
   *
   * @return returns a String.
   */
  String greyscaleFunction();

  /**
   * This method handles the RGBCombine button click event by the user.
   *
   * @return returns a string.
   */
  String[] rgbCombine();

  /**
   * This method is used to display an image and the histogram on the JPanel.
   *
   * @param histogram represents the histogram to be displayed after every operation.
   * @param image     represents the image.
   */
  void displayImage(int[][] histogram, BufferedImage image);

  /**
   * This method is used to display error when an operation is unsupported.
   *
   * @param errorMessage shows the proper error encountered during the operation
   */
  void displayError(String errorMessage);
}
