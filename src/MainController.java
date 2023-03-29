import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import controller.Command;
import controller.ImageController;
import controller.ImageControllerImp;
import controller.commands.Compare;
import controller.commands.Dither;
import controller.commands.Exposure;
import controller.commands.FlipImage;
import controller.commands.GrayscaleFunctions;
import controller.commands.LoadCommand;
import controller.commands.RgbFunctions;
import controller.commands.SaveCommand;
import controller.commands.SepiaTone;
import model.Image;
import model.RGBImage;
import view.ImageView;
import view.TextView;

/**
 * This class represents the main controller and determines the steps after each input.
 */
public class MainController {
  final Readable in;
  final Appendable out;

  /**
   * The constructor is used to initialize the class variables.
   *
   * @param in  represents the input stream.
   * @param out represents the output stream.
   */
  MainController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  /**
   * This method is used as the main method which determines all the steps to be performed.
   *
   * @param imageControllerImp represents the image controller.
   * @param image              represents an image.
   * @throws IOException throws exception when inout is not valid.
   */
  public void startProgram(ImageController imageControllerImp, Image image, ImageView view)
          throws IOException {
    Objects.requireNonNull(image);
    Scanner scan = new Scanner(this.in);
    while (menuScript(scan, image, imageControllerImp, view)) {
      ;
    }
  }

//  private boolean menuScript(Scanner scan, ImageController imageControllerImp, Image image)
//          throws IOException, NoSuchElementException {
//    String t = scan.next();
//
//    switch (t) {
//      case "load":
//      case "save":
//        String imagePath = scan.next();
//        String imageName = scan.next();
//        if (t.equals("load")) {
//          imageControllerImp.load(imagePath, imageName);
//          this.out.append(String.format("loaded %s successfully\n", imageName));
//          break;
//        }
//        imageControllerImp.save(imagePath, imageName);
//        this.out.append(String.format("saved %s successfully\n", imageName));
//        break;
//      case "vertical-flip":
//      case "horizontal-flip":
//        imageName = scan.next();
//        String updatedImageName = scan.next();
//        if (t.equals("vertical-flip")) {
//          imageControllerImp.flipVertical(imageName, updatedImageName);
//          this.out.append(String.format("flipped %s to %s vertically successfully\n", imageName,
//                  updatedImageName));
//          break;
//        }
//        imageControllerImp.flipHorizontal(imageName, updatedImageName);
//        this.out.append(String.format("flipped %s to %s horizontally successfully\n", imageName,
//                updatedImageName));
//        break;
//      case "brighten":
//      case "darken":
//        int value = scan.nextInt();
//        imageName = scan.next();
//        updatedImageName = scan.next();
//        if (t.equals("brighten")) {
//          imageControllerImp.brighten(value, imageName, updatedImageName);
//          this.out.append(String.format("increased the brightness of %s by %d to %s "
//                  + "successfully\n", imageName, value, updatedImageName));
//          break;
//        }
//        imageControllerImp.darken(value, imageName, updatedImageName);
//        this.out.append(String.format("decreased the brightness of %s by %d to %s "
//                + "successfully\n", imageName, value, updatedImageName));
//        break;
//      case "rgb-split":
//      case "rgb-combine":
//        imageName = scan.next();
//        String redImageName = scan.next();
//        String greenImageName = scan.next();
//        String blueImageName = scan.next();
//        if (t.equals("rgb-split")) {
//          imageControllerImp.split(imageName, redImageName, greenImageName, blueImageName);
//          this.out.append(String.format("split %s to red: %s green: %s blue: %s "
//                  + "successfully\n", imageName, redImageName, greenImageName, blueImageName));
//          break;
//        }
//        imageControllerImp.combine(imageName, redImageName, greenImageName, blueImageName);
//        this.out.append(String.format("combined red: %s green: %s blue: %s to %s"
//                + "successfully\n", redImageName, greenImageName, blueImageName, imageName));
//        break;
//      case "compare":
//        imageName = scan.next();
//        updatedImageName = scan.next();
//        boolean f = imageControllerImp.compareImages(imageName, updatedImageName);
//        if (!f) {
//          this.out.append(String.format("%s is not the same as %s\n", imageName,
//                  updatedImageName));
//          break;
//        }
//        this.out.append(String.format("%s is the same as %s\n", imageName, updatedImageName));
//        break;
//      case "dither":
//        imageName = scan.next();
//        updatedImageName = scan.next();
//
//        imageControllerImp.dither(imageName, updatedImageName);
//        this.out.append(String.format("dither conversion of %s to %s is successful\n",
//                imageName, updatedImageName));
//        break;
//      case "greyscale":
//        String conversionType = scan.next();
//        imageName = scan.next();
//        updatedImageName = scan.next();
//        switch (conversionType) {
//          case "value-component":
//            imageControllerImp.combineByValue(imageName, updatedImageName);
//            this.out.append(String.format("greyscale split of %s by %s to %s is successful\n",
//                    imageName, conversionType, updatedImageName));
//            break;
//          case "luma-component":
//            imageControllerImp.combineByLuma(imageName, updatedImageName);
//            this.out.append(String.format("greyscale split of %s by %s to %s is successful\n",
//                    imageName, conversionType, updatedImageName));
//            break;
//          case "intensity-component":
//            imageControllerImp.combineByIntensity(imageName, updatedImageName);
//            this.out.append(String.format("greyscale split of %s by %s to %s is successful\n",
//                    imageName, conversionType, updatedImageName));
//            break;
//          case "red-component":
//            imageControllerImp.combineByComponent(0, imageName, updatedImageName);
//            this.out.append(String.format("greyscale split of %s by %s to %s is successful\n",
//                    imageName, conversionType, updatedImageName));
//            break;
//          case "green-component":
//            imageControllerImp.combineByComponent(1, imageName, updatedImageName);
//            this.out.append(String.format("greyscale split of %s by %s to %s is successful\n",
//                    imageName, conversionType, updatedImageName));
//            break;
//          case "blue-component":
//            imageControllerImp.combineByComponent(2, imageName, updatedImageName);
//            this.out.append(String.format("greyscale split of %s by %s to %s is successful\n",
//                    imageName, conversionType, updatedImageName));
//            break;
//          case "sepia-component":
//            imageControllerImp.combineBySepia(imageName, updatedImageName);
//            this.out.append(String.format("converting %s to a sepia-toned "
//                            + "Image - %s is successful\n",
//                    imageName, updatedImageName));
//            break;
//
//          default:
//            break;
//        }
//        break;
//      case "run":
//        String scriptFilePath = scan.next();
//        int lastDotIndex = scriptFilePath.lastIndexOf(".");
//        if (lastDotIndex > 0 && !scriptFilePath.substring(lastDotIndex + 1)
//                .equals("txt")) {
//          throw new IOException("invalid script being used. only txt files are allowed. "
//                  + "Try again\n");
//        }
//        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {
//          String line;
//          while ((line = reader.readLine()) != null) {
//            menuScript(new Scanner(line), imageControllerImp, image);
//          }
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//        break;
//      case "q":
//        return false;
//      default:
//        System.out.println("invalid selection. select again");
//        startProgram(imageControllerImp, image);
//    }
//    return true;
//  }

  /**
   * This is the main function form which the program starts.
   *
   * @param args represents various arguments from the command line.
   */
  public static void main(String[] args) {

    Image imageModel = new RGBImage(0, 0, 0);
    ImageView view = new TextView(System.out);
    ImageController imageController = new ImageControllerImp(imageModel);

    try {
      new MainController(new InputStreamReader(System.in), System.out)
              .startProgram(imageController, imageModel, view);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

//----------------------------------------------------------------------------------------------

  private boolean menuScript(Scanner scan, Image image, ImageController imageControllerImp, ImageView view)
          throws IOException, NoSuchElementException {
    String t = scan.next();
    Command command = null;
    switch (t) {
      case "load":
        command = new LoadCommand(scan, imageControllerImp, view);
        break;
      case "save":
        command = new SaveCommand(scan, imageControllerImp, view);
        break;
      case "vertical-flip":
        command = new FlipImage(scan, imageControllerImp, view, "v");
        break;
      case "horizontal-flip":
        command = new FlipImage(scan, imageControllerImp, view, "h");
        break;
      case "brighten":
        command = new Exposure(scan, imageControllerImp, view, "b");
        break;
      case "darken":
        command = new Exposure(scan, imageControllerImp, view, "d");
        break;
      case "rgb-split":
        command = new RgbFunctions(scan, imageControllerImp, view, "split");
        break;
      case "rgb-combine":
        command = new RgbFunctions(scan, imageControllerImp, view, "combine");
        break;
      case "compare":
        command = new Compare(scan, imageControllerImp, view);
        break;
      case "greyscale":
        command = new GrayscaleFunctions(scan, imageControllerImp, view);
        break;
      case "sepia-tone":
        command = new SepiaTone(scan, imageControllerImp, view);
        break;
      case "dither":
        command = new Dither(scan, imageControllerImp, view);
        break;
      case "run":
        String scriptFilePath = scan.next();
        int lastDotIndex = scriptFilePath.lastIndexOf(".");
        if (lastDotIndex > 0 && !scriptFilePath.substring(lastDotIndex + 1)
                .equals("txt")) {
          throw new IOException("invalid script being used. only txt files are allowed. "
                  + "Try again\n");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {
          String line;
          while ((line = reader.readLine()) != null) {
            menuScript(new Scanner(line), image, imageControllerImp, view);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case "q":
        return false;
      default:
        //        throw new IllegalArgumentException("Invalid command: " + t);
        startProgram(imageControllerImp, image, view);

    }

    command.execute();

    return true;
  }

}


