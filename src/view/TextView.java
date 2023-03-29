package view;

import java.io.IOException;
import java.io.PrintStream;

public class TextView implements ImageView {

  private final PrintStream out;

  private Appendable output = new StringBuffer();

  public TextView(PrintStream out) {
    this.out = out;
  }

  @Override
  public void display(String s) throws IOException {
    out.println(s);
    this.output.append(s);
  }

  @Override
  public Appendable outputString() {
    return output;
  }

}
