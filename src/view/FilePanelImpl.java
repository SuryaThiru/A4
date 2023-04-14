package view;

import java.awt.Component;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import controller.Features;

import static view.Identifiers.FILE_PANEL_TITLE;
import static view.Identifiers.OPEN_BUTTON_TITLE;
import static view.Identifiers.REDO_BUTTON_TITLE;
import static view.Identifiers.SAVE_BUTTON_TITLE;
import static view.Identifiers.UNDO_BUTTON_TITLE;

/**
 * This class implements all operations of FilePanel interface.
 */
public class FilePanelImpl extends AbstractOperations implements FilePanel {
  final JButton loadButton;
  final JButton saveButton;
  final JButton undoButton;
  final JButton redoButton;

  /**
   * This constructor initialises the FilePanelImpl object and set up the initial panel for files.
   *
   * @param jPanel          represents the panel for files
   * @param parentComponent represents the parent component or the main panel
   */
  public FilePanelImpl(JPanel jPanel, Component parentComponent) {
    super(parentComponent, jPanel);

    jPanel.setBorder(BorderFactory.createTitledBorder(FILE_PANEL_TITLE));
    jPanel.setVisible(true);
    this.undoButton = new JButton(UNDO_BUTTON_TITLE);
    this.undoButton.setActionCommand(UNDO_BUTTON_TITLE);
    jPanel.add(this.undoButton);

    this.redoButton = new JButton(REDO_BUTTON_TITLE);
    this.redoButton.setActionCommand(REDO_BUTTON_TITLE);
    jPanel.add(this.redoButton);

    this.loadButton = new JButton(OPEN_BUTTON_TITLE);
    this.loadButton.setActionCommand(OPEN_BUTTON_TITLE);
    jPanel.add(this.loadButton);

    this.saveButton = new JButton(SAVE_BUTTON_TITLE);
    this.saveButton.setActionCommand(SAVE_BUTTON_TITLE);
    jPanel.add(this.saveButton);

  }

  @Override
  public String load() throws IOException {
    return loadHelper(true, "Loading File",
            parentComponent);
  }

  @Override
  public String save() throws IOException {
    return loadHelper(false, SAVE_BUTTON_TITLE,
            parentComponent);
  }

  @Override
  public void addFeatures(Features features) {
    loadButton.addActionListener(evt -> {
      features.load();
    });
    saveButton.addActionListener(evt -> {
      features.save();
    });
    undoButton.addActionListener(evt -> {
      features.undo();
    });
    redoButton.addActionListener(evt -> {
      features.redo();
    });
  }
}
