package elements;
import primitives.*;


public class Camara 
{
	protected Point3D middle;
	protected Vector vUp;      //y
	protected Vector vToward;  //z
	protected Vector vRight;   //x
	// ***************** Constructors ********************** // 
	public Camara( Vector vUp, Vector vToward, Vector vRight) {
		this.middle = new Point3D(0,0,0);
		this.vUp = (vUp);
		this.vToward = (vToward);
		this.vRight = (vRight);
	}
	public Camara() {
		this.middle = new Point3D(0,0,0);
		this.vUp = new Vector(new Point3D(0,1,0));
		this.vToward = new Vector(new Point3D(0,0,-1));
		this.vRight = new Vector(new Point3D(1,0,0));
	}
	public Camara(Camara c) {
		this.middle = new Point3D(c.middle);
		this.vUp = new Vector(c.vUp);
		this.vToward = new Vector(c.vToward);
		this.vRight = new Vector(c.vRight);
	}
	// ***************** Getters/Setters ********************** // 
	public Point3D getMiddle() {
		return (middle);
	}
	public void setMiddle(Point3D middle) {
		this.middle = middle;
	}
	public Vector getvUp() {
		return (vUp);
	}
	public void setvUp(Vector vUp) {
		this.vUp = vUp;
	}
	public Vector getvToward() {
		return (vToward);
	}
	public void setvToward(Vector vToward) {
		this.vToward = vToward;
	}
	public Vector getvRight() {
		return (vRight);
	}
	public void setvRight(Vector vRight) {
		this.vRight = vRight;
	}
	// ***************** Administration  ******************** // 
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camara other = (Camara) obj;
		if (middle == null) {
			if (other.middle != null)
				return false;
		} else if (!middle.equals(other.middle))
			return false;
		if (vRight == null) {
			if (other.vRight != null)
				return false;
		} else if (!vRight.equals(other.vRight))
			return false;
		if (vToward == null) {
			if (other.vToward != null)
				return false;
		} else if (!vToward.equals(other.vToward))
			return false;
		if (vUp == null) {
			if (other.vUp != null)
				return false;
		} else if (!vUp.equals(other.vUp))
			return false;
		return true;
	}
	public String toString() {
		return "Camara: Point"+middle.toString()+"Vectors: "+vRight.toString()+" "+vToward.toString()+" "+vUp.toString();
	
	}
	public Ray constructRayThroughAPixel(int Nx, int Ny, double x, double y,double screenDist,double screenWidth,double screenHeight)
	{
		
		Point3D pc=new Point3D(middle);
		Vector v3=new Vector(vToward);
		v3.scale(screenDist);
		pc.add(v3);
		double rx=screenWidth/Nx;
		double ry=screenHeight/Ny;
		double firstPart=(x-(Nx/2.0))*(rx)-(rx/2.0);
		double secondPart=(y-(Ny/2.0))*(ry)-((ry)/2.0);
		Vector v1=new Vector(vUp);
		v1.scale(firstPart);
		Vector v2=new Vector(vRight);
		v2.scale(secondPart);
		v1.subtract(v2);
		pc.add(v1);
		Vector v=new Vector(pc);
		v.normalize();
		return new Ray((middle),v);
	}
}
