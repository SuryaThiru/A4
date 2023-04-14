# Image Manipulation and Enhancement using Java

## Application Architecture

- `Image.java` — Represents a model in our MVC architecture.
- `ImageController.java` — Represents a controller in our MVC architecture.
- `ImageView.java` — Represents a view for the text output in our MVC architecture.
- `GUIView.java` — Represents a view for the GUi in our MVC architecture.
- `AbstractImage.java` implements the model and is extended by `RGBImage`
  and `GrayscaleImage`. `RGBImage` represents an RGB (colored) image and `GrayscaleImage` represents
  a grayscale image (black & white) of our Image implementation. Image is split into these classes
  to future-proof the application if the need arises to add different method to the respective image
  types.
- `CommandController.java` is where the program starts and the controller manages the application.
  This
  application is used to perform image manipulation techniques on an image using command line or a
  script file.

It can perform the following tasks :

1. Load a PPM Image.
2. Brighten that image.
3. Darken that image.
4. Flip the image horizontally.
5. Flip the image vertically.
6. Split the images into RGB channels.
7. Combine the RGB channels to form an RGB image.
8. Create a greyscale image using the value, intensity or luma values.
9. Creating a greyscale image using either R, G or B channels.
10. Save the image.

# By Command Line

### load flower.ppm and call it 'flower'

```
load res/images/flower.ppm flower
```

### brighten flower by adding 10

```
brighten 10 flower flower-brighter
```

### flip flower vertically

```
vertical-flip flower flower-vertical
```

### flip the vertically flipped flower horizontally

```
horizontal-flip flower-vertical flower-vertical-horizontal
```

### create a greyscale using only the value component, as an image flower-greyscale

```
greyscale value-component flower flower-greyscale
```

### save flower-brighter

```
save res/images/flower-brighter.ppm flower-brighter
```

### save flower-greyscale

```
save res/images/flower-gs.ppm flower-greyscale
```

### overwrite flower with another file

```
load res/images/flower-brighter.ppm flower
```

### give the flower a red tint

```
rgb-split flower flower-red flower-green flower-blue
```

### brighten just the red image

```
brighten 50 flower-red flower-red
```

### creating a greyscale image using red channel

```
greyscale red-component flower-red flower-greyscale
```

### combine them back, but by using the brightened red we get a red tint

```
rgb-combine flower-red-tint flower-red flower-green flower-blue
save res/images/flower-red-tint.ppm flower-red-tint
```

### exit / close the program

```
q
```

# By Script File

```
run res/scripts/testScript1.txt
```

### Example of a script file -

```
   load images/flower.ppm fractal
   brighten 10 fractal fractal-brighter
   vertical-flip fractal-brighter fractal-brighter-vertical
   save images/fractal-brighter-vertical.ppm fractal-brighter-vertical
   q
```

## New Features added :

### - load a png Image

`load res/images/flower.png flower-png`

### - load a jpeg Image

`load res/images/flower.jpeg flower-jpeg`

### - load a bmp Image

`load res/images/flower.bmp flower-bmp`

### - sepia-tone an Image

`sepia-tone fractal fractal-sepia`

### - blur an Image

`blur fractal-sepia fractal-blur-sepia`

### - stack blur on the same Image

`blur fractal-blur-sepia fractal-blur2-sepia`

### - sharpen an Image

`sharpen fractal fractal-sharpened`

### - stack sharpen on the same Image

`sharpen fractal-sharpened fractal-sharpened2`

### - produce a dithered version of an Image

`dither fractal fractal-dithered`

### - loading image in one format, performing operations on it and saving it in another format

`load res/images/fractal.bmp fractal`

`sharpen fractal fractal-sharpened`

`blur fractal fractal-blurred`

`sepia-tone fractal-blurred fractal-blurred-sepia`

`save res/images/fractal-blurred-sepia.png fractal-blur

### To run a script which contains all the available functions -
if you are inside the res folder, use

`java -jar A6.jar -file scripts/mainTestScript.txt`

or

`java -jar A6.jar -text`

or

For GUI — `java -jar A6.jar`


## —> Using GUI

1. **Exposure** - Used to brighten or darken the![img_1.png](res%2Fimages%2Fuseme%2Fimg_1.png)
   Give +ve values for brightening and -ve values for darkening the image.
2. **Filter** - Used to _Blur_ or _Sharpen_ an image.![img_2.png](res%2Fimages%2Fuseme%2Fimg_2.png)
3. **GreyscaleFunctions** — This button is used to turn the image into a greyscale image using one of the below methods.![img_3.png](res%2Fimages%2Fuseme%2Fimg_3.png)
    1. value-component — Convert a rgb image to greyscale using the value component.
    2. luma-component — Convert a rgb image to greyscale using the luma component.
    3. intensity-component — Convert a rgb image to greyscale using the intensity component.
    4. red-component — Convert a rgb image to greyscale using the red component.
    5. green-component — Convert a rgb image to greyscale using the green component.
    6. blue-component — Convert a rgb image to greyscale using the blue component.
4. **Dither** - This button dithers the existing image.![img_4.png](res%2Fimages%2Fuseme%2Fimg_4.png)
5. **Flip** - The button lets us flip an image _horizontally_ or _vertically_.![img![img_5.png](res%2Fimages%2Fuseme%2Fimg_5.png)
6. **Sepia** - This button add a sepia-tone to the image. ![im![img_6.png](res%2Fimages%2Fuseme%2Fimg_6.png)
7. **Split to RGB Components** - This button lets us split the rgb into a single channel greyscale image of red, greed or blue component.![img_7.png](res%2Fimages%2Fuseme%2Fimg_7.png)
8. **Combine RGB Components** - This button lets us combine the rgb into a 3 channel rgb image of red, greed or blue component.![img_8.png](res%2Fimages%2Fuseme%2Fimg_8.png)
9. **← Undo** - This button is used to revert the latest change to an image.
9. **→ Redo** -  This button is used to repeat the operation which was previously undone.
10. **Open a file** - This button lets us load an image to the application.
11. **Save a file** - This button is used to save the latest image.


## Changes :

1. Removed `darken` method from model and used only `brighten` and a helper method (`clamp`) to
   perform both the operations.
2. Extracted the concrete `Pixel` class to an interface and `PixelImpl` to implement its methods.
3. Extracted the `main method` to a different class called `IMEApplication`.
4. Implemented the Command Design Patterns for the controller and created different classes for each
   of the commands in `controller.commands` package.
5. Created a `view` for the application called `ImageView`.
6. Removed save functionality from the AbstractModel.java.
7. Created a new controller implementation called `GUIController` ( implements `Features` ) to handle all requests from the GUI application.
8. Created a new view `GUIView` to display a GUI to interact with the application.
9. Added 2 new methods for ImageControllerImp `calculateHistogram` & `updatedImage`.
10. Added a method `getFileName` in the `ImageUtil` as a utility method.
11. Added a method `calculateHistogram` in `AbstractImage`.
12. `IMEApplication` is changed to handle running the application using script (command line arguments), terminal commands & GUI.
13. Added a class for constants for readability and to remove magic numbers/strings in view `Identifiers`

# Citation :

```
   We have created the image using our ipad and have not depended on any other sources for the project.
```