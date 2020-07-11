package geometry;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import primitives.*;

public class Sphere extends Geometry
{

public double radius;
public Point3D center;

public final static double EPSILON = 0.0001;

//***************** Constructors ********************** // 

public Sphere(Color clr, double radius, Point3D center) {
	super(clr);
	this.radius = radius;
	this.center = new Point3D(center);
}
public Sphere(Color clr, Material material, double radius, Point3D center) {
	super(clr, material);
	this.radius = radius;
	this.center = center;
}
public Sphere() {
	super();
	this.radius=0;
	this.center = new Point3D();
	
}
public Sphere(Sphere s) 
{
	super(s.clr,s.material);
	this.radius = s.radius;
	this.center = new Point3D(s.center);
}
//***************** Getters/Setters ******************* // 
public double getRadius() {
	return radius;
}
public void setRadius(double radius) {
	this.radius = radius;
}
public Point3D getCenter() {
	return (center);
}
public void setCenter(Point3D center) {
	this.center = center;
}
//***************** Administration  ******************* // 


public Vector getNormal(Point3D point)
{
	Point3D p1=new Point3D(point);
	Vector v1=new Vector(center);
	p1.substract(v1);
	Vector vec=new Vector(p1);
	vec.normalize();
	return vec;
}

@Override
public String toString() {
	return "Sphere [radius=" + radius + ", center=" + center + ", clr=" + clr + ", material=" + material + "]";
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Sphere other = (Sphere) obj;
	if (center == null) {
		if (other.center != null)
			return false;
	} else if (!center.equals(other.center))
		return false;
	if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
		return false;
	return true;
}
@Override
public List<Point3D> findIntersections(Ray r) 
{
	
	List<Point3D > lst= new ArrayList<Point3D>();
	
	//V.normalize();
	Point3D p0= new Point3D(r.getStart());
	Point3D cen= new Point3D(center);
	cen.substract(new Vector(p0));
	Vector L= new Vector(cen);
	double l=L.length();
	
	double tm=L.dotProduct(r.getDirection());
	/*
	if (tm<0)
		return lst;
	
	double d= Math.sqrt((l*l)-(tm*tm));
	if(d>radius)
		return lst;
	else
	{
			double th= Math.sqrt((radius*radius)-(d*d));
			double t1=tm-th;
			double t2=tm+th;
			//V.normalize();
			Vector vec1= new Vector(r.getDirection());
			Vector vec2= new Vector(r.getDirection());
			vec1.scale(t1);
			vec2.scale(t2);
			Point3D  p1= new Point3D(r.getStart());	
			Point3D  p2= new Point3D(r.getStart());
			
			p1.add(vec1);
			p2.add(vec2);
			
			if(!p1.equals(p2))
			{
				lst.add(p1);
				lst.add(p2);
			}
			else
				lst.add(p1);
			return lst;
	}
		*/
	if (Math.abs(l - radius) < EPSILON) {
		// Ray starts at the sphere
		lst.add(p0); // add the head of the ray
		if (tm < EPSILON)
			// It is either out of sphere or tangent to sphere
			return lst;
		// ray goes through the sphere - find the 2nd intersection point
		lst.add(p0.add(r.getDirection().multiplyScalar(tm + tm).getHead()));
		return lst;
	} else if (l < radius) {
		// ray starts inside the sphere - there must be exactly one
		// intersection point - lets calculate it
		//
		// d2 = l2 - tm2
		// th = r2 - d2 = r2 - l2 + tm2
		double th = Math.sqrt((radius*radius) - (l*l) + tm * tm);
		lst.add(p0.add(r.getDirection().multiplyScalar(tm + th).getHead()));
		return lst;
	} else {
		// Ray starts outside the sphere
		if (tm <EPSILON) {
			// If it is close to zero - L & D are orthogonal and if it is
			// negative than the sphere is in the back of the ray - no
			// intersections in both cases
			return lst;
		}
		double d2 = l * l - tm * tm;
		if (Math.abs((radius*radius) - d2) < EPSILON *EPSILON) {
			lst.add(p0.add(r.getDirection().multiplyScalar(tm).getHead()));
		} else if (d2 > (radius*radius)) {
			return lst;
		} else {
			double th = Math.sqrt((radius*radius) - d2);
			lst.add(p0.add(r.getDirection().multiplyScalar(tm - th).getHead()));
			lst.add(p0.add(r.getDirection().multiplyScalar(tm + th).getHead()));
		}
		return lst;
	}

}

}
