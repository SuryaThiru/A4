package controller;

/**
 * This interface represents the GUI controller operations in the application.
 */
public interface Features {

  /**
   * This method helps in loading an image to the application.
   */
  void load();

  /**
   * This method is used for saving the image to a certain file path.
   */
  void save();

  /**
   * This method loops through all pixels in the image, and increases their red, green, and blue.
   */
  void brighten();

  /**
   * This method used for executing the Filter command from the controller.
   */
  void filter();

  /**
   * This method is used for executing the Grayscale commands from the controller.
   */
  void grayscale();

  /**
   * This method is used for executing flip from the controller.
   */
  void flip();

  /**
   * This method is used to convert an RGB Image to a Dithered Image.
   */
  void dither();

  /**
   * This method converts an GRB image into a sepia toned Image.
   */
  void sepia();

  /**
   * Combines the image from 3 channels.
   */
  void combine();

  /**
   * Splits the image into 3 channels.
   */
  void split();

  /**
   * This method is used to undo the image operation and revert the image back.
   */
  void undo();

  /**
   * This method is used to redo the image operation.
   */
  void redo();
}
