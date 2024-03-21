import java.awt.Color;

//morphs to greyscale
public class Editor4 {
    public static void main (String[] args) {
        //gets desired img
		String source = args[0];
        Color[][] sourceImage = Runigram.read(source);
        //gets n desired phases of morph
        int n = Integer.parseInt(args[1]);

        //greyscales source pic
        //this output will be used as target
        Color[][] greyScaledImg = Runigram.grayScaled(sourceImage);

        //now we morph from source to greyscalled in n phases
        Runigram.setCanvas(sourceImage);
		Runigram.morph(sourceImage, greyScaledImg, n);
    }
}