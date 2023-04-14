package view;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import static view.Identifiers.ALLOWED_EXTENSIONS;

/**
 * This abstract class implements operations of Operations interface.
 */
public abstract class AbstractOperations extends JFrame implements Operations {

  final Component parentComponent;

  /**
   * This constructor initialises the AbstractOperations class and all its associated variables.
   *
   * @param parentComponent represents the main parent component of the gui
   */
  protected AbstractOperations(Component parentComponent) {
    this.parentComponent = parentComponent;
  }

  protected String loadHelper(Boolean calledByLoad, String dialogTitle, Component component)
          throws IOException {
    final JFileChooser fileChooser = new JFileChooser(".");
    int value;

    fileChooser.setDialogTitle(dialogTitle);
    if (calledByLoad) {
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "PNG,JPEG,BMP & PPM Images", ALLOWED_EXTENSIONS);
      fileChooser.setFileFilter(filter);
      value = fileChooser.showOpenDialog(component);
    } else {
      value = fileChooser.showSaveDialog(component);
    }
    if (value == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      return f.getAbsolutePath();
    } else if (value == JFileChooser.CANCEL_OPTION) {
      return null;
    } else {
      throw new IOException("Please enter correct Path");
    }
  }
}
