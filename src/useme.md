# How to use this application :

### - load fractal.ppm and call it 'fractal'

`load res/images/fractal.ppm fractal`

### - brighten fractal by adding 10

`brighten 10 fractal fractal-brighter`

### - flip fractal vertically

`vertical-flip fractal fractal-vertical`

### - flip the vertically flipped fractal horizontally

`horizontal-flip fractal-vertical fractal-vertical-horizontal`

### - create a greyscale using only the value component, as an image fractal-greyscale

`greyscale value-component fractal fractal-greyscale`

### - save fractal-brighter

`save res/images/fractal-brighter.ppm fractal-brighter`

### - save fractal-greyscale

`save res/images/fractal-gs.ppm fractal-greyscale`

### - overwrite fractal with another file

`load res/images/upper.ppm fractal`

### - give the koala a red tint

`rgb-split fractal fractal-red fractal-green fractal-blue`

### - brighten just the red image

`brighten 50 fractal-red fractal-red`

### - combine them back, but by using the brightened red we get a red tint

`rgb-combine fractal-red-tint fractal-red fractal-green fractal-blue`

### - save the red tinted image

`save res/images/fractal-red-tint.ppm fractal-red-tint`

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

`save res/images/fractal-blurred-sepia.png fractal-blurred-sepia`