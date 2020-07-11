package tests;
import primitives.*;
import geometry.*;
import elements.*;

import java.awt.Color;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SphereTest {

	@Test
	public void testGetNormal() 
	{
		Point3D p=new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0));
		Color c=new Color(0,0,0);
		Sphere s=new Sphere(c,3,p);
		Vector v1=new Vector(s.getNormal(p));
		
		assertEquals(0, v1.getHead().getX().getC(), 0);
		assertEquals(0, v1.getHead().getY().getC(), 0);
		assertEquals(0, v1.getHead().getZ().getC(), 0);
		
		/*Point3D p=new Point3D(0,0,0);
		Sphere s=new Sphere(new Color(0,0,0),2,p);

		Vector temp=s.getNormal(new Point3D(2,0,0));
		assertEquals(2.0,temp.getHead().getX().getC(),0);
		assertEquals(0.0,temp.getHead().getY().getC(),0);
		assertEquals(0.0,temp.getHead().getZ().getC(),0);*/
	   
	}
	
	@Test
	public void findIntersections() 
	{
	Sphere s=new Sphere(new Color(0,0,0),1.0,new Point3D(0,0,-3));
	Ray r=new Ray();
	Camara c=new Camara();
	r=c.constructRayThroughAPixel(3,3,2.0,2.0,1.0,9.0,9.0);
	List<Point3D> lst=s.findIntersections(r);
	List<Point3D> lst1=new ArrayList<Point3D>();
	lst1.add(new Point3D(0,0,-2));
	lst1.add(new Point3D(0,0,-4));
	assertEquals(lst1, lst);
	/*	Vector v=new Vector(new Point3D(1,-1,-1));
		v.normalize();
		Point3D p=new Point3D(0,0,0);
		Ray ray=new Ray(p,v);
		
		Sphere sphere=new Sphere(new Color(0,0,0),200,new Point3D(0,0,-400));
		List<Point3D> list=sphere.findIntersections(ray);
		assertEquals(list.size(),0);*/
	}
	@Test
	public void findIntersections2() 
	{
		Vector v_=new Vector(new Point3D(100,80,150));
		v_.normalize();
		Point3D p_=new Point3D(0,0,0);
		Ray ray_=new Ray(p_,v_);
		
		Sphere sphere_=new Sphere(new Color(0,0,0),180,new Point3D(200,50,0));
		List<Point3D> list_=sphere_.findIntersections(ray_);		
		List<Point3D> correct=new ArrayList<Point3D>();
		correct.add(new Point3D(26.91050291857561,21.528402334860488,40.36575437786342));
		correct.add(new Point3D(96.48281327679712,77.18625062143771,144.7242199151957));
		assertEquals(list_,correct);
	}

}
