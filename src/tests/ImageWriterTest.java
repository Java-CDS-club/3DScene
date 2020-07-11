package tests;
import renderer.*;
//import java.awt.Image;
import java.awt.Color;
import org.junit.Test;


public class ImageWriterTest {

	
	
	public void printGrid(int interval,ImageWriter im)
	{
		for(int i=0;i<im.getNx();i+= interval)
			for(int j=0;j<im.getNy();j++)
					im.writePixel(i, j, new Color(255,255,255));
		
		for (int j = 0; j < im.getNx(); j += interval)
		{
			for (int i = 0; i < im.getNy(); i++) 
			{
				im.writePixel(i, j, Color.WHITE);
			}
		}
	}
	
	@Test
	public void testImage1() {
		ImageWriter image1= new ImageWriter("image1",501,501,501,501);
		for(int i=0;i<500;i++)
			for(int j=0;j<500;j++)
				image1.writePixel(j, i, new Color(0,0,0));
		printGrid(50,image1);
		image1.writeToimage();
				
		
	}
	
	@Test
	public void testImage2() {
		ImageWriter image2= new ImageWriter("image2",501,501,501,501);
		for(int i=0;i<500;i++)
			for(int j=0;j<500;j++)
				image2.writePixel(j, i, new Color(0,0,0));
		printGrid(50,image2);
		for(int i=200;i<300;i++)
			for(int j=200;j<i;j++)
				image2.writePixel(j, i, new Color(0,255,255));
		for(int j=200;j<300;j++)
			for(int i=200;i<j;i++)
				image2.writePixel(j, i, new Color(255,0,255));
		image2.writeToimage();
					

		
	}


}
