package view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;

/**
 * This view is used to show the GUI of the Application.
 */
public class GUIViewImp extends JFrame implements GUIView {

  final JPanel histPanel;
  final JPanel imagePanel;
  final JButton exposureButton;
  final JButton loadButton;
  final JButton saveButton;
  final JButton filterButton;
  final JButton greyscaleButton;
  final JButton sepiaButton;
  final JButton ditherButton;
  final JButton flipButton;
  final JButton splitButton;
  final JButton combineButton;
  final JButton undoButton;
  final JButton redoButton;

  /**
   * Constructs a GUIViewImpl object and initialises the caption.
   *
   * @param caption the caption to be displayed in the title bar of the GUIViewImpl window.
   */
  public GUIViewImp(String caption) {
    super(caption);

    setSize(1200, 800);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel mainPanel = new JPanel(new BorderLayout());

    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    this.imagePanel = new JPanel();

    this.imagePanel.setBorder(BorderFactory.createTitledBorder("Active Image"));
    this.imagePanel.setVisible(true);
    JScrollPane imageScrollPane = new JScrollPane(imagePanel);
    imageScrollPane.setPreferredSize(new Dimension(400, 400));
    mainPanel.add(imageScrollPane, BorderLayout.CENTER);

    this.histPanel = new JPanel();
    setBackground(Color.BLACK);
    this.histPanel.setPreferredSize(new Dimension(400, 400));
    //setLocation(400, 400);

    this.histPanel.setBorder(BorderFactory.createTitledBorder("Active Histogram"));
    this.histPanel.setLayout(new BorderLayout());
    this.histPanel.setVisible(true);
    JScrollPane histogramScrollPane = new JScrollPane(histPanel);
    histogramScrollPane.setPreferredSize(new Dimension(200, 200));
    mainPanel.add(histogramScrollPane, BorderLayout.LINE_END);

    JPanel toolPanel = new JPanel();
    toolPanel.setBorder(BorderFactory.createTitledBorder("Tools"));
    toolPanel.setVisible(true);
    this.exposureButton = new JButton("Exposure");
    this.exposureButton.setActionCommand("Exposure");
    toolPanel.add(this.exposureButton);

    this.filterButton = new JButton("Filter");
    this.filterButton.setActionCommand("filter");
    toolPanel.add(this.filterButton);

    this.sepiaButton = new JButton("Sepia");
    this.sepiaButton.setActionCommand("Sepia");
    toolPanel.add(this.sepiaButton);

    this.greyscaleButton = new JButton("GrayscaleFunctions");
    this.greyscaleButton.setActionCommand("Enter the function required.");
    toolPanel.add(this.greyscaleButton);

    this.flipButton = new JButton("Flip");
    this.flipButton.setActionCommand("Flip Image");
    toolPanel.add(this.flipButton);

    this.ditherButton = new JButton("Dither");
    this.ditherButton.setActionCommand("Dither");
    toolPanel.add(this.ditherButton);


    this.splitButton = new JButton("Split to RGB Components");
    this.splitButton.setActionCommand("Split RGB");
    toolPanel.add(this.splitButton);

    this.combineButton = new JButton("Combine RGB Components");
    this.combineButton.setActionCommand("Combine");
    toolPanel.add(this.combineButton);

    mainPanel.add(toolPanel, BorderLayout.PAGE_END);

    JPanel filePanel = new JPanel();
    filePanel.setBorder(BorderFactory.createTitledBorder("File"));
    filePanel.setVisible(true);
    this.undoButton = new JButton("← Undo");
    this.undoButton.setActionCommand("Undo");
    filePanel.add(this.undoButton);

    this.redoButton = new JButton("→ Redo");
    this.redoButton.setActionCommand("Redo");
    filePanel.add(this.redoButton);

    this.loadButton = new JButton("Open Image");
    this.loadButton.setActionCommand("Open Image");
    filePanel.add(this.loadButton);

    this.saveButton = new JButton("Save Image");
    this.saveButton.setActionCommand("Save Image");
    filePanel.add(this.saveButton);

    mainPanel.add(filePanel, BorderLayout.PAGE_START);
    pack();
    setVisible(true);

  }

  @Override
  public void addFeatures(Features features) {
    this.exposureButton.addActionListener(evt -> {
      features.brighten();
    });
    this.filterButton.addActionListener(evt -> {
      features.filter();
    });
    this.sepiaButton.addActionListener(evt -> {
      features.sepia();
    });
    this.greyscaleButton.addActionListener(evt -> {
      features.grayscale();
    });
    this.flipButton.addActionListener(evt -> {
      features.flip();
    });
    this.ditherButton.addActionListener(evt -> {
      features.dither();
    });
    this.splitButton.addActionListener(evt -> {
      features.split();
    });
    this.combineButton.addActionListener(evt -> {
      features.combine();
    });
    this.loadButton.addActionListener(evt -> {
      features.load();
    });
    this.saveButton.addActionListener(evt -> {
      features.save();
    });
    this.undoButton.addActionListener(evt -> {
      features.undo();
    });
    this.redoButton.addActionListener(evt -> {
      features.redo();
    });

  }

  @Override
  public String load() {
    return this.loadHelper(true, "Loading File");
  }

  private String loadHelper(Boolean calledByLoad, String dialogTitle) {
    final JFileChooser fileChooser = new JFileChooser(".");
    int value;

    fileChooser.setDialogTitle(dialogTitle);
    if (calledByLoad) {
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "PNG,JPEG,BMP & PPM Images", "jpeg", "png", "ppm", "bmp", "jpg");
      fileChooser.setFileFilter(filter);
      value = fileChooser.showOpenDialog(GUIViewImp.this);
    } else {
      value = fileChooser.showSaveDialog(this);
    }
    if (value == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      return f.getAbsolutePath();
    } else if (value == JFileChooser.CANCEL_OPTION) {
      return null;
    } else {
      this.displayError("Please enter correct Path");
      return null;
    }
  }

  @Override
  public String save() {
    return this.loadHelper(false, "Save");
  }

  @Override
  public int brighten() {
    String brightenValue = JOptionPane.showInputDialog(
            "Enter brighten value?", 0);

    if(brightenValue == null) {
      return 0;
    }

    return Integer.parseInt(brightenValue);
  }

  @Override
  public String filter() {
    String[] options = {"Blur", "Sharpen"};
    String filterValue = (String) JOptionPane.showInputDialog(
            GUIViewImp.this, "Select Filter type",
            "Filter type", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

    if(filterValue == null) {
      return null;
    }

    return filterValue.toLowerCase();
  }


  @Override
  public String flip() {

    String[] options = {"Horizontal-Flip", "Vertical-Flip"};
    String flipValue = (String) JOptionPane.showInputDialog(
            GUIViewImp.this, "Select flip type",
            "Flip type", JOptionPane.QUESTION_MESSAGE, null, options,
            options[0]);
    return flipValue;
  }

  @Override
  public String split() {
    String[] options = {"red", "green", "blue"};
    String split = (String) JOptionPane.showInputDialog(
            GUIViewImp.this, "Select the component you require",
            "RGB Split", JOptionPane.QUESTION_MESSAGE, null, options,
            options[0]);
    return split;
  }

  @Override
  public String greyscaleFunction() {

    String[] options = {"Luma", "Value", "Intensity", "Red", "Green", "Blue"};
    String greyscaleValue = (String) JOptionPane.showInputDialog(
            GUIViewImp.this, "Select Greyscale type",
            "Greyscale type", JOptionPane.QUESTION_MESSAGE, null, options,
            options[2]);
    return greyscaleValue;
  }

  @Override
  public String[] rgbCombine() {
    String[] imagePaths = new String[3];
    imagePaths[0] = this.loadHelper(true, "Load GreyScale Red Image");
    imagePaths[1] = this.loadHelper(true, "Load GreyScale Green Image");
    imagePaths[2] = this.loadHelper(true, "Load GreyScale Blue Image");
    return imagePaths;
  }

  @Override
  public void displayImage(int[][] histogram, BufferedImage image) {
    this.imagePanel.removeAll();
    if (image != null) {
      JLabel picLabel = new JLabel(new ImageIcon(image));
      this.imagePanel.add(picLabel, BorderLayout.CENTER);
    }
    imagePanel.revalidate();
    imagePanel.repaint();
    displayHistogram(histogram);
    pack();
  }

  /**
   * This function is used to display the histogram after every step.
   *
   * @param histogram represents the histogram array
   */
  void displayHistogram(int[][] histogram) {
    this.histPanel.removeAll();
    JPanel chartPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x1;
        int x2;
        int y1;
        int y2;

        Graphics2D g2d = (Graphics2D) g;
        int width = histPanel.getWidth();
        int height = histPanel.getHeight();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA};

        for (int i = 0; i < histogram.length; i++) {

          g2d.setColor(colors[i]);
          int max = findMaxValue(histogram[i]);
          double scaleFactor = (double) height / max;

          for (int j = 1; j < histogram[i].length - 1; j++) {

            x1 = (int) (((double) width / (histogram[i].length - 1)) * (j - 1));
            y1 = height - (int) (histogram[i][j - 1] * scaleFactor) - 30;
            x2 = (int) (((double) width / (histogram[i].length - 1)) * j);
            y2 = height - (int) (histogram[i][j] * scaleFactor) - 30;

            g2d.drawLine(x1, y1, x2, y2);
          }
        }
      }
    };

    histPanel.add(chartPanel);
    histPanel.revalidate();
    histPanel.repaint();
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

  @Override
  public void displayError(String errorMessage) {
    JOptionPane.showMessageDialog(this, errorMessage,
            "Error", JOptionPane.ERROR_MESSAGE);
  }

}
