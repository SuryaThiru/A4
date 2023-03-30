# Image Manipulation and Enhancement using Java

## Application Architecture

- `Image.java` — Represents a model in our MVC architecture.
- `ImageController.java` — Represents a controller in our MVC architecture.
- `ImageView.java` — Represents a view in our MVC architecture.
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

# Citation

```
   We have created the image using our ipad and have not depended on any other sources for the project.
```

## Changes

1. Removed `darken` method from model and used only `brighten` and a helper method (`clamp`) to
   perform both the operations.
2. Extracted the concrete `Pixel` class to an interface and `PixelImpl` to implement its methods.
3. Extracted the `main method` to a different class called `IMEApplication`.
4. Implemented the Command Design Patterns for the controller and created different classes for each
   of the commands in `controller.commands` package.
5. Created a `view` for the application called `ImageView`.
