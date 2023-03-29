package view;

import java.io.PrintStream;

public class TextView implements ImageView {

  private PrintStream out;

  public TextView(PrintStream out) {
    this.out = out;
  }

  @Override
  public void display(String s) {
    out.println("String: " + s);
  }
}
