package view;

import java.io.IOException;

import javax.swing.JPanel;

/**
 * This interface represents all operations related to Tools.
 */
public interface ToolPanel extends Operations {
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
  String[] rgbCombine() throws IOException;

  /**
   * This method is used to retrieve the entire panel.
   *
   * @return returns a JPanel object representing the ToolPanel.
   */
  JPanel getPanel();
}
