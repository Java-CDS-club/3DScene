package primitives;

public class Point3D extends Point2D {
	
	protected Coordinate z;
	// ***************** Constructors ********************** // 

	public Point3D(double x, double y, double z)
	{
		super(new Coordinate(x),new Coordinate(y));
		this.z = new Coordinate(z);
	}	
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		super((x), (y));
		this.z = (z);
	}
	public Point3D()
	{
		super();
		z=new Coordinate();
	}
	public Point3D(Point3D p)
	{
		super(new Point2D(new Coordinate(p.x),new Coordinate(p.y)));
		z=new Coordinate(p.z);
	}
	// ***************** Getters/Setters ********************** // 
	public Coordinate getZ() {
		return (z);
	}
	public void setZ(Coordinate z) {
		this.z = z;
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
		Point3D other = (Point3D) obj;
		return (x.equals(other.getX())&&y.equals(other.getY())&&z.equals(other.getZ()));

	}
	@Override
	public String toString() {
		return " (" + x +","+y+","+z+")";
	}
	
	/*  public double distance(Point3D other) {
	   double tmp1 = x.getC() - other.x.getC();
	   double tmp2 = y.getC() - other.y.getC();
	   double tmp3 = z.getC() - other.z.getC();
	   return Math.sqrt((tmp1 * tmp1) + (tmp2 * tmp2) + (tmp3 * tmp3));
	}*/
	public double distance(Point3D p)
	{
		return Math.sqrt ((x.c-(p.getX()).c)*(x.c-(p.getX()).c)+
			(y.c-(p.getY()).c)*(y.c-(p.getY()).c)+
					(z.c-(p.getZ().c)*(z.c-(p.getZ().c))));
	}
    public void add(Vector vector) {
		
		this.x.add(vector.getHead().getX());
		this.y.add(vector.getHead().getY());
		this.z.add(vector.getHead().getZ());

	}
    public void substract(Vector vector) {
		
		this.getX().substract(vector.getHead().getX());
		this.getY().substract(vector.getHead().getY());
		this.z.substract(vector.getHead().getZ());

	}
    public Point3D substract1(Point3D other) {
		return new Point3D(x.add(-other.x.getC()), y.add(-other.y.getC()), z.add(-other.z.getC()));
	}

    public Point3D add(Point3D other) {
		return new Point3D(x.add(other.x.getC()), y.add(other.y.getC()), z.add(other.z.getC()));
	}
}
