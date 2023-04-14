package view;

import java.io.IOException;

/**
 * This interface represents all operations related to FilePanel.
 */
public interface FilePanel extends Operations {

  /**
   * This method is used to load the image into the application.
   *
   * @return returns a String
   */
  String load() throws IOException;

  /**
   * This method is used to save the image to a file path selected by the user.
   *
   * @return returns a String
   */
  String save() throws IOException;
}
