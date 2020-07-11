// avigail wilk 314086208 and nechama verbov 318755790
// project phase 1
// classes of primitives


package main;
import elements.Camara;
import geometry.Triangle;

import java.awt.Color;

import primitives.*;

public class Main {

	public static void main(String[] args)
	{
	/*	Vector vec1=new Vector();
		Point3D p2=new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0));
		Point3D p1=new Point3D(new Coordinate(0),new Coordinate(1),new Coordinate(0));
		Vector vec2=new Vector(p2);
		vec1.setHead(p1);
		System.out.println(vec1.crossProduct(vec2));
		System.out.println(vec1);*/

		Point3D p1=new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0));
		Point3D p2=new Point3D(new Coordinate(1),new Coordinate(2),new Coordinate(3));
		Point3D p3=new Point3D(new Coordinate(2),new Coordinate(0),new Coordinate(0));
		Color c=new Color(0,0,0);
		Triangle t=new Triangle(c, p1, p2, p3);
		Vector v1=new Vector(t.getNormal(new Point3D(new Coordinate(1.5),new Coordinate(0),new Coordinate(0))));
		System.out.println(v1);
		Camara cam=new Camara();
		Ray r4=new Ray(cam.constructRayThroughAPixel(3,3,1.0,2.0,1.0,9.0,9.0));
	}

}
