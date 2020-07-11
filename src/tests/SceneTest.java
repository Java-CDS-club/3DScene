package tests;
import static org.junit.Assert.*;
import geometry.Triangle;
import scene.*;
import primitives.*;
import java.awt.Color;

import org.junit.Test;

public class SceneTest {

	@Test
	public void test() 
	{
		Scene s=new Scene();
		s.addGeometry(new Triangle(new Color(0,0,0),
				new Point3D(1,2,3), new Point3D(1,2,3), new Point3D(1,2,3)));
		//Triangle t=new Triangle(new Color(0,0,0),new Point3D(1,2,3), new Point3D(1,2,3), new Point3D(1,2,3));
		assertEquals(false , s.getGeometry().isEmpty());
	}

}
