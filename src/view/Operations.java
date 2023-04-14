package view;

import javax.swing.JPanel;

import controller.Features;

/**
 * This interface represents all operations related to image application.
 */
public interface Operations {

  /**
   * This method handles all the events triggered by the user.
   *
   * @param features represents an object of type Features
   */
  void addFeatures(Features features);

  /**
   * This method is used to retrieve the entire panel.
   *
   * @return returns a JPanel object.
   */
  JPanel getPanel();
}
