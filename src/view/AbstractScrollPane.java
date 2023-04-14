package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * This abstract class implements operations of ScrollPane interface.
 */
public abstract class AbstractScrollPane implements ScrollPane {
  JPanel jPanel;

  JScrollPane jScrollPane;

  /**
   * This constructor initialises the AbstractOperations class and all its associated variables.
   *
   * @param jPanel represents the JPanel
   */
  protected AbstractScrollPane(JPanel jPanel) {
    this.jPanel = jPanel;
  }
}
