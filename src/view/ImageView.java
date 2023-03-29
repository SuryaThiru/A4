package view;

import java.io.IOException;

/**
 * This interface represents the view part of the MVC architecture of our application.
 */
public interface ImageView {

  /**
   * This method is used to display text on console.
   *
   * @param s represents the string to be displayed
   * @throws IOException throws an exception at the time of append.
   */
  void display(String s) throws IOException;

  /**
   * This method returns the complete output string.
   *
   * @return returns an Appendable object
   */
  Appendable outputString();
}
