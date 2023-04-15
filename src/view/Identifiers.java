package view;

/**
 * This class contains all identifiers/constants for the view gui.
 */
public class Identifiers {
  static final String[] COMPONENTS = {"RED", "GREEN", "BLUE"};
  static final String[] ALLOWED_EXTENSIONS = {"jpeg", "png", "ppm", "bmp", "jpg"};
  static final int VERTICAL_SCALE = 26;
  static final int SCROLLPANE_DIMENSION_H = 200;
  static final int SCROLLPANE_DIMENSION_W = 200;
  static final int PANEL_DIMENSION_H = 400;
  static final int PANEL_DIMENSION_W = 400;
  static final int WINDOW_DIMENSION_H = 800;
  static final int WINDOW_DIMENSION_W = 1200;
  static final String IMAGE_TITLE = "Active Image";
  static final String HISTOGRAM_TITLE = "Active Histogram";
  static final String TOOL_PANEL_TITLE = "Tools";
  static final String FILE_PANEL_TITLE = "File";
  static final String EXPOSURE_BUTTON_TITLE = "Exposure";
  static final String FILTER_BUTTON_TITLE = "Filter";
  static final String[] ALLOWED_FILTERS = {"Blur", "Sharpen"};
  static final String SEPIA_BUTTON_TITLE = "Sepia";
  static final String GRAYSCALE_BUTTON_TITLE = "GrayscaleFunctions";
  static final String[] ALLOWED_GRAYSCALE = {"Luma", "Value", "Intensity", "Red", "Green", "Blue"};
  static final String FLIP_BUTTON_TITLE = "Flip";
  static final String[] ALLOWED_FLIPS = {"Horizontal-Flip", "Vertical-Flip"};
  static final String DITHER_BUTTON_TITLE = "Dither";
  static final String SPLIT_BUTTON_TITLE = "Split to RGB Components";
  static final String[] ALLOWED_SPLITS = {"red", "green", "blue"};
  static final String COMBINE_BUTTON_TITLE = "Combine RGB Components";
  static final String UNDO_BUTTON_TITLE = "← Undo";
  static final String REDO_BUTTON_TITLE = "→ Redo";
  static final String OPEN_BUTTON_TITLE = "Open Image";
  static final String SAVE_BUTTON_TITLE = "Save Image";
}
