package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import controller.Features;

import static view.Identifiers.WINDOW_DIMENSION_H;
import static view.Identifiers.WINDOW_DIMENSION_W;

/**
 * This view is used to show the GUI of the Application.
 */
public class GUIViewImp extends JFrame implements GUIView {
  final ImageScrollPane imageScrollPane;
  final HistogramScrollPane histogramScrollPane;
  final ToolPanel toolPanel;
  final FilePanel filePanel;

  /**
   * Constructs a GUIViewImpl object and initialises the caption.
   *
   * @param caption the caption to be displayed in the title bar of the GUIViewImpl window.
   */
  public GUIViewImp(String caption) {
    super(caption);

    setSize(WINDOW_DIMENSION_W, WINDOW_DIMENSION_H);
    setLocationRelativeTo(null);
    setBackground(Color.BLACK);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel mainPanel = new JPanel(new BorderLayout());

    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    imageScrollPane = new ImageScrollPane(new JPanel());
    mainPanel.add(imageScrollPane.jScrollPane, BorderLayout.CENTER);

    histogramScrollPane = new HistogramScrollPane(new JPanel());
    mainPanel.add(histogramScrollPane.jScrollPane, BorderLayout.LINE_END);

    toolPanel = new ToolPanelImpl(new JPanel(), this);
    mainPanel.add(toolPanel.getPanel(), BorderLayout.PAGE_END);

    filePanel = new FilePanelImpl(new JPanel(), this);
    mainPanel.add(filePanel.getPanel(), BorderLayout.PAGE_START);
    pack();
    setVisible(true);

  }

  @Override
  public void displayError(String errorMessage) {
    JOptionPane.showMessageDialog(this, errorMessage,
            "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void displayImage(int[][] histogram, BufferedImage image) {
    imageScrollPane.jPanel.removeAll();
    if (image != null) {
      JLabel picLabel = new JLabel(new ImageIcon(image));
      imageScrollPane.jPanel.add(picLabel, BorderLayout.CENTER);
    }
    imageScrollPane.jPanel.revalidate();
    imageScrollPane.jPanel.repaint();
    histogramScrollPane.displayHistogram(histogram);
  }

  @Override
  public String load() throws IOException {
    return filePanel.load();
  }

  @Override
  public String save() throws IOException {
    return filePanel.save();
  }

  @Override
  public void addFeatures(Features features) {
    filePanel.addFeatures(features);
    toolPanel.addFeatures(features);
  }

  @Override
  public int brighten() {
    return toolPanel.brighten();
  }

  @Override
  public String filter() {
    return toolPanel.filter();
  }

  @Override
  public String flip() {
    return toolPanel.flip();
  }

  @Override
  public String split() {
    return toolPanel.split();
  }

  @Override
  public String greyscaleFunction() {
    return toolPanel.greyscaleFunction();
  }

  @Override
  public String[] rgbCombine() throws IOException {
    return toolPanel.rgbCombine();
  }

  @Override
  public JPanel getPanel() {
    throw new UnsupportedOperationException("individual panel cannot be extracted");
  }
}
