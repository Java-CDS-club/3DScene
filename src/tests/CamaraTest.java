package tests;
import primitives.*;
import elements.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class CamaraTest {

	@Test
	public void testRaysConstruction1() 
	{
		Camara cam=new Camara();
		Ray r1=new Ray(cam.constructRayThroughAPixel(3,3,3.0,3.0,100.0,150.0,150.0));
		Vector v=new Vector(new Point3D(50,-50,-100));
		v.normalize();
		Ray r2=new Ray(new Point3D(0,0,0),v);
		assertEquals(r2,r1);
	}
	
	
		@Test
		public void testRaysConstruction2()
	{
			/*
		Camara cam=new Camara();
		Ray r4=new Ray(cam.constructRayThroughAPixel(3,3,1.0,2.0,1.0,9.0,9.0));
		Vector v=new Vector(new Point3D(-3,0,-1));
		v.normalize();
		Ray r3=new Ray(new Point3D(0,0,0),v);
		assertEquals(r3,r4);
		*/

			
			Camara cam_=new Camara();
			Ray r_=cam_.constructRayThroughAPixel(10, 10, 8.0, 1.0, 20.0, 100.0, 100.0);
			
			Vector v_=new Vector(new Point3D(-45.0,-25.0,-20.0));
			v_.normalize();
			Ray currect_=new Ray(new Point3D(0,0,0),v_);
			assertEquals(r_,currect_);	
	}

}
