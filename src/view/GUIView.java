package view;

import java.awt.image.BufferedImage;

/**
 * This interface contains all operations that is required for image view gui.
 */
public interface GUIView extends ToolPanel, FilePanel {

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
