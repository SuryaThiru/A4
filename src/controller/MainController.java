package controller;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

import model.Image;
import model.RGBImage;

class MainController {
  final Readable in;
  final Appendable out;

  MainController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  public void go(Image image) throws IOException {
    Objects.requireNonNull(image);
    String imagePath, imageName;
    Scanner scan = new Scanner(this.in);
    while (true) {
      switch (scan.next()) {
        case "load":
          imagePath = scan.next();
          imageName = scan.next();
          ImageControllerImp imageControllerImp = new ImageControllerImp(image);
          imageControllerImp.load(imagePath, imageName);
          break;
        case "q":
          return;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    Image image = new RGBImage(0, 0, 0);
    try {
      new MainController(new InputStreamReader(System.in), System.out).go(image);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
