package view;

import java.io.IOException;
import java.io.PrintStream;

/**
 * This class represents the view in text format as output and implements the operations
 * of ImageView.
 */
public class TextView implements ImageView {

  final PrintStream out;

  final Appendable output;

  /**
   * This constructor initialises the TextView object with the passed values.
   *
   * @param out represents the PrintStream object that is used to print the output.
   */
  public TextView(PrintStream out) {
    this.out = out;
    this.output = new StringBuffer();
  }

  @Override
  public void display(String s) throws IOException {
    out.println(s + "\n");
    this.output.append(s).append("\n");
  }

  @Override
  public Appendable outputString() {
    return output;
  }

}
