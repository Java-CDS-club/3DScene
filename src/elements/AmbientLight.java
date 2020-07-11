package elements;

import java.awt.Color;

import primitives.*;

public class AmbientLight extends Light
{
	protected double Ka;

	// ***************** Constructors ********************** //
	
	public AmbientLight() {
		super();
		this.Ka =0.1;
	}

public AmbientLight(Color color, double _Ka) {
	super(color);
	this.Ka = _Ka;
}
public AmbientLight(AmbientLight al) {
	super(al.l0);
	this.Ka = al.Ka;
}

//***************** Getters/Setters ********************** // 

public double getKa() {
	return Ka;
}
public void setKa(double ka) {
	Ka = ka;
}
//***************** Administration  ******************** //

@Override
public Vector getL(Point3D point)
{
	return new Vector();
}

@Override
public  Color  getIntensity(Point3D point)
{
	 Color c=new Color((int)(l0.getRed()*Ka),(int)(l0.getGreen()*Ka),(int)(l0.getBlue()*Ka));
   	return c;
}

public  double distance(Point3D p)
{
	return 0;
}


}
