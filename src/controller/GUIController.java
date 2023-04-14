package controller;

import java.io.IOException;
import java.util.Arrays;

import view.GUIView;

import static helper.ImageUtil.getFileName;

/**
 * This class represents the GUI controller and determines the steps after each button input.
 */
public class GUIController implements Features {

  String[] images;

  int index;
  final ImageController imageControllerImp;
  final GUIView view;

  /**
   * The constructor is used to initialize the GUIController class variables.
   *
   * @param imageControllerImp represents the ImageController
   * @param view               represents the GUIView
   */
  public GUIController(ImageController imageControllerImp, GUIView view) {
    this.index = 0;
    this.images = new String[10];
    this.imageControllerImp = imageControllerImp;
    this.view = view;
    view.addFeatures(this);
  }

  /**
   * This method is used to update the image name.
   *
   * @param updatedImageName represents the updated image name
   */
  void updateImage(String updatedImageName) {
    try {
      if (index == 9) {
        images = Arrays.copyOfRange(images, 1, images.length);
      }
      images[index++] = updatedImageName;
      view.displayImage(imageControllerImp.calculateHistogram(updatedImageName),
              imageControllerImp.updatedImage(updatedImageName));
    } catch (IOException ex) {
      view.displayError(ex.getMessage());
    } catch (UnsupportedOperationException ex) {
      view.displayError(ex.getMessage());
    }
  }

  @Override
  public void load() {
    load(view.load());
  }

  String load(String imagePath) {
    try {
      String imageName = getFileName(imagePath);
      imageControllerImp.load(imagePath, imageName);
      updateImage(imageName);
      return imageName;
    } catch (Exception ex) {
      view.displayError(ex.getMessage());
    }
    return null;
  }

  @Override
  public void save() {
    try {
      if (images[0] == null) {
        throw new IOException("save is not possible without an image");
      }
      String imagePath = view.save();
      imageControllerImp.save(imagePath, images[index - 1]);
      updateImage(images[index - 1]);
    } catch (IOException ex) {
      view.displayError(ex.getMessage());
    } catch (UnsupportedOperationException ex) {
      view.displayError(ex.getMessage());
    }
  }

  @Override
  public void brighten() {
    try {
      if (images[0] == null) {
        throw new IOException("image operations are not possible without an image");
      }
      String updatedImageName = images[index - 1] + 'b';
      int brighten = view.brighten();
      if(brighten == 0) {
        return;
      }
      imageControllerImp.brighten(brighten, images[index - 1], updatedImageName);
      updateImage(updatedImageName);
    } catch (IOException ex) {
      view.displayError(ex.getMessage());
    } catch (UnsupportedOperationException ex) {
      view.displayError(ex.getMessage());
    }
  }

  @Override
  public void filter() {
    try {
      if (images[0] == null) {
        throw new IOException("image operations are not possible without an image");
      }
      String filter = view.filter();
      if (filter == null) {
        return;
      }
      String updatedImageName = images[index - 1] + filter;
      imageControllerImp.filter(images[index - 1], updatedImageName, filter);
      updateImage(updatedImageName);
    } catch (IOException ex) {
      view.displayError(ex.getMessage());
    } catch (UnsupportedOperationException ex) {
      view.displayError(ex.getMessage());
    }
  }

  @Override
  public void grayscale() {
    try {
      if (images[0] == null) {
        throw new IOException("image operations are not possible without an image");
      }
      String conversionType = view.greyscaleFunction();
      if(conversionType == null) {
        return;
      }
      String updatedImageName = images[index - 1] + conversionType;
      switch (conversionType) {
        case "Value":
          imageControllerImp.combineByValue(images[index - 1], updatedImageName);
          break;
        case "Luma":
          imageControllerImp.combineByLuma(images[index - 1], updatedImageName);
          break;
        case "Intensity":
          imageControllerImp.combineByIntensity(images[index], updatedImageName);
          break;
        case "Red":
          imageControllerImp.combineByComponent(0, images[index - 1], updatedImageName);
          break;
        case "Green":
          imageControllerImp.combineByComponent(1, images[index - 1], updatedImageName);
          break;
        case "Blue":
          imageControllerImp.combineByComponent(2, images[index - 1], updatedImageName);
          break;
        default:
          throw new IOException("Unsupported operation");
      }
      updateImage(updatedImageName);
    } catch (IOException ex) {
      view.displayError(ex.getMessage());
    } catch (UnsupportedOperationException ex) {
      view.displayError(ex.getMessage());
    }
  }

  @Override
  public void flip() {
    try {
      if (images[0] == null) {
        throw new IOException("image operations are not possible without an image");
      }
      String updatedImageName;
      String flip = view.flip();
      if(flip == null) {
        return;
      }
      if (flip.equals("Horizontal-Flip")) {
        updatedImageName = images[index - 1] + 'h';
        imageControllerImp.flipHorizontal(images[index - 1], updatedImageName);
      } else {
        updatedImageName = images[index - 1] + 'v';
        imageControllerImp.flipVertical(images[index - 1], updatedImageName);
      }
      updateImage(updatedImageName);
    } catch (IOException ex) {
      view.displayError(ex.getMessage());
    } catch (UnsupportedOperationException ex) {
      view.displayError(ex.getMessage());
    }
  }

  @Override
  public void dither() {
    try {
      if (images[0] == null) {
        throw new IOException("image operations are not possible without an image");
      }
      String updatedImageName = images[index - 1] + 'd';
      imageControllerImp.dither(images[index - 1], updatedImageName);
      updateImage(updatedImageName);
    } catch (IOException ex) {
      view.displayError(ex.getMessage());
    } catch (UnsupportedOperationException ex) {
      view.displayError(ex.getMessage());
    }
  }

  @Override
  public void sepia() {
    try {
      if (images[0] == null) {
        throw new IOException("image operations are not possible without an image");
      }
      String updatedImageName = images[index - 1] + 's';
      imageControllerImp.combineBySepia(images[index - 1], updatedImageName);
      updateImage(updatedImageName);
    } catch (IOException ex) {
      view.displayError(ex.getMessage());
    } catch (UnsupportedOperationException ex) {
      view.displayError(ex.getMessage());
    }
  }

  @Override
  public void combine() {
    try {
      if (images[0] == null) {
        throw new IOException("image operations are not possible without an image");
      }
      String[] imageNames = new String[3];
      String[] imagePaths = view.rgbCombine();
      if (imagePaths == null || imagePaths[0] == null) {
        throw new IOException("Combine of RGB aborted");
      }
      for (int i = 0; i < imagePaths.length; i++) {
        imageNames[i] = load(imagePaths[i]);
      }
      String updatedImageName = images[index - 1] + "rgb-c";
      imageControllerImp.combine(updatedImageName, imageNames[0], imageNames[1], imageNames[2]);
      updateImage(updatedImageName);
    } catch (IOException ex) {
      view.displayError(ex.getMessage());
    } catch (UnsupportedOperationException ex) {
      view.displayError(ex.getMessage());
    }
  }

  @Override
  public void split() {
    try {
      if (images[0] == null) {
        throw new IOException("image operations are not possible without an image");
      }
      String split = view.split();
      if(split == null) {
        return;
      }
      imageControllerImp.split(images[index - 1], images[index - 1] + "-red",
              images[index - 1] + "-green", images[index - 1] + "-blue");
      updateImage(images[index - 1] + '-' + split);
    } catch (IOException ex) {
      view.displayError(ex.getMessage());
    } catch (UnsupportedOperationException ex) {
      view.displayError(ex.getMessage());
    }
  }

  @Override
  public void undo() {
    try {
      if (images[0] == null) {
        throw new IOException("image operations are not possible without an image");
      }
      if (images == null || index - 2 < 0) {
        throw new IOException("Undo is not possible");
      }
      view.displayImage(imageControllerImp.calculateHistogram(images[index - 2]),
              imageControllerImp.updatedImage(images[index - 2]));
      index--;
    } catch (IOException ex) {
      view.displayError(ex.getMessage());
    } catch (UnsupportedOperationException ex) {
      view.displayError(ex.getMessage());
    }
  }

  @Override
  public void redo() {
    try {
      if (images[0] == null) {
        throw new IOException("image operations are not possible without an image");
      }
      if (images == null || index == 0 || index >= images.length - 1) {
        throw new IOException("Redo is not possible");
      }
      view.displayImage(imageControllerImp.calculateHistogram(images[index]),
              imageControllerImp.updatedImage(images[index]));
      index++;
    } catch (IOException ex) {
      view.displayError(ex.getMessage());
    } catch (UnsupportedOperationException ex) {
      view.displayError(ex.getMessage());
    }
  }
}
