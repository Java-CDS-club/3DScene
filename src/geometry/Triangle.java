package geometry;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import elements.Camara;
import primitives.*;

public class Triangle extends Geometry implements flatGeometry
{
public Point3D point1;
public Point3D point2;
public Point3D point3;
//***************** Constructors ********************** // 
public Triangle(Color clr, Point3D point1, Point3D point2, Point3D point3) {
	super(clr);
	this.point1 =new Point3D( point1);
	this.point2 = new Point3D(point2);
	this.point3 = new Point3D(point3);
}

public Triangle(Color clr,Material m, Point3D point1, Point3D point2, Point3D point3) {
	super(clr,m);
	this.point1 = point1;
	this.point2 = point2;
	this.point3 = point3;
}

public Triangle() {
	super();
	this.point1 = new Point3D();
	this.point2 = new Point3D();
	this.point3 = new Point3D();
}
public Triangle(Triangle tr) {
	super(tr.clr,tr.material);
	this.point1 = tr.point1;
	this.point2 = tr.point2;
	this.point3 = tr.point3;
}


//***************** Getters/Setters ******************* // 

public Point3D getPoint1() {
	return (point1);
}
public void setPoint1(Point3D point1) {
	this.point1 = point1;
}
public Point3D getPoint2() {
	return (point2);
}
public void setPoint2(Point3D point2) {
	this.point2 = point2;
}
public Point3D getPoint3() {
	return (point3);
}
public void setPoint3(Point3D point3) {
	this.point3 = point3;
}

//***************** Administration  ******************* // 


public Vector getNormal(Point3D point)
{
	Point3D p1=new Point3D(point1);
	Vector v1=new Vector(point2);
	p1.substract(v1);
	Point3D p2=new Point3D(point3);
	p2.substract(v1);
	Vector v2=new Vector(p1);
	Vector v3=new Vector(p2);
	Vector v=new Vector(v2.crossProduct(v3));
	v.normalize();
	
	return v;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	Triangle other = (Triangle) obj;
	if (point1 == null) {
		if (other.point1 != null)
			return false;
	} else if (!point1.equals(other.point1))
		return false;
	if (point2 == null) {
		if (other.point2 != null)
			return false;
	} else if (!point2.equals(other.point2))
		return false;
	if (point3 == null) {
		if (other.point3 != null)
			return false;
	} else if (!point3.equals(other.point3))
		return false;
	return true;
}


@Override
public String toString() {
	return "Triangle [point1=" + point1 + ", point2=" + point2 + ", point3=" + point3 + ", clr=" + clr + ", material="
			+ material + "]";
}

@Override
public List<Point3D> findIntersections(Ray r) 
{
	List<Point3D > lst= new ArrayList<Point3D>();
	
	Vector V= new Vector(r.getDirection());
	
	Vector N= new Vector(this.getNormal(new Point3D(0,0,0)));
	N.scale(-1);
	
	Point3D q0=new Point3D(point1);
	Vector v=new Vector(q0);
	Point3D p0=new Point3D(r.getStart());
	p0.substract(v);
	Vector v1=new Vector(p0);
	double help= N.dotProduct(v1);
	
	Vector Norm= new Vector(this.getNormal(new Point3D(0,0,0)));
	double help2=Norm.dotProduct(r.getDirection());
	if(help2==0)
		return lst;
	
	double t= help/help2;
	
	V.scale(t);
	Point3D p1=new Point3D(r.getStart());
	p1.add(V);
	
	Vector vec1= new Vector(new Point3D(point1));
	Vector vec2= new Vector(new Point3D(point2));
	Vector vec3= new Vector(new Point3D(point3));
	Vector P= new Vector(p1);
	Vector N1= (vec2.crossProduct(vec1));
	N1.normalize();
	Vector N2= (vec3.crossProduct(vec2));
	N2.normalize();
	Vector N3= (vec1.crossProduct(vec3));
	N3.normalize();
	
	if(((P.dotProduct(N1)<0&&P.dotProduct(N2)<0&&P.dotProduct(N3)<0)||(P.dotProduct(N1)>0&&P.dotProduct(N2)>0&&P.dotProduct(N3)>0)))
	{
		//if (p1.distance(r.getStart()) < d)
			lst.add(p1);
	}
		
	return lst;
	
}


}
