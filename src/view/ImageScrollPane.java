package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;


import static view.Identifiers.IMAGE_TITLE;
import static view.Identifiers.SCROLLPANE_DIMENSION_H;
import static view.Identifiers.SCROLLPANE_DIMENSION_W;

/**
 * This class implements all operations of ScrollPane interface for the image.
 */
public class ImageScrollPane extends AbstractScrollPane {

  /**
   * This constructor initialises the ImageScrollPane object and set up the JPanel
   * and ScrollPane.
   *
   * @param imagePanel represents the panel for image
   */
  public ImageScrollPane(JPanel imagePanel) {
    super(imagePanel);
    setScrollPane();
  }

  @Override
  public void setScrollPane() {
    jPanel.setBorder(BorderFactory.createTitledBorder(IMAGE_TITLE));
    jPanel.setVisible(true);
    jScrollPane = new JScrollPane(jPanel);
    jScrollPane.setPreferredSize(new Dimension(SCROLLPANE_DIMENSION_W,
            SCROLLPANE_DIMENSION_H));
  }
}
