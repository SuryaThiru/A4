import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

import controller.ImageController;
import controller.ImageControllerImp;
import model.Image;
import model.RGBImage;

public class MainController {
  final Readable in;
  final Appendable out;

  MainController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  public void go(ImageController imageControllerImp, Image image) throws IOException {
    Objects.requireNonNull(image);
    Scanner scan = new Scanner(this.in);
    while (true) {
      menuScript(scan, imageControllerImp, image);
    }
  }

  private void menuScript(Scanner scan, ImageController imageControllerImp, Image image)
          throws IOException {
    String t = scan. next();

    switch (t) {
      case "load":
      case "save":
        String imagePath = scan.next();
        String imageName = scan.next();
        if (t.equals("load")) {
          imageControllerImp.load(imagePath, imageName);
          this.out.append(String.format("loaded %s successfully\n", imageName));
          break;
        }
        imageControllerImp.save(imagePath, imageName);
        this.out.append(String.format("saved %s successfully\n", imageName));
        break;
      case "vertical-flip":
      case "horizontal-flip":
        imageName = scan.next();
        String updatedImageName = scan.next();
        if (t == "vertical-flip") {
          imageControllerImp.flipVertical(imageName, updatedImageName);
          this.out.append(String.format("flipped %s to %s vertically successfully\n", imageName,
                  updatedImageName));
          break;
        }
        imageControllerImp.flipHorizontal(imageName, updatedImageName);
        this.out.append(String.format("flipped %s to %s horizontally successfully\n", imageName,
                updatedImageName));
        break;
      case "brighten":
      case "darken":
        int value = scan.nextInt();
        imageName = scan.next();
        updatedImageName = scan.next();
        if (t == "brighten") {
          imageControllerImp.brighten(value, imageName, updatedImageName);
          this.out.append(String.format("increased the brightness of %s by %d to %s "
                  + "successfully\n", imageName, value, updatedImageName));
          break;
        }
        imageControllerImp.darken(value, imageName, updatedImageName);
        this.out.append(String.format("decreased the brightness of %s by %d to %s "
                + "successfully\n", imageName, value, updatedImageName));
        break;
      case "rgb-split":
      case "rgb-combine":
        imageName = scan.next();
        String redImageName = scan.next();
        String greenImageName = scan.next();
        String blueImageName = scan.next();
        if (t == "rgb-split") {
          imageControllerImp.split(imageName, redImageName, greenImageName, blueImageName);
          this.out.append(String.format("split %s to red: %s green: %s blue: %s "
                  + "successfully\n", imageName, redImageName, greenImageName, blueImageName));
          break;
        }
        imageControllerImp.combine(imageName, redImageName, greenImageName, blueImageName);
        this.out.append(String.format("combined red: %s green: %s blue: %s to %s"
                + "successfully\n", redImageName, greenImageName, blueImageName, imageName));
        break;
      case "compare":
        imageName = scan.next();
        updatedImageName = scan.next();
        boolean f = imageControllerImp.compareImages(imageName, updatedImageName);
        if (!f) {
          this.out.append(String.format("%s is not the same as %s\n", imageName,
                  updatedImageName));
          break;
        }
        this.out.append(String.format("%s is the same as %s\n", imageName, updatedImageName));
        break;
      case "greyscale":
        String conversionType = scan.next();
        imageName = scan.next();
        updatedImageName = scan.next();
        switch (conversionType) {
          case "value-component":
            imageControllerImp.combineByValue(imageName, updatedImageName);
            this.out.append(String.format("greyscale split of %s by %s to %s is successful\n",
                    imageName, conversionType, updatedImageName));
            break;
          case "luma-component":
            imageControllerImp.combineByLuma(imageName, updatedImageName);
            this.out.append(String.format("greyscale split of %s by %s to %s is successful\n",
                    imageName, conversionType, updatedImageName));
            break;
          case "intensity-component":
            imageControllerImp.combineByIntensity(imageName, updatedImageName);
            this.out.append(String.format("greyscale split of %s by %s to %s is successful\n",
                    imageName, conversionType, updatedImageName));
            break;
          case "red-component":
            imageControllerImp.combineByComponent(0, imageName, updatedImageName);
            this.out.append(String.format("greyscale split of %s by %s to %s is successful\n",
                    imageName, conversionType, updatedImageName));
            break;
          case "green-component":
            imageControllerImp.combineByComponent(1, imageName, updatedImageName);
            this.out.append(String.format("greyscale split of %s by %s to %s is successful\n",
                    imageName, conversionType, updatedImageName));
            break;
          case "blue-component":
            imageControllerImp.combineByComponent(2, imageName, updatedImageName);
            this.out.append(String.format("greyscale split of %s by %s to %s is successful\n",
                    imageName, conversionType, updatedImageName));
            break;
        }
        break;
      case "run":
        String scriptFilePath = scan.next();
        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {
          String line;
          while ((line = reader.readLine()) != null) {
            menuScript(new Scanner(line), imageControllerImp, image);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case "q":
        return;
      default:
        System.out.println("invalid selection. select again");
        go(imageControllerImp, image);
    }
  }

  public static void main(String[] args) {
    Image imageModel = new RGBImage(0, 0, 0);
    ImageController imageController = new ImageControllerImp(imageModel);
    try {
      new MainController(new InputStreamReader(System.in), System.out)
              .go(imageController, imageModel);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}