package view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import static view.Identifiers.HISTOGRAM_TITLE;
import static view.Identifiers.PANEL_DIMENSION_H;
import static view.Identifiers.PANEL_DIMENSION_W;
import static view.Identifiers.SCROLLPANE_DIMENSION_H;
import static view.Identifiers.SCROLLPANE_DIMENSION_W;
import static view.Identifiers.VERTICAL_SCALE;

/**
 * This class implements all operations of ScrollPane interface for the image histogram.
 */
public class HistogramScrollPane extends AbstractScrollPane {

  /**
   * This constructor initialises the HistogramScrollPane object and set up the JPanel
   * and ScrollPane.
   *
   * @param histPanel represents the panel for histogram
   */
  public HistogramScrollPane(JPanel histPanel) {
    super(histPanel);
    setScrollPane();
  }

  @Override
  public void setScrollPane() {
    jPanel.setPreferredSize(new Dimension(PANEL_DIMENSION_W,
            PANEL_DIMENSION_H));

    jPanel.setBorder(BorderFactory.createTitledBorder(HISTOGRAM_TITLE));
    jPanel.setLayout(new BorderLayout());
    jPanel.setVisible(true);
    jScrollPane = new JScrollPane(this.jPanel);
    jScrollPane.setPreferredSize(new Dimension(SCROLLPANE_DIMENSION_W,
            SCROLLPANE_DIMENSION_H));
  }

  /**
   * This function is used to display the histogram after every step.
   *
   * @param histogram represents the histogram array
   */
  void displayHistogram(int[][] histogram) {
    jPanel.removeAll();
    JPanel chartPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x1;
        int x2;
        int y1;
        int y2;

        Graphics2D g2d = (Graphics2D) g;
        int width = jPanel.getWidth();
        int height = jPanel.getHeight();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA};

        for (int i = 0; i < histogram.length; i++) {

          g2d.setColor(colors[i]);
          int max = findMaxValue(histogram[i]);
          double scaleFactor = (double) height / max;

          for (int j = 1; j < histogram[i].length - 1; j++) {

            x1 = (int) (((double) width / (histogram[i].length - 1)) * (j - 1));
            y1 = height - (int) (histogram[i][j - 1] * scaleFactor) - VERTICAL_SCALE;
            x2 = (int) (((double) width / (histogram[i].length - 1)) * j);
            y2 = height - (int) (histogram[i][j] * scaleFactor) - VERTICAL_SCALE;

            g2d.drawLine(x1, y1, x2, y2);
          }
        }
      }
    };

    jPanel.add(chartPanel);
    jPanel.revalidate();
    jPanel.repaint();
  }

  /**
   * This method is used to find the mac value among the array of data.
   *
   * @param data represents the data array
   * @return returns the max value
   */
  int findMaxValue(int[] data) {
    int max = data[0];
    for (int i = 1; i < data.length; i++) {
      if (data[i] > max) {
        max = data[i];
      }
    }
    return max;
  }
}
