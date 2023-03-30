import org.junit.Test;

import java.io.IOException;

import view.ImageView;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the view part of the mvc architecture of the application.
 */
public class TextViewTest {

  @Test
  public void testDisplay() throws IOException {
    ImageView view = new TextView(System.out);
    String s = "testing display";
    view.display(s);
    assertEquals(s + "\n", view.outputString().toString());
  }

}