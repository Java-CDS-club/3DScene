package geometry;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import primitives.*;
import elements.*;

public class Plane extends Geometry implements flatGeometry
{
	public Point3D point1;  //point on plane
	public Vector vector;  //normal to plane
	// ***************** Constructors ********************** // 
	
	public Plane(Color clr, Point3D point1, Vector vector) {
		super(clr);
		this.point1 = point1;
		this.vector = vector;
	}
	
	public Plane(Color clr, Material m,Point3D point1, Vector vector) {
		super(clr,m);
		this.point1 = point1;
		this.vector = vector;
	}

	public Plane() 
	{
		super();
		this.point1 = new Point3D();
		vector=new Vector();
	}
	public Plane(Plane p) 
	{
		super(p.clr,p.material);
		this.point1 = new Point3D(p.point1);
		vector=new Vector(p.vector);
	}
	// ***************** Getters/Setters ********************** // 
	public Point3D getPoint1() {
		return (point1);
	}
	public void setPoint1(Point3D point1) {
		this.point1 = point1;
	}
	public Vector getVector() {
		return (vector);
	}
	public void setVector(Vector vector) {
		this.vector = vector;
	}
	// ***************** Administration  ******************** // 
	
	public Vector getNormal(Point3D point)
	{
		vector.normalize();
		return new Vector(vector); 
		
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plane other = (Plane) obj;
		if (point1 == null) {
			if (other.point1 != null)
				return false;
		} else if (!point1.equals(other.point1))
			return false;
		if (vector == null) {
			if (other.vector != null)
				return false;
		} else if (!vector.equals(other.vector))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Plane [point1=" + point1 + ", vector=" + vector + ", clr=" + clr + ", material=" + material + "]";
	}

	@Override
	public List<Point3D> findIntersections(Ray r)
	{
		List<Point3D > lst= new ArrayList<Point3D>();
	
		Vector V= new Vector(r.getDirection());
		
		Vector N= new Vector(this.getVector());
		N.scale(-1);
		
		Point3D q0=new Point3D(point1);
		Vector v=new Vector(q0);
		Point3D p0=new Point3D(r.getStart());
		p0.substract(v);
		Vector v1=new Vector(p0);
		double help= N.dotProduct(v1);
		
		Vector N1= new Vector(this.getVector());
		double help2=N1.dotProduct(r.getDirection());
		if(help2==0)
			return lst;
		
		double t= help/help2;
		
		V.scale(t);
		Point3D p1=new Point3D(r.getStart());
		p1.add(V);
		
		Point3D P=new Point3D(p1);
		Vector P1= new Vector(point1);
		P.substract(P1);
		P1= new Vector(P);
		//if(P1.dotProduct(this.getVector())==0)
				lst.add(p1);
		return lst;

	}
	
	
}
