package view;

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
}
