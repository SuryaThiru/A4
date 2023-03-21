# Image Manipulation and Enhancement using Java
This application is used to perform image manipulation techniques on an image using command line or a script file.

<<<<<<< HEAD
It can perform the following tasks :
=======
It can perform the following tasks : 
>>>>>>> feature/after-readme
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

<<<<<<< HEAD
### brighten flower by adding 10
=======
### brighten flower by adding 10  
>>>>>>> feature/after-readme
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
<<<<<<< HEAD
```
=======
```


>>>>>>> feature/after-readme
