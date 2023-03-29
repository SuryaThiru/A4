package controller;

import java.io.IOException;

/**
 * This interface represents the method to be executed by all the controller commands.
 */
public interface Command {
  /**
   * This method will be executed by all the classes of the commands classes.
   *
   * @throws IOException when there is a discrepancy in the input provided.
   */
  void execute() throws IOException;
}
