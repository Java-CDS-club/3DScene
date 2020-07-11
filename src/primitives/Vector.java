package primitives;

public class Vector {
	
	
	protected Point3D head;
	// ***************** Constructors ********************** // 

	public Vector(Point3D head) {
		this.head = new Point3D(head);
	}
	public Vector() {
		this.head = new Point3D();
	}
	public Vector(Vector v) {
		
		this.head = new Point3D(v.head);
	}
	// ***************** Getters/Setters ********************** // 
	public Point3D getHead() {
		return (head);
	}
	public void setHead(Point3D head) {
		this.head = head;
	}
	public Vector(Point3D p1,Point3D p2)
	{
		p1.substract(new Vector(p2));
		head=(p1);
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
		Vector other = (Vector) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		return true;
	}
		@Override
		public String toString() {
			return head.toString();
		}
		
	public void add (Vector vector )
	{
		head.add(vector);
	}
	public void subtract (Vector vector )
	{
		head.substract(vector);
	}
	public void scale(double scalingFacor)
	{
		head.x.setC(head.getX().getC()*scalingFacor);
		head.y.setC(head.getY().getC()*scalingFacor);
		head.z.setC(head.getZ().getC()*scalingFacor);
	}
	public double length()
	{
		return Math.sqrt(head.x.c*head.x.c+head.y.c*head.y.c+head.z.c*head.z.c);
	}
	public void normalize()
	{
		double length=length();
		if (length!=0)
		{
		head.x.setC(head.getX().getC()/length);
		head.y.setC(head.getY().getC()/length);
		head.z.setC(head.getZ().getC()/length);
		}
	}
	public Vector crossProduct (Vector v)
	{
		double X= (head.y.c*v.head.z.c-head.z.c*v.head.y.c);
		double Y= (head.z.c*v.head.x.c-head.x.c*v.head.z.c);
		double Z=(head.x.c*v.head.y.c-head.y.c*v.head.x.c);
		Point3D p=new Point3D(X,Y,Z);
		return new Vector(p);
	}
	public double dotProduct(Vector v)
	{
		return (head.x.c*v.head.x.c)+(head.y.c*v.head.y.c)+(head.z.c*v.head.z.c);
	}

	public void add(double d)
	{
		this.head.x=new Coordinate(head.x.getC()+d);
		this.head.y=new Coordinate(head.y.getC()+d);
		this.head.z=new Coordinate(head.z.getC()+d);
	}
	public Vector multiplyScalar(double a) {
		return new Vector(new Point3D(head.getX().getC()*a, head.getY().getC()*a, head.getZ().getC()*a));
	}
	public Vector substract(Vector v)
	{
		Vector temp = new Vector(v).multiplyScalar(-1);
		this.add(temp);
		return this;
	}
}
