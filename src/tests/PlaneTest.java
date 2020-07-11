package tests;
import elements.Camara;
import geometry.*;
import primitives.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;



public class PlaneTest {

	@Test
	public void testGetNormal() 
	{
		Point3D p=new Point3D(1,0,0);
		Vector v=new Vector(new Point3D(1,1,1));
		Color c=new Color(0,0,0);
		Plane pl=new Plane(c,p,v);
		Vector v1=new Vector (pl.getNormal(p));
		assertEquals(1, v1.getHead().getX().getC(), 0);
		assertEquals(1, v1.getHead().getY().getC(), 0);
		assertEquals(1, v1.getHead().getZ().getC(), 0);
		
		
		/*	Point3D x=new Point3D(2,4,5);
		Vector v=new Vector(new Point3D(2,5,1));
		Plane p=new Plane(new Color(0,0,0),x,v);
		Vector temp=p.getNormal(new Point3D(0,0,0));
		assertEquals(2.0,temp.getHead().getX().getC(),0);
		assertEquals(5.0,temp.getHead().getY().getC(),0);
		assertEquals(1.0,temp.getHead().getZ().getC(),0);*/
	}

	@Test
	public void findIntersections() 
	{
		Plane p= new Plane(new Color(0,0,0),new Point3D(0,0,-4),new Vector(new Point3D(0,0,-1)));
		Camara c= new Camara();
		Ray r=new Ray();
		r=c.constructRayThroughAPixel(3,3,2.0,2.0,1.0,9.0,9.0);
		List<Point3D> lst=p.findIntersections(r);
		List<Point3D> lst1=new ArrayList<Point3D>();
		lst1.add(new Point3D(0,0,-4));
		assertEquals(lst1, lst);
		
		
		/*Vector v=new Vector(new Point3D(2,4,0));
		v.normalize();
		Point3D p=new Point3D(0,0,0);
		Ray ray=new Ray(p,v);
		
		Plane plane=new Plane(new Color(0,0,0),new Point3D(2,2,5),new Vector(new Point3D(1,0,1)));
		List<Point3D> list=plane.findIntersections(ray);
		
		List<Point3D> correct=new ArrayList<Point3D>();
		correct.add(new Point3D(7,14,0));
		
		assertEquals(list,correct);*/
	
	}
}
