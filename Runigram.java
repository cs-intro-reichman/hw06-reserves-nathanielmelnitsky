// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		//Color[][] thor = read("thor.ppm");
		//Color[][] cake = read("cake.ppm");
		//print(tinypic);
		//print(flippedHorizontally(tinypic));
		//print(flippedVertically(tinypic));
		//print(grayScaled(tinypic));
		//print(scaled(tinypic, 3, 5));

		// Creates an image which will be the result of various 
		// image processing operations:
		//Color[][] imageOut;

		// Tests the horizontal flipping of an image:
		//imageOut = flippedHorizontally(tinypic);
		//System.out.println();
		//print(imageOut);
		
		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.

		//test code for blend (on two colors)
		//Color c1 = new Color(100, 40, 100);
		//Color c2 = new Color(200, 20, 40);
		//print(blend(c1, c2, .25));

		//tests the blending of 2 imgs
		//adjusts target image to source dimentions
		//int height = thor.length;
		//int width = thor[0].length;
		//Color[][] newCake = scaled(cake, width, height);
		//blend(thor, newCake, .5);
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		//System.out.println(image.length);
		//System.out.println(image[0].length);

		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				Color currentPix = new Color(in.readInt(), in.readInt(), in.readInt());
				image[i][j] = currentPix;
			}
		}
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		int numRows = image.length;
		int numCols = image[0].length; 
		System.out.println();
		for(int i = 0; i < numRows; i++){
			System.out.println();
			for(int j = 0; j < numCols; j++){
				Color currentPixColor = image[i][j];
				print(currentPixColor);
			}
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		int rows = image.length;
		int cols = image[0].length;
		Color[][] newImg = new Color[rows][cols];
		//uses increment to get each pix on opposite side of row and store
		System.out.println();
		for(int i = 0; i < rows; i++){
			for(int j = 0; j  < cols; j++){
				Color oppositColor = image[i][cols - j - 1];
				newImg[i][j] = oppositColor;
			}
		}
		//System.out.println();
		//print(newImg);
		return newImg;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		int rows = image.length;
		int cols = image[0].length;
		Color[][] newImg = new Color[rows][cols];
		//uses increment to avoid those pesky out of bounds errors
		//literally took me forever to figure that out
		System.out.println();
		for(int j = 0; j < cols; j++){
			for(int i = 0; i < rows; i++){
				Color oppositColor = image[rows - i - 1][j];
				newImg[i][j] = oppositColor;
			}
		}
		//System.out.println();
		//print(newImg);
		return newImg;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		//assigns vars to imput vals from given pix
		int r = pixel.getRed();
		int g = pixel.getGreen();
		int b = pixel.getBlue();

		//calcs with given formula
		//casts back to int
		int lum = (int) (0.299 * r + 0.587 * g + 0.114 * b);

		//creates new col obj to return
		Color greyScaleColor = new Color(lum, lum, lum);
		
		//returns
		return greyScaleColor;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//coll and row lengths for dimmensions of new img
		int r = image.length;
		int c = image[0].length;
		Color[][] greyImg = new Color[r][c];

		//loops through uses luminecence method to assign to newImg
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				Color lumed = luminance(image[i][j]);
				greyImg[i][j] = lumed;
			}
		}
		//print(greyImg);
		return greyImg;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//original dimentions
		int oldH = image.length;
		int oldW = image[0].length;
		//new dimentions based on parameters
		int newH = height;
		int newW = width;
		Color[][] newImg = new Color[newH][newW];

		//loops through uses scaled formula to assign to newImg
		for(int i = 0; i < newH; i++){
			for(int j = 0; j < newW; j++){
				newImg[i][j] = image[(int) (i * ((double) oldH/newH))][(int) (j * ((double) oldW/newW))];
			}
		}
		//print(newImg);
		return newImg;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		double weightC1 = alpha;
		double wieghtC2 = 1 - alpha;
		double r3 = (weightC1 * c1.getRed()) + (wieghtC2 * c2.getRed());
		double g3 = (weightC1 * c1.getGreen()) + (wieghtC2 * c2.getGreen());
		double b3 = (weightC1 * c1.getBlue()) + (wieghtC2 * c2.getBlue());

		Color blendedColor = new Color((int) r3, (int) g3, (int) b3);
		return blendedColor;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		int height = image1.length;
		int width = image1[0].length;
		Color[][] newImg = new Color[height][width];

		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				newImg[i][j] = blend(image1[i][j], image2[i][j], alpha);
			}
		}
		return newImg;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//adjusts target image to source dimentions
		int height = source.length;
		int width = source[0].length;
		Color[][] newTarget = scaled(target, width, height);

		//initializes our alpha that will change
		//through every phase of n
		double alpha;

		//creates new img obj to be returned and projected
		//after each phase of n
		Color[][] newImg = new Color[height][width];

		for(int i = 0; i <= n; i++){
			alpha = ((double) i/n);
			newImg = blend(newTarget, source, alpha);
			setCanvas(newImg);
			display(newImg);
			StdDraw.pause(500);
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}


