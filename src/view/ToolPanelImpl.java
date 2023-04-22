package view;

import java.awt.Component;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import controller.Features;

import static view.Identifiers.ALLOWED_FILTERS;
import static view.Identifiers.ALLOWED_FLIPS;
import static view.Identifiers.ALLOWED_GRAYSCALE;
import static view.Identifiers.ALLOWED_SPLITS;
import static view.Identifiers.COMBINE_BUTTON_TITLE;
import static view.Identifiers.COMPONENTS;
import static view.Identifiers.DITHER_BUTTON_TITLE;
import static view.Identifiers.EXPOSURE_BUTTON_TITLE;
import static view.Identifiers.FILTER_BUTTON_TITLE;
import static view.Identifiers.FLIP_BUTTON_TITLE;
import static view.Identifiers.GRAYSCALE_BUTTON_TITLE;
import static view.Identifiers.MOSAIC_BUTTON_TITLE;
import static view.Identifiers.SEPIA_BUTTON_TITLE;
import static view.Identifiers.SPLIT_BUTTON_TITLE;
import static view.Identifiers.TOOL_PANEL_TITLE;

/**
 * This class implements all operations of ToolPanel interface.
 */
public class ToolPanelImpl extends AbstractOperations implements ToolPanel {
  final JButton exposureButton;
  final JButton filterButton;
  final JButton greyscaleButton;
  final JButton sepiaButton;
  final JButton ditherButton;
  final JButton flipButton;
  final JButton splitButton;
  final JButton combineButton;
  final JButton mosaicButton;

  /**
   * This constructor initialises the ToolPanelImpl object and set up the initial panel for tools.
   *
   * @param jPanel          represents the panel for tools
   * @param parentComponent represents the parent component or the main panel
   */
  public ToolPanelImpl(JPanel jPanel, Component parentComponent) {
    super(parentComponent, jPanel);

    jPanel.setBorder(BorderFactory.createTitledBorder(TOOL_PANEL_TITLE));
    jPanel.setVisible(true);

    this.exposureButton = new JButton(EXPOSURE_BUTTON_TITLE);
    this.exposureButton.setActionCommand(EXPOSURE_BUTTON_TITLE);
    jPanel.add(this.exposureButton);

    this.filterButton = new JButton(FILTER_BUTTON_TITLE);
    this.filterButton.setActionCommand(FILTER_BUTTON_TITLE);
    jPanel.add(this.filterButton);

    this.sepiaButton = new JButton(SEPIA_BUTTON_TITLE);
    this.sepiaButton.setActionCommand(SEPIA_BUTTON_TITLE);
    jPanel.add(this.sepiaButton);

    this.greyscaleButton = new JButton(GRAYSCALE_BUTTON_TITLE);
    this.greyscaleButton.setActionCommand(GRAYSCALE_BUTTON_TITLE);
    jPanel.add(this.greyscaleButton);

    this.flipButton = new JButton(FLIP_BUTTON_TITLE);
    this.flipButton.setActionCommand(FLIP_BUTTON_TITLE);
    jPanel.add(this.flipButton);

    this.ditherButton = new JButton(DITHER_BUTTON_TITLE);
    this.ditherButton.setActionCommand(DITHER_BUTTON_TITLE);
    jPanel.add(this.ditherButton);


    this.splitButton = new JButton(SPLIT_BUTTON_TITLE);
    this.splitButton.setActionCommand(SPLIT_BUTTON_TITLE);
    jPanel.add(this.splitButton);

    this.combineButton = new JButton(COMBINE_BUTTON_TITLE);
    this.combineButton.setActionCommand(COMBINE_BUTTON_TITLE);
    jPanel.add(this.combineButton);

    this.mosaicButton = new JButton(MOSAIC_BUTTON_TITLE);
    this.mosaicButton.setActionCommand(MOSAIC_BUTTON_TITLE);
    jPanel.add(this.mosaicButton);

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
    this.mosaicButton.addActionListener(evt -> {
      features.mosaick();
    });
  }

  @Override
  public int brighten() {
    String brightenValue = JOptionPane.showInputDialog(
            "Enter brighten value?", 0);

    if (brightenValue == null) {
      return 0;
    }

    return Integer.parseInt(brightenValue);
  }

  @Override
  public String filter() {
    String filterValue = (String) JOptionPane.showInputDialog(
            parentComponent, "Select Filter type",
            "Filter type", JOptionPane.QUESTION_MESSAGE, null,
            ALLOWED_FILTERS, ALLOWED_FILTERS[0]);

    if (filterValue == null) {
      return null;
    }

    return filterValue.toLowerCase();
  }


  @Override
  public String flip() {
    return (String) JOptionPane.showInputDialog(
            parentComponent, "Select flip type",
            "Flip type", JOptionPane.QUESTION_MESSAGE, null, ALLOWED_FLIPS,
            ALLOWED_FLIPS[0]);
  }

  @Override
  public String split() {
    return (String) JOptionPane.showInputDialog(
            parentComponent, "Select the component you require",
            "RGB Split", JOptionPane.QUESTION_MESSAGE, null, ALLOWED_SPLITS,
            ALLOWED_SPLITS[0]);
  }

  @Override
  public String greyscaleFunction() {
    return (String) JOptionPane.showInputDialog(
            parentComponent, "Select Greyscale type",
            "Greyscale type", JOptionPane.QUESTION_MESSAGE, null, ALLOWED_GRAYSCALE,
            ALLOWED_GRAYSCALE[2]);
  }

  @Override
  public String[] rgbCombine() throws IOException {
    String[] imagePaths = new String[COMPONENTS.length];
    for (int i = 0; i < imagePaths.length; i++) {
      imagePaths[i] = loadHelper(true, "Load GreyScale "
              + COMPONENTS[i] + " Image", parentComponent);

      if (imagePaths[i] == null) {
        return null;
      }
    }
    return imagePaths;
  }

  @Override
  public int mosaick() {
    String seedValue = JOptionPane.showInputDialog(
        "Enter number of seeds", 0);

    if (seedValue == null) {
      return 0;
    }

    return Integer.parseInt(seedValue);
  }
}
