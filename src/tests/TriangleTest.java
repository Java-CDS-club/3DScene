package tests;
import primitives.*;
import elements.Camara;
import geometry.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

public class TriangleTest {

	@Test
	public void testGetNormal() {
		/*Point3D p1=new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0));
		Point3D p2=new Point3D(new Coordinate(1),new Coordinate(2),new Coordinate(3));
		Point3D p3=new Point3D(new Coordinate(2),new Coordinate(0),new Coordinate(0));
		Color c=new Color(0,0,0);
		Triangle t=new Triangle(c, p1, p2, p3);
		Vector v1=new Vector(t.getNormal(new Point3D(new Coordinate(1.5),new Coordinate(0),new Coordinate(0))));
		assertEquals(0, v1.getHead().getX().getC(), 0);
		assertEquals(-3, v1.getHead().getY().getC(), 0);
		assertEquals(2, v1.getHead().getZ().getC(), 0);*/
		Point3D x=new Point3D(2,4,5);
		Point3D y=new Point3D(2,2,2);
		Point3D z=new Point3D(4,4,2);
		Triangle t=new Triangle(new Color(0,0,0),x,y,z);
		Vector v=t.getNormal(new Point3D(0,0,0));
		assertEquals(-6.0,v.getHead().getX().getC(),0);
		assertEquals(6.0,v.getHead().getY().getC(),0);
		assertEquals(-4.0,v.getHead().getZ().getC(),0);
	}
	
	@Test
	public void findIntersections() 
	{
	/*Triangle t=new Triangle(new Color(0,0,0),new Point3D(0,4.5,-4),new Point3D(-4.5,-4.5,-4),new Point3D(4.5,-4.5,-4));
	Camara c= new Camara();
	Ray r=new Ray();
	r=c.constructRayThroughAPixel(3,3,2.0,2.0,1.0,9.0,9.0);
	List<Point3D> lst=t.findIntersections(r);
	
	List<Point3D> lst1=new ArrayList<Point3D>();
	lst1.add(new Point3D(0.0,0.0,-4.0));
	assertEquals(lst1, lst);*/
		
		Vector v=new Vector(new Point3D(50,-50,-100));
		v.normalize();
		Point3D p=new Point3D(0,0,0);
		Ray ray=new Ray(p,v);

		Triangle triangle=new Triangle(new Color(0,0,0),new Point3D(100,-100,-200),new Point3D(-100,-100,-200),new Point3D(0,100,-200));
		List<Point3D> list=triangle.findIntersections(ray);
		assertEquals(list.size(),0);
	}
	@Test
	public void findIntersections2() 
	{
		//test 2

		Triangle t=new Triangle(new Color(0,0,0),new Point3D(0,4.5,-4),new Point3D(-4.5,-4.5,-4),new Point3D(4.5,-4.5,-4));
		Camara c= new Camara();
		Ray r=new Ray();
		r=c. constructRayThroughAPixel(3,3,2.0,2.0,1.0,9.0,9.0);
		
		List<Point3D> lst=t.findIntersections(r); 
		
		List<Point3D> lst1=new ArrayList<Point3D>();
		lst1.add(new Point3D(0.0,0.0,-4.0));
		assertEquals(lst1, lst);

	}


}
